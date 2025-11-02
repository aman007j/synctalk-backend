package com.aman.synctalk_backend.controllers;

import com.aman.synctalk_backend.dto.RoomDto;
import com.aman.synctalk_backend.entities.Message;
import com.aman.synctalk_backend.entities.Room;
import com.aman.synctalk_backend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody RoomDto roomDto) {
        return roomService.createRoom(roomDto);
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<Room> getRoom(@PathVariable String roomId) {
        return roomService.getRoomByRoomId(roomId);
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable String roomId,
            @RequestParam(value ="page", defaultValue = "0", required = false) int page,
            @RequestParam(value ="size", defaultValue = "20", required = false) int size
    ) {
        return roomService.getMessagesByRoomId(roomId, page, size);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<String> deleteRoom(@PathVariable String roomId) {
        return roomService.deleteRoomByRoomId(roomId);
    }
}
