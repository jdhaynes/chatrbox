package jackhaynes.chatrbox.exchange.core;

import java.util.UUID;

public class UserAlreadyConnectedException extends RuntimeException {
    private UUID userId;

    public UserAlreadyConnectedException(UUID userId) {
        super("Couldn't register new connection as connection already exists for user.");
        this.userId = userId;
    }
}
