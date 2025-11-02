package com.aman.synctalk_backend.entities;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "rooms")
public class Room {
    @Id
    private String id; // MongoDB : unique identifier
    private String roomId;
    private String name;
    private String description;

    private List<Message> messages = new ArrayList<>();
}
