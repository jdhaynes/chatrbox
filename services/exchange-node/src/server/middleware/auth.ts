import { Socket } from "socket.io";

export const authorizeConnection = (socket: Socket, next: any) => {
  const token = socket.handshake.headers.authorization;
  if (token == "password") {
    socket.data.userId = "d41ee669-f222-4924-8cd9-29504ab75c5d";
    next();
  } else {
    next(new Error("Invalid credentials"));
  }
};
