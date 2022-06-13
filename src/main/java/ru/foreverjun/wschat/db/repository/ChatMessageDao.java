package ru.foreverjun.wschat.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.foreverjun.wschat.db.entity.ChatMessage;

public interface ChatMessageDao extends JpaRepository<ChatMessage, String> {
}
