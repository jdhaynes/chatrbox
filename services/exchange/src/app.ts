import { Server } from "socket.io";
import {
  PrivateMessageSentEvent,
  privateMessageSentEventSchema,
  UserConnectedToExchangeEvent,
  UserDisconnectedFromExchangeEvent,
} from "./events";
import { v4 as uuid } from "uuid";
import { eventBus, Events } from "@chatrbox/lib/src/event-bus";

interface EventMap extends Events {
  privateMessageSent: PrivateMessageSentEvent;
  userConnectedToExchange: UserConnectedToExchangeEvent;
  userDisconnectedFromExchange: UserDisconnectedFromExchangeEvent;
}

(async () => {
  const bus = await eventBus<EventMap>();

  const server = new Server();
  const exchangeId = uuid();

  server.on("connection", (socket) => {
    socket.data.userId = uuid();

    bus.publish("userConnectedToExchange", {
      connectionId: socket.id,
      eventId: uuid(),
      exchangeId: exchangeId,
      timestamp: new Date(),
      userId: socket.data.userId,
    });

    socket.on("disconnect", () => {
      bus.publish("userDisconnectedFromExchange", {
        connectionId: socket.id,
        eventId: uuid(),
        exchangeId: exchangeId,
        timestamp: new Date(),
        userId: socket.data.userId,
      });
    });

    socket.on("sendPrivateMessage", (message) => {
      const event = {
        ...JSON.parse(message),
        senderId: socket.data.userId,
        eventId: uuid(),
        timestamp: new Date(),
      };

      const validatedEvent = privateMessageSentEventSchema.parse(event);
      bus.publish("privateMessageSent", validatedEvent);

      socket.emit("privateMessageSent", {
        correlationId: event.correlationId,
        timeSent: validatedEvent.timestamp,
      });
    });
  });

  server.listen(3000);
})();
