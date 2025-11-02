package com.aman.synctalk_backend.controllers;

import com.aman.synctalk_backend.dto.MessageDto;
import com.aman.synctalk_backend.entities.Message;
import com.aman.synctalk_backend.entities.Room;
import com.aman.synctalk_backend.repositories.RoomRepository;
import com.aman.synctalk_backend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
public class ChatController {

    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomRepository roomRepository;

    // for sending and receiving messages
    @MessageMapping("/sendMessage/{roomId}") //client will send message to this endpoint // /app/sendMessage/{roomId}
    @SendTo("/topic/room/{roomId}") // all clients subscribed to this endpoint will receive the message
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageDto messageDto
    ) {
        Room room = roomService.getRoomByRoomId(messageDto.getRoomId()).getBody();
        if (room != null) {
            Message message = new Message();
            message.setSender(messageDto.getSender());
            message.setContent(messageDto.getContent());
            message.setTimestamp(LocalDateTime.now());

            room.getMessages().add(message);
            roomRepository.save(room);

            return message;
        }
        throw new RuntimeException("room not found !!");
    }
}
