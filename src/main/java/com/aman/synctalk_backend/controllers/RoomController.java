package com.aman.synctalk_backend.controllers;

import com.aman.synctalk_backend.dto.RoomDto;
import com.aman.synctalk_backend.entities.Room;
import com.aman.synctalk_backend.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody RoomDto roomDto) {
        return roomService.createRoom(roomDto);
    }

    @GetMapping("/id")
    public ResponseEntity<Room> getRoom(@RequestParam String roomId) {
        return roomService.getRoomByRoomId(roomId);
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deleteRoom(@RequestParam String roomId) {
        return roomService.deleteRoomByRoomId(roomId);
    }
}
