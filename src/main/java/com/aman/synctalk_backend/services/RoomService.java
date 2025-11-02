package com.aman.synctalk_backend.services;
import com.aman.synctalk_backend.dto.RoomDto;
import com.aman.synctalk_backend.entities.Room;
import com.aman.synctalk_backend.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;


    public ResponseEntity<Room> createRoom(RoomDto roomDto) {
        String roomId = roomDto.getRoomId();

        Room existingRoom = roomRepository.findByRoomId(roomId);
        if (existingRoom != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Room newRoom = new Room();
        newRoom.setRoomId(roomId);
        newRoom.setName(roomDto.getRoomName());
        newRoom.setDescription(roomDto.getRoomDescription());
        roomRepository.save(newRoom);
        return new ResponseEntity<>(newRoom, HttpStatus.CREATED);
    }

    public ResponseEntity<Room> getRoomByRoomId(String roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        if (room != null) {
            return new ResponseEntity<>(room, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteRoomByRoomId(String roomId) {
        Room room = roomRepository.findByRoomId(roomId);
        if (room != null) {
            roomRepository.delete(room);
            return new ResponseEntity<>("Room deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Room not found", HttpStatus.NOT_FOUND);
        }
    }
}
