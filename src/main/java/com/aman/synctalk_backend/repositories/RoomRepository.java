package com.aman.synctalk_backend.repositories;

import com.aman.synctalk_backend.entities.Room;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room, String> {
    Room findByRoomId(String roomId); // through this user will find the room
}
