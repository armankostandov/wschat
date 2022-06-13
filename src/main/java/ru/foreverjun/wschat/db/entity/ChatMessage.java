package ru.foreverjun.wschat.db.entity;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import ru.foreverjun.wschat.model.MessageStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "chat_messages")
public class ChatMessage {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private LocalDateTime datetime;
    @Enumerated(EnumType.STRING)
    private MessageStatus status;
}
