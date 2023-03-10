package jackhaynes.chatrbox.exchange.core;

import java.util.UUID;

public class SpringSocketClientConnection implements ClientConnection{
    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public UUID getUserId() {
        return null;
    }
}
