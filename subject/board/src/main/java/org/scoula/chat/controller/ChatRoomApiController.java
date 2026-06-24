package org.scoula.chat.controller;

import lombok.RequiredArgsConstructor;
import org.scoula.chat.domain.ChatRoom;
import org.scoula.chat.service.ChatRoomService;
import org.scoula.common.util.dto.ApiResponse;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatRoomApiController {

    private final ChatRoomService chatRoomService;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/rooms")
    public ApiResponse<List<ChatRoom>> getRooms() {
        return ApiResponse.ok(chatRoomService.getRooms());
    }

    @PostMapping("/rooms")
    public ApiResponse<ChatRoom> createRoom(@RequestBody ChatRoom room) {
        ChatRoom created = chatRoomService.createRoom(room.getName());
        messagingTemplate.convertAndSend("/topic/rooms", chatRoomService.getRooms());
        return ApiResponse.ok(created);
    }
}
