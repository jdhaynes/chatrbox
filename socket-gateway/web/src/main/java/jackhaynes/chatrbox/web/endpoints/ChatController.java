package jackhaynes.chatrbox.web.endpoints;

import jackhaynes.chatrbox.web.WebSocketPool;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ChatController {
    private final WebSocketPool socketPool;

    public ChatController(WebSocketPool socketPool) {
        this.socketPool = socketPool;
    }

    @PostMapping("/chat/{recipientId}")
    public String sendMessage(@PathVariable String recipientId, @RequestBody String message) throws IOException {
        socketPool.sendMessageToSocket(recipientId, message);
        return "Message sent!";
    }
}
