package jackhaynes.chatrbox.exchange.privatemsg;

import jackhaynes.chatrbox.exchange.privatemsg.model.OutgoingPrivateMessage;

public interface RecipientServer {
    public void sendPrivateMessage(OutgoingPrivateMessage message);
}
