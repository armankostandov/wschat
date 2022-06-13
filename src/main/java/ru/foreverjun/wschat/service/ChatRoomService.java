package ru.foreverjun.wschat.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.foreverjun.wschat.db.entity.ChatRoom;
import ru.foreverjun.wschat.db.repository.ChatRoomDao;

@RequiredArgsConstructor
@Service
public class ChatRoomService {

    private final ChatRoomDao dao;

    public String getChatId(String senderId, String recipientId) throws ChangeSetPersister.NotFoundException {
        ChatRoom chatRoom = dao.findFirstBySenderIdAndRecipientId(senderId, recipientId)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);
        return chatRoom.getChatId();
    }
}
