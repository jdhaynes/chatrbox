package jackhaynes.chatrbox.exchange.privatemsg;

public interface SendPrivateMessageService {
    public void sendMessage(String senderId, String recipientId, String body);
}
