package ru.foreverjun.wschat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.foreverjun.wschat.db.entity.ChatMessage;
import ru.foreverjun.wschat.db.repository.ChatMessageDao;
import ru.foreverjun.wschat.exception.ResourceNotFoundException;
import ru.foreverjun.wschat.model.ChatNotification;
import ru.foreverjun.wschat.model.MessageStatus;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatMessageService {

    private final ChatMessageDao dao;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomService chatRoomService;

    public void process(ChatMessage message) {
        String chatId = chatRoomService.getChatId(message.getSenderId(), message.getRecipientId());
        message.setChatId(chatId);
        message = dao.save(message);
        ChatNotification notification = new ChatNotification(message.getId(), message.getSenderId(), message.getSenderName());

        messagingTemplate.convertAndSendToUser(message.getRecipientId(), "/queue/messages", notification);
    }

    public long countNewMessages(String senderId, String recipientId) {
        return dao.countBySenderIdAndRecipientIdAndStatus(senderId, recipientId, MessageStatus.RECEIVED);
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        String chatId = chatRoomService.getChatId(senderId, recipientId);
        List<ChatMessage> chatMessages = dao.findByChatId(chatId);
        for (ChatMessage message: chatMessages) {
            message.setStatus(MessageStatus.DELIVERED);
        }
        return dao.saveAll(chatMessages);
    }

    public ChatMessage findById(String id) {
        ChatMessage message = dao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Message with id = " + id + " doesn't exist"));
        message.setStatus(MessageStatus.DELIVERED);
        return dao.save(message);
    }
}
