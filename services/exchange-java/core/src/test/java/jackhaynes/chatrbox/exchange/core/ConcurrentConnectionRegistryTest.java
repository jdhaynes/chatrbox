package jackhaynes.chatrbox.exchange.core;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ConcurrentConnectionRegistryTest {
    private class ClientConnectionStub implements ClientConnection {
        private UUID id;

        public ClientConnectionStub(UUID userId) {
            this.id = userId;
        }

        @Override
        public boolean isConnected() {
            return false;
        }

        @Override
        public UUID getUserId() {
            return id;
        }
    }

    @Test
    public void givenEmptyRegister_whenGetConnection_shouldReturnNull() {
        ConnectionRegistry registry = new ConcurrentConnectionRegistry();
        UUID userId = UUID.fromString("0374ed44-fafa-4b70-a5e8-85c6ba4c6e32");

        ClientConnection result = registry.getUserConnection(userId);

        assertNull(result);
    }

    @Test
    public void givenEmptyRegister_whenGetUserCount_shouldReturnZero() {
        ConnectionRegistry registry = new ConcurrentConnectionRegistry();
        assertEquals(0, registry.size());
    }

    @Test
    public void givenEmptyRegister_whenAddConnection_shouldIncreaseSize() {
        ConnectionRegistry registry = new ConcurrentConnectionRegistry();
        UUID userId = UUID.fromString("0374ed44-fafa-4b70-a5e8-85c6ba4c6e32");

        registry.register(new ClientConnectionStub(userId));

        assertEquals(1, registry.size());
    }

    @Test
    public void givenContainsUserConn_whenGetUserConn_shouldReturnCorrectConn() {
        ConnectionRegistry registry = new ConcurrentConnectionRegistry();
        UUID userId1 = UUID.fromString("0374ed44-fafa-4b70-a5e8-85c6ba4c6e32");
        UUID userId2 = UUID.fromString("7a075354-c415-489b-bc67-64dace31095c");
        ClientConnection conn1 = new ClientConnectionStub(userId1);
        ClientConnection conn2 = new ClientConnectionStub(userId2);

        registry.register(conn1);
        registry.register(conn2);
        ClientConnection result = registry.getUserConnection(userId2);

        assertEquals(conn2, result);
    }

    @Test
    public void givenExistingUserConn_whenRegisterConnSameUser_shouldThrowException() {
        ConnectionRegistry registry = new ConcurrentConnectionRegistry();
        UUID userId1 = UUID.fromString("0374ed44-fafa-4b70-a5e8-85c6ba4c6e32");
        ClientConnection conn1 = new ClientConnectionStub(userId1);
        ClientConnection conn2 = new ClientConnectionStub(userId1);

        registry.register(conn1);

        assertThrows(UserAlreadyConnectedException.class,
                () -> registry.register(conn2));
    }
}