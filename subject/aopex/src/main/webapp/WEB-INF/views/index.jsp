<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hello WebSocket</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@stomp/stompjs@7.0.0/bundles/stomp.umd.min.js"></script>
</head>
<body>
    <div id="main-content" class="container">
    <h1 class="page-header my-4"><i class="fas fa-comments"></i> STOMP 채팅</h1>
    <div class="row mb-3">
        <div class="col-md-4">
            <label class="form-label">닉네임</label>
            <input type="text" id="nickname" class="form-control" placeholder="이름을 입력하세요">
        </div>
        <div class="col-md-4 d-flex align-items-end gap-2">
            <button id="connect" class="btn btn-primary"><i class="fas fa-plug"></i> 연결</button>
            <button id="disconnect" class="btn btn-secondary" disabled><i class="fas fa-unlink"></i> 끊기</button>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <span><i class="fas fa-door-open"></i> 대화방 목록</span>
                </div>
                <div class="card-body">
                    <div class="input-group mb-3">
                        <input type="text" id="roomName" class="form-control" placeholder="새 대화방 이름">
                        <button id="createRoom" class="btn btn-success" disabled>개설</button>
                    </div>
                    <ul id="roomList" class="list-group list-group-flush">
                        <li class="list-group-item text-muted">연결 후 대화방이 표시됩니다.</li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <div class="card">
                <div class="card-header d-flex justify-content-between align-items-center">
                    <span id="currentRoomTitle"><i class="fas fa-comment-dots"></i> 대화방을 선택하세요</span>
                    <button id="leaveRoom" class="btn btn-sm btn-outline-danger" disabled>나가기</button>
                </div>
                <div class="card-body" style="height: 360px; overflow-y: auto;" id="chatMessages">
                    <p class="text-muted">대화방에 입장하면 메시지가 표시됩니다.</p>
                </div>
                <div class="card-footer">
                    <div class="input-group">
                        <input type="text" id="message" class="form-control" placeholder="메시지를 입력하세요" disabled>
                        <button id="send" class="btn btn-primary" disabled>전송</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
</body>
<script src="/resources/js/stomp.js"></script>
</html>