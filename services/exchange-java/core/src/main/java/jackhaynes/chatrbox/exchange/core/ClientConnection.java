package jackhaynes.chatrbox.exchange.core;

import java.util.UUID;

public interface ClientConnection {
    boolean isConnected();
    UUID getUserId();
}
