package app;

import app.demo.api.kafka.OrderCreatedMessage;
import app.demo.kafka.BulkOrderCreatedMessageHandler;
import core.framework.module.Module;

/**
 * @author kimi
 */
public class AsyncModule extends Module {
    @Override
    protected void initialize() {
        //kafka
        //kafka().subscribe("order-created", OrderCreatedMessage.class, bind(OrderCreatedMessageHandler.class));
        kafka().subscribe("order-created", OrderCreatedMessage.class, bind(BulkOrderCreatedMessageHandler.class));
    }
}
