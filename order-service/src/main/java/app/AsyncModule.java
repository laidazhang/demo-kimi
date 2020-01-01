package app;

import app.demo.api.kafka.OrderCreatedMessage;
import core.framework.module.Module;

/**
 * @author kimi
 */
public class AsyncModule extends Module {
    @Override
    protected void initialize() {
        //executor
        executor().add();
        //kafka
        kafka().publish("order-created", OrderCreatedMessage.class);
    }
}
