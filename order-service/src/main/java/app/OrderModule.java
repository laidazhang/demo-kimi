package app;

import app.demo.api.BOOrderWebService;
import app.demo.api.kafka.OrderCreatedMessage;
import app.demo.order.domain.Order;
import app.demo.order.service.OrderService;
import app.demo.order.web.BOOrderWebServiceImpl;
import core.framework.module.DBConfig;
import core.framework.module.Module;

/**
 * @author kimi
 */
public class OrderModule extends Module {
    @Override
    protected void initialize() {
        DBConfig db = db();
        db.repository(Order.class);

        bind(OrderService.class);
        api().service(BOOrderWebService.class, bind(BOOrderWebServiceImpl.class));

        executor().add();
        //kafka
        kafka().publish("order-created", OrderCreatedMessage.class);
    }
}
