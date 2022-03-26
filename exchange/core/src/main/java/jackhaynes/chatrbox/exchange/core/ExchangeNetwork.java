package jackhaynes.chatrbox.exchange.core;

import jackhaynes.chatrbox.exchange.core.registry.ExchangeRegistry;
import jackhaynes.chatrbox.exchange.privatemsg.model.OutgoingPrivateMessage;

import java.util.Collection;

public class ExchangeNetwork implements Exchange {
    private ExchangeRegistry exchangeRegistry;

    public ExchangeNetwork(ExchangeRegistry exchangeRegistry) {
        this.exchangeRegistry = exchangeRegistry;
    }

    @Override
    public void sendMessage(OutgoingPrivateMessage message) {
        Collection<Exchange> recipientExchanges = exchangeRegistry.getActiveUserExchanges(message.getRecipientId());
        for(Exchange exchange : recipientExchanges) {
            exchange.sendMessage(message);
        }
    }
}
