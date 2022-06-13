package ru.foreverjun.wschat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.foreverjun.wschat.db.entity.ChatMessage;
import ru.foreverjun.wschat.db.repository.ChatMessageDao;
import ru.foreverjun.wschat.model.ChatNotification;

@RequiredArgsConstructor
@Service
public class ChatMessageService {

    private final ChatMessageDao dao;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomService chatRoomService;

    public void process(ChatMessage message) throws ChangeSetPersister.NotFoundException {
        String chatId = chatRoomService.getChatId(message.getSenderId(), message.getRecipientId());
        message.setChatId(chatId);
        message = dao.save(message);
        ChatNotification notification = new ChatNotification(message.getId(), message.getSenderId(), message.getSenderName());

        messagingTemplate.convertAndSendToUser(message.getRecipientId(),"/queue/messages", notification);
    }
}
