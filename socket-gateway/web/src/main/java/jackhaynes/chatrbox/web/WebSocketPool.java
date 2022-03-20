package jackhaynes.chatrbox.web;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;

public class WebSocketPool {
    private final HashMap<String, WebSocketSession> sockets;

    public WebSocketPool() {
        this.sockets = new HashMap<>();
    }

    public void registerSocket(WebSocketSession socket) {
        sockets.put(socket.getId(), socket);
    }

    public void deregisterSocket(String id) {
        sockets.remove(id);
    }

    public void sendMessageToSocket(String socketId, String payload) throws IOException {
        WebSocketSession socket = sockets.get(socketId);
        socket.sendMessage(new TextMessage(payload));
    }
}
