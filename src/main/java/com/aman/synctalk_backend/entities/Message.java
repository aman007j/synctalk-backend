package com.aman.synctalk_backend.entities;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    private String sender;
    private String content;
    private LocalDateTime timestamp;

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }
}
