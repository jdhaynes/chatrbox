import { z } from "zod";

export const privateMessageSentEventSchema = z.object({
  eventId: z.string().uuid(),
  timestamp: z.date(),
  senderId: z.string().uuid(),
  recipientId: z.string().uuid(),
  body: z.string().max(1000),
});

export type PrivateMessageSentEvent = z.infer<
  typeof privateMessageSentEventSchema
>;

export const userConnectedToExchangeEventSchema = z.object({
  eventId: z.string().uuid(),
  timestamp: z.date(),
  userId: z.string().uuid(),
  exchangeId: z.string(),
  connectionId: z.string(),
});

export type UserConnectedToExchangeEvent = z.infer<
  typeof userConnectedToExchangeEventSchema
>;

export const userDisconnectedFromExchangeEventSchema = z.object({
  eventId: z.string().uuid(),
  timestamp: z.date(),
  userId: z.string().uuid(),
  exchangeId: z.string(),
  connectionId: z.string(),
});

export type UserDisconnectedFromExchangeEvent = z.infer<
  typeof userDisconnectedFromExchangeEventSchema
>;
