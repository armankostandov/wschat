package ru.foreverjun.wschat.db.entity;


import lombok.Data;
import ru.foreverjun.wschat.model.MessageStatus;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;

@Data
@Entity
public class ChatMessage {
    @Id
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private Instant timestamp;
    private MessageStatus status;
}
