package ru.foreverjun.wschat.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ChatNotification {
    private String id;
    private String senderId;
    private String senderName;
}
