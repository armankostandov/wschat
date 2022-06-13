package ru.foreverjun.wschat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.foreverjun.wschat.db.entity.ChatRoom;
import ru.foreverjun.wschat.db.repository.ChatRoomDao;
import ru.foreverjun.wschat.exception.ResourceNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomDao dao;

    public String getChatId(String senderId, String recipientId) {
        try {
            ChatRoom chatRoom = dao.findFirstBySenderIdAndRecipientId(senderId, recipientId)
                    .orElseThrow(() -> new ResourceNotFoundException("chatRoom doesn't exist"));
            return chatRoom.getChatId();
        } catch (ResourceNotFoundException e) {
            return createChat(senderId, recipientId);
        }
    }

    private String createChat(String senderId, String recipientId) {
        String chatId = generateChatId(senderId, recipientId);
        ChatRoom room1 = new ChatRoom(chatId, senderId, recipientId);
        ChatRoom room2 = new ChatRoom(chatId, recipientId, senderId);
        dao.save(room1);
        dao.save(room2);
        return chatId;
    }

    public String generateChatId(String senderId, String recipientId) {
        return Stream.of(senderId, recipientId)
                .sorted()
                .collect(Collectors.joining("-"));
    }
}
