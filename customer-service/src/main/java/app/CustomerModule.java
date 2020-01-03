package app;

import app.demo.api.BOCustomerWebService;
import app.demo.api.customer.BOGetCustomerResponse;
import app.demo.customer.domin.Customer;
import app.demo.customer.domin.CustomerAddress;
import app.demo.customer.domin.CustomerView;
import app.demo.customer.service.CustomerService;
import app.demo.customer.web.BOCustomerWebServiceImpl;
import core.framework.module.DBConfig;
import core.framework.module.Module;

import java.time.Duration;

/**
 * @author kimi
 */
public class CustomerModule extends Module {
    @Override
    protected void initialize() {
        configDB();

        //cache
        cache().remote(BOGetCustomerResponse.class, Duration.ofMinutes(30));
        
        bind(CustomerService.class);
        api().service(BOCustomerWebService.class, bind(BOCustomerWebServiceImpl.class));
    }

    private void configDB() {
        DBConfig db = db();
        db.repository(Customer.class);
        db.repository(CustomerAddress.class);
        db.view(CustomerView.class);
    }
}
