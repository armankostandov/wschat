package ru.foreverjun.wschat.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.foreverjun.wschat.db.entity.ChatMessage;
import ru.foreverjun.wschat.model.MessageStatus;

import java.util.List;

public interface ChatMessageDao extends JpaRepository<ChatMessage, String> {

    long countBySenderIdAndRecipientIdAndStatus(String senderId, String recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(String chatId);

}
