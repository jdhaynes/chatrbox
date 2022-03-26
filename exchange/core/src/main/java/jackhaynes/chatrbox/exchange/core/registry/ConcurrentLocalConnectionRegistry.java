package jackhaynes.chatrbox.exchange.core.registry;

import jackhaynes.chatrbox.exchange.core.connection.ClientExchangeConnection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentLocalConnectionRegistry implements LocalConnectionRegistry {
    private final Map<String, ClientExchangeConnection> connections;

    public ConcurrentLocalConnectionRegistry() {
        connections = new ConcurrentHashMap<>();
    }

    @Override
    public void register(String connectionId, ClientExchangeConnection connection) {
        if(connectionId == null || connectionId.trim().isEmpty()) {
            throw new IllegalArgumentException("Connection ID must be specified");
        }

        if(connection == null) {
            throw new IllegalArgumentException("Connection cannot be null");
        }

        connections.put(connectionId, connection);
    }

    @Override
    public void deregister(String connectionId) {
        if(!connections.containsKey(connectionId)) {
            throw new IllegalArgumentException("Connection does not exist in registry");
        }

        connections.remove(connectionId);
    }

    @Override
    public ClientExchangeConnection getConnection(String connectionId) {
        if(!connections.containsKey(connectionId)) {
            throw new IllegalArgumentException("Session does not exist in registry");
        }

        return connections.get(connectionId);
    }
}
