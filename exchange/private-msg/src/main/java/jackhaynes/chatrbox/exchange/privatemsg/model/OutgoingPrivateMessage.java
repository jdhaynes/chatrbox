package jackhaynes.chatrbox.exchange.privatemsg.model;

import java.time.Instant;
import java.util.Objects;

public class OutgoingPrivateMessage {
    private String senderId;
    private String recipientId;
    private String body;
    private Instant timestampSent;

    public OutgoingPrivateMessage(String senderId, String recipientId, String body, Instant timestampSent) {
        this.senderId = senderId;
        this.recipientId = recipientId;
        this.body = body;
        this.timestampSent = timestampSent;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public String getBody() {
        return body;
    }

    public Instant getTimestampSent() {
        return timestampSent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutgoingPrivateMessage that = (OutgoingPrivateMessage) o;
        return Objects.equals(senderId, that.senderId) && Objects.equals(recipientId, that.recipientId) && Objects.equals(body, that.body) && Objects.equals(timestampSent, that.timestampSent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(senderId, recipientId, body, timestampSent);
    }
}
