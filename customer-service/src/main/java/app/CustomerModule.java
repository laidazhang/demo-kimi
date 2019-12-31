package app;

import app.demo.api.BOCustomerWebService;
import app.demo.api.kafka.OrderCreatedMessage;
import app.demo.customer.domin.Customer;
import app.demo.customer.domin.CustomerAddress;
import app.demo.customer.domin.CustomerView;
import app.demo.customer.service.CustomerService;
import app.demo.customer.web.BOCustomerWebServiceImpl;
import app.demo.kafka.BulkOrderCreatedMessageHandler;
import app.demo.kafka.OrderCreatedMessageHandler;
import core.framework.module.DBConfig;
import core.framework.module.Module;

/**
 * @author kimi
 */
public class CustomerModule extends Module {
    @Override
    protected void initialize() {
        configDB();
        bind(CustomerService.class);
        api().service(BOCustomerWebService.class, bind(BOCustomerWebServiceImpl.class));

        //kafka
        kafka().subscribe("order-created", OrderCreatedMessage.class, bind(OrderCreatedMessageHandler.class));
        //kafka().subscribe("order-created", OrderCreatedMessage.class, bind(BulkOrderCreatedMessageHandler.class));
    }

    private void configDB() {
        DBConfig db = db();
        db.repository(Customer.class);
        db.repository(CustomerAddress.class);
        db.view(CustomerView.class);
    }
}
