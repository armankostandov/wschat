package ru.foreverjun.wschat.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Data
@Entity
@Table(name = "chat_rooms")
public class ChatRoom {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    String id;
    String chatId;
    String senderId;
    String recipientId;

    public ChatRoom(String chatId, String senderId, String recipientId) {
        this.chatId = chatId;
        this.senderId = senderId;
        this.recipientId = recipientId;
    }
}
