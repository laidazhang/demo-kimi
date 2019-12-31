package app.demo.kafka;

import app.demo.api.kafka.OrderCreatedMessage;
import core.framework.json.JSON;
import core.framework.kafka.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author kimi
 */
public class OrderCreatedMessageHandler implements MessageHandler<OrderCreatedMessage> {
    private final Logger logger = LoggerFactory.getLogger(OrderCreatedMessageHandler.class);

    @Override
    public void handle(String key, OrderCreatedMessage value) throws Exception {
        logger.info("order created message handler, key={}, value={}", key, JSON.toJSON(value));
    }
}
