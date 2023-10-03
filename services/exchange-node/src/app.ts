import { Server } from "socket.io";
import {
  PrivateMessageSentEvent,
  sendPrivateMessageEventSchema,
} from "./events/application/out/send-private-message";
import { eventBus, Events } from "./event-bus";
import { v4 as uuid } from "uuid";

// export interface ClientToServerEvents {
//   sendPrivateMessage: ({
//     senderId,
//     recipientId,
//     body,
//   }: SendPrivateMessageEvent) => void;
// }
//
// export interface ServerToClientEvents {}
//
// export interface ServerToServerEvents {}
//
// export interface SocketData {
//   userId: string;
// }

interface EventMap extends Events {
  privateMessageSent: PrivateMessageSentEvent;
}

const bus = eventBus<EventMap>();

const server = new Server();

server.on("connection", (socket) => {
  console.log(`Client connected (${socket.id})`);
  socket.data.userId = uuid();

  socket.on("disconnect", () => {
    console.log(`Client disconnected (${socket.id})`);
  });

  socket.on("sendPrivateMessage", (message) => {
    const event = {
      ...JSON.parse(message),
      senderId: socket.data.userId,
      eventId: uuid(),
      timestamp: new Date(),
    };

    const validatedEvent = sendPrivateMessageEventSchema.parse(event);
    bus.publish("privateMessageSent", validatedEvent);

    socket.emit("privateMessageSent", {
      eventId: event.eventId,
      timeSent: validatedEvent.timestamp,
    });
  });
});

server.listen(3000);
