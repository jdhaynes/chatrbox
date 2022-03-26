package jackhaynes.chatrbox.exchange.privatemsg;

import jackhaynes.chatrbox.exchange.core.Exchange;
import jackhaynes.chatrbox.exchange.core.registry.LocalConnectionRegistry;
import jackhaynes.chatrbox.exchange.privatemsg.model.OutgoingPrivateMessage;

import java.time.Instant;

public class SendPrivateMessageServiceImpl implements SendPrivateMessageService {
    private LocalConnectionRegistry connectionRegistry;
    private Exchange globalExchange;

    public SendPrivateMessageServiceImpl(LocalConnectionRegistry connectionRegistry, Exchange exchange) {
        this.connectionRegistry = connectionRegistry;
    }

    @Override
    public void sendMessage(String senderId, String recipientId, String body) {
        OutgoingPrivateMessage message = new OutgoingPrivateMessage(senderId, recipientId, body, Instant.now());
        globalExchange.sendMessage(message);
    }
}
