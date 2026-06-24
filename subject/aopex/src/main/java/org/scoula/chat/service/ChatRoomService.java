package org.scoula.chat.service;

import org.scoula.chat.domain.ChatRoom;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ChatRoomService {

    private final Map<String, ChatRoom> rooms = new ConcurrentHashMap<>();
    private final AtomicLong idSeq = new AtomicLong(1);

    public ChatRoom createRoom(String name) {
        String id = String.valueOf(idSeq.getAndIncrement());
        ChatRoom room = new ChatRoom(id, name);
        rooms.put(id, room);
        return room;
    }

    public List<ChatRoom> getRooms() {
        return new ArrayList<>(rooms.values());
    }

    public ChatRoom getRoom(String id) {
        return rooms.get(id);
    }
}
