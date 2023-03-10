package jackhaynes.chatrbox.exchange.core;

import java.time.LocalDateTime;
import java.util.UUID;

public class Message {
    private UUID senderId;
    private UUID recipientId;
    private String body;
    private LocalDateTime timestampSent;
}
