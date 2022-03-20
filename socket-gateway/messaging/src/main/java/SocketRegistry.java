import java.util.HashMap;
import java.util.UUID;

public class SocketRegistry<T> {
    private final HashMap<UUID, T> sockets;

    public SocketRegistry() {
        this.sockets = new HashMap<UUID, T>();
    }

    public boolean socketExists(UUID id) {
        return sockets.containsKey(id);
    }

    public UUID registerSocket(T socket) {
        UUID id = generateSocketId();
        sockets.put(id, socket);

        return id;
    }

    public void deregisterSocket(UUID id) {
        sockets.remove(id);
    }

    private UUID generateSocketId() {
        return UUID.randomUUID();
    }
}
