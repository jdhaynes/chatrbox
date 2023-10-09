import amqp from "amqplib";

type Event = {
  eventId: string;
  timestamp: Date;
};

type EventName = string;
export type Events = Record<EventName, Event>;
type EventTypes = keyof Events;

interface EventBus<TEvents extends Events> {
  publish<TEventType extends EventTypes>(
    type: TEventType,
    payload: TEvents[TEventType],
  ): void;

  subscribe<TEventType extends EventTypes>(
    type: TEventType,
    callback: (payload: TEvents[TEventType]) => void,
  ): void;
}

type EventBusOptions = {
  host: string;
  service: string;
};

export const eventBus = async <TEvents extends Events>(
  options: EventBusOptions,
): Promise<EventBus<TEvents>> => {
  const connection = await amqp.connect(options.host);
  const channel = await connection.createChannel();

  const publish = <TEventType extends EventTypes>(
    type: TEventType,
    payload: TEvents[TEventType],
  ) => {
    console.log(payload);
    const exchange = type;
    const routingKey = options.service;

    channel.assertExchange(exchange, "fanout");
    channel.publish(exchange, routingKey, Buffer.from(JSON.stringify(payload)));
  };

  const subscribe = <TEventType extends EventTypes>(
    type: TEventType,
    callback: (payload: TEvents[TEventType]) => void,
  ) => {};

  return { publish, subscribe };
};
