package ru.foreverjun.wschat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import ru.foreverjun.wschat.db.entity.ChatMessage;
import ru.foreverjun.wschat.service.ChatMessageService;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(@Payload ChatMessage message) throws ChangeSetPersister.NotFoundException {
        chatMessageService.process(message);
    }
}
