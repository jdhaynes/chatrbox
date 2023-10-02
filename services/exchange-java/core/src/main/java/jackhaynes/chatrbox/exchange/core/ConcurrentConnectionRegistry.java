package jackhaynes.chatrbox.exchange.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentConnectionRegistry implements ConnectionRegistry {
    private ConcurrentMap<UUID, ClientConnection> connections;

    public ConcurrentConnectionRegistry() {
        this.connections = new ConcurrentHashMap<UUID, ClientConnection>();
    }

    @Override
    public void register(ClientConnection connection) {
        if(this.connections.containsKey(connection.getUserId())) {
            throw new UserAlreadyConnectedException(connection.getUserId());
        }

        this.connections.put(connection.getUserId(), connection);
    }

    @Override
    public void deregister(ClientConnection connection) {

    }

    public int size() {
        return connections.size();
    }

    @Override
    public ClientConnection getUserConnection(UUID userId) {
        return connections.getOrDefault(userId, null);
    }
}
