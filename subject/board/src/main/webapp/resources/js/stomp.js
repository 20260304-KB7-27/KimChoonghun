let stompClient = null;
let roomSubscription = null;
let currentRoomId = null;

const stompClientConfig = {
    brokerURL: (location.protocol === 'https:' ? 'wss:' : 'ws:') + '//' + location.host + '/chat-app',
    reconnectDelay: 5000,
};

function getNickname() {
    return document.getElementById('nickname').value.trim();
}

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('createRoom').disabled = !connected;
    document.getElementById('nickname').disabled = connected;

    if (!connected) {
        leaveCurrentRoom(false);
        document.getElementById('roomList').innerHTML =
            '<li class="list-group-item text-muted">연결 후 대화방이 표시됩니다.</li>';
    }
}

function connect() {
    const nickname = getNickname();
    if (!nickname) {
        alert('닉네임을 입력하세요.');
        return;
    }

    stompClient = new StompJs.Client(stompClientConfig);

    stompClient.onWebSocketError = (error) => {
        console.error('WebSocket error', error);
    };

    stompClient.onStompError = (frame) => {
        console.error('STOMP error', frame.headers['message'], frame.body);
    };

    stompClient.onConnect = () => {
        setConnected(true);

        stompClient.subscribe('/topic/rooms', (message) => {
            renderRoomList(JSON.parse(message.body));
        });

        loadRooms();
    };

    stompClient.activate();
}

function disconnect() {
    if (stompClient) {
        leaveCurrentRoom(false);
        stompClient.deactivate();
        stompClient = null;
    }
    setConnected(false);
}

function loadRooms() {
    fetch('/api/chat/rooms')
        .then((res) => res.json())
        .then((response) => {
            if (response.success) {
                renderRoomList(response.data);
            }
        });
}

function renderRoomList(rooms) {
    const roomList = document.getElementById('roomList');
    roomList.innerHTML = '';

    if (!rooms || rooms.length === 0) {
        roomList.innerHTML = '<li class="list-group-item text-muted">개설된 대화방이 없습니다.</li>';
        return;
    }

    rooms.forEach((room) => {
        const item = document.createElement('li');
        item.className = 'list-group-item list-group-item-action';
        if (room.id === currentRoomId) {
            item.classList.add('active');
        }
        item.textContent = room.name;
        item.addEventListener('click', () => enterRoom(room));
        roomList.appendChild(item);
    });
}

function createRoom() {
    const name = document.getElementById('roomName').value.trim();
    if (!name) {
        alert('대화방 이름을 입력하세요.');
        return;
    }

    if (stompClient && stompClient.connected) {
        stompClient.publish({
            destination: '/app/room.create',
            body: JSON.stringify({ name }),
        });
        document.getElementById('roomName').value = '';
        return;
    }

    fetch('/api/chat/rooms', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name }),
    })
        .then((res) => res.json())
        .then((response) => {
            if (response.success) {
                document.getElementById('roomName').value = '';
                loadRooms();
            }
        });
}

function enterRoom(room) {
    if (currentRoomId === room.id) {
        return;
    }

    leaveCurrentRoom(false);
    currentRoomId = room.id;

    document.getElementById('currentRoomTitle').innerHTML =
        '<i class="fas fa-comment-dots"></i> ' + room.name;
    document.getElementById('chatMessages').innerHTML = '';
    document.getElementById('message').disabled = false;
    document.getElementById('send').disabled = false;
    document.getElementById('leaveRoom').disabled = false;

    roomSubscription = stompClient.subscribe('/topic/room.' + room.id, (message) => {
        showMessage(JSON.parse(message.body));
    });

    stompClient.publish({
        destination: '/app/room.enter',
        body: JSON.stringify({
            roomId: room.id,
            name: getNickname(),
        }),
    });

    renderRoomListFromActive();
}

function renderRoomListFromActive() {
    fetch('/api/chat/rooms')
        .then((res) => res.json())
        .then((response) => {
            if (response.success) {
                renderRoomList(response.data);
            }
        });
}

function leaveCurrentRoom(notify) {
    if (!currentRoomId) {
        return;
    }

    if (notify && stompClient && stompClient.connected) {
        stompClient.publish({
            destination: '/app/room.leave',
            body: JSON.stringify({
                roomId: currentRoomId,
                name: getNickname(),
            }),
        });
    }

    if (roomSubscription) {
        roomSubscription.unsubscribe();
        roomSubscription = null;
    }

    currentRoomId = null;
    document.getElementById('currentRoomTitle').innerHTML =
        '<i class="fas fa-comment-dots"></i> 대화방을 선택하세요';
    document.getElementById('chatMessages').innerHTML =
        '<p class="text-muted">대화방에 입장하면 메시지가 표시됩니다.</p>';
    document.getElementById('message').disabled = true;
    document.getElementById('send').disabled = true;
    document.getElementById('leaveRoom').disabled = true;
    document.getElementById('message').value = '';
}

function sendMessage() {
    const content = document.getElementById('message').value.trim();
    if (!content || !currentRoomId) {
        return;
    }

    stompClient.publish({
        destination: '/app/room.chat',
        body: JSON.stringify({
            roomId: currentRoomId,
            name: getNickname(),
            content,
        }),
    });

    document.getElementById('message').value = '';
}

function showMessage(message) {
    const container = document.getElementById('chatMessages');
    const line = document.createElement('div');
    line.className = 'mb-2';

    if (message.type === 'JOIN') {
        line.innerHTML = '<span class="text-success"><i class="fas fa-sign-in-alt"></i> ' +
            message.name + '님이 입장했습니다.</span>';
    } else if (message.type === 'LEAVE') {
        line.innerHTML = '<span class="text-danger"><i class="fas fa-sign-out-alt"></i> ' +
            message.name + '님이 나갔습니다.</span>';
    } else {
        line.innerHTML = '<strong>' + message.name + '</strong>: ' + message.content;
    }

    container.appendChild(line);
    container.scrollTop = container.scrollHeight;
}

window.addEventListener('DOMContentLoaded', () => {
    document.getElementById('connect').addEventListener('click', (e) => {
        e.preventDefault();
        connect();
    });
    document.getElementById('disconnect').addEventListener('click', (e) => {
        e.preventDefault();
        disconnect();
    });
    document.getElementById('createRoom').addEventListener('click', (e) => {
        e.preventDefault();
        createRoom();
    });
    document.getElementById('leaveRoom').addEventListener('click', (e) => {
        e.preventDefault();
        leaveCurrentRoom(true);
        renderRoomListFromActive();
    });
    document.getElementById('send').addEventListener('click', (e) => {
        e.preventDefault();
        sendMessage();
    });
    document.getElementById('message').addEventListener('keydown', (e) => {
        if (e.key === 'Enter') {
            e.preventDefault();
            sendMessage();
        }
    });
});
