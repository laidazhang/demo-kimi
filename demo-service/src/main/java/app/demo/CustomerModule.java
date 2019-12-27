package app.demo;

import app.demo.api.CustomerWebService;
import app.demo.customer.domin.Customer;
import app.demo.customer.domin.CustomerAddress;
import app.demo.customer.domin.CustomerInfoView;
import app.demo.customer.service.CustomerService;
import app.demo.customer.web.CustomerWebServiceImpl;
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
        api().service(CustomerWebService.class, bind(CustomerWebServiceImpl.class));
    }

    private void configDB() {
        DBConfig db = db();
        db.repository(Customer.class);
        db.repository(CustomerAddress.class);
        db.view(CustomerInfoView.class);
    }
}
