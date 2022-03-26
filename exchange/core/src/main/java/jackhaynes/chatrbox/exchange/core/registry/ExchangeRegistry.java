package jackhaynes.chatrbox.exchange.core.registry;

import jackhaynes.chatrbox.exchange.core.Exchange;

import java.util.Collection;

public interface ExchangeRegistry {
    public Collection<Exchange> getActiveUserExchanges(String userId);
}
