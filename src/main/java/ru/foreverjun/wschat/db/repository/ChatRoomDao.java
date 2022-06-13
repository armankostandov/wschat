package ru.foreverjun.wschat.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.foreverjun.wschat.db.entity.ChatRoom;

import java.util.Optional;

@Repository
public interface ChatRoomDao extends JpaRepository<ChatRoom, String> {

    Optional<ChatRoom> findFirstBySenderIdAndRecipientId(String SenderId, String recipientId);
}
