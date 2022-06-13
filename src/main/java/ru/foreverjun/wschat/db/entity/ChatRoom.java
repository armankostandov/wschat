package ru.foreverjun.wschat.db.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class ChatRoom {
    @Id
    String id;
    String chatId;
    String senderId;
    String recipientId;
}
