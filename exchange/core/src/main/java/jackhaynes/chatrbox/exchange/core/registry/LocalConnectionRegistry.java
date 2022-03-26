package jackhaynes.chatrbox.exchange.core.registry;

import jackhaynes.chatrbox.exchange.core.connection.ClientExchangeConnection;

public interface LocalConnectionRegistry {
    public void register(String connectionId, ClientExchangeConnection connection);
    public void deregister(String connectionId);
    public ClientExchangeConnection getConnection(String connectionId);
}
