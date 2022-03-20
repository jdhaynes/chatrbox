package jackhaynes.chatrbox.web.endpoints;

import jackhaynes.chatrbox.web.WebSocketPool;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class ServerWebSocketHandler extends TextWebSocketHandler  {
    private final WebSocketPool socketPool;

    public ServerWebSocketHandler(WebSocketPool socketPool) {
        this.socketPool = socketPool;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        socketPool.registerSocket(session);

        String message = session.getId() + " | Successfully connected.";
        session.sendMessage(new TextMessage(message));

        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println(session.getId() + " | Received message through websocket: " + message.getPayload());
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        socketPool.deregisterSocket(session.getId());
        super.afterConnectionClosed(session, status);
    }
}
