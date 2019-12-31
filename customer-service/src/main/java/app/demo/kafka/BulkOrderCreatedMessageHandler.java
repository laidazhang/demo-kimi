package app.demo.kafka;

import app.demo.api.kafka.OrderCreatedMessage;
import core.framework.json.JSON;
import core.framework.kafka.BulkMessageHandler;
import core.framework.kafka.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author kimi
 */
public class BulkOrderCreatedMessageHandler implements BulkMessageHandler<OrderCreatedMessage> {
    private final Logger logger = LoggerFactory.getLogger(BulkOrderCreatedMessageHandler.class);

    @Override
    public void handle(List<Message<OrderCreatedMessage>> messages) throws Exception {
        messages.forEach(message -> logger.info("bulk order created message handler, key={}, value={}", message.key, JSON.toJSON(message.value)));
    }
}
