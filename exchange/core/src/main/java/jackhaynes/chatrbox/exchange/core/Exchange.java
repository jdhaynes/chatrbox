package jackhaynes.chatrbox.exchange.core;

import jackhaynes.chatrbox.exchange.privatemsg.model.OutgoingPrivateMessage;

public interface Exchange {
    public void sendMessage(OutgoingPrivateMessage message);
}
