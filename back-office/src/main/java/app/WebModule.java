package app;

import app.bo.api.CustomerAJAXWebService;
import app.demo.api.BOCustomerWebService;
import app.demo.api.BOOrderWebService;
import app.demo.api.kafka.OrderCreatedMessage;
import app.web.ajax.CustomerAJAXWebServiceImpl;
import core.framework.module.Module;

/**
 * @author kimi
 */
public class WebModule extends Module {
    @Override
    protected void initialize() {
        api().client(BOCustomerWebService.class, requiredProperty("app.customerServiceURL"));
        api().client(BOOrderWebService.class, requiredProperty("app.orderServiceURL"));

        api().service(CustomerAJAXWebService.class, bind(CustomerAJAXWebServiceImpl.class));
    }
}
