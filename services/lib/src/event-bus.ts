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
};

export const eventBus = <TEvents extends Events>(): EventBus<TEvents> => {
  const publish = <TEventType extends EventTypes>(
    type: TEventType,
    payload: TEvents[TEventType],
  ) => {};

  const subscribe = <TEventType extends EventTypes>(
    type: TEventType,
    callback: (payload: TEvents[TEventType]) => void,
  ) => {};

  return { publish, subscribe };
};
