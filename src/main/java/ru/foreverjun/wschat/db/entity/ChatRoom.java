package ru.foreverjun.wschat.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Data
@Entity
public class ChatRoom {
    @Id
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
