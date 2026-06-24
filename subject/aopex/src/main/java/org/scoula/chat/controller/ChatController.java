package org.scoula.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.chat.domain.ChatMessage;
import org.scoula.chat.domain.ChatRoom;
import org.scoula.chat.service.ChatRoomService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomService chatRoomService;

    @MessageMapping("/room.create")
    public void createRoom(ChatRoom room) {
        log.info("대화방 개설: {}", room.getName());
        chatRoomService.createRoom(room.getName());
        messagingTemplate.convertAndSend("/topic/rooms", chatRoomService.getRooms());
    }

    @MessageMapping("/room.enter")
    public void enter(ChatMessage message) {
        log.info("{} 님이 {}번 방 입장", message.getName(), message.getRoomId());
        ChatMessage join = new ChatMessage(message.getRoomId(), message.getName(), null, "JOIN");
        messagingTemplate.convertAndSend("/topic/room." + message.getRoomId(), join);
    }

    @MessageMapping("/room.chat")
    public void chat(ChatMessage message) {
        message.setType("CHAT");
        messagingTemplate.convertAndSend("/topic/room." + message.getRoomId(), message);
    }

    @MessageMapping("/room.leave")
    public void leave(ChatMessage message) {
        log.info("{} 님이 {}번 방 퇴장", message.getName(), message.getRoomId());
        ChatMessage leave = new ChatMessage(message.getRoomId(), message.getName(), null, "LEAVE");
        messagingTemplate.convertAndSend("/topic/room." + message.getRoomId(), leave);
    }
}
