package com.aman.synctalk_backend.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private String sender;
    private String content;
    private String roomId;
}
