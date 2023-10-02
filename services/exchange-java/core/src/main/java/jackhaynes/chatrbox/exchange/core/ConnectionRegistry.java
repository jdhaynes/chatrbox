package jackhaynes.chatrbox.exchange.core;

import java.util.UUID;

public interface ConnectionRegistry {
    void register(ClientConnection connection);
    void deregister(ClientConnection connection);
    ClientConnection getUserConnection(UUID userId);
    int size();
}
