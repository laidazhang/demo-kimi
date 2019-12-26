package app.demo;

import app.demo.api.customer.CustomerView;
import app.demo.customer.domin.Customer;
import app.demo.customer.domin.CustomerAddress;
import app.demo.customer.domin.CustomerInfoView;
import app.demo.customer.domin.CustomerStatus;
import app.demo.customer.service.CustomerService;
import core.framework.module.DBConfig;
import core.framework.module.Module;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kimi
 */
public class CustomerModule extends Module {
    @Override
    protected void initialize() {
        DBConfig db = db();
        db.repository(Customer.class);
        db.repository(CustomerAddress.class);
        db.view(CustomerInfoView.class);

        bind(CustomerService.class);
        //test();
    }

    private void test() {
        CustomerService customerService = bean(CustomerService.class);
        CustomerView customerView = new CustomerView();
        //customerView.email = "kimi@test.com";
        //customerView.firstName = "kimi";
        customerView.lastName = "lai";

        //test create
        //Customer customer = customerService.create(customerView);

        //test batch create
        List<Customer> entityList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Customer entity = new Customer();
            entity.status = CustomerStatus.INACTIVE;
            entity.email = "kimi" + i + "@test.com";
            entity.firstName = "kimi" + i;
            entity.lastName = "lai";
            entity.updatedTime = ZonedDateTime.now();
            entityList.add(entity);
        }
        //customerService.batchCreate(entityList);

        //test get customer
        Customer customer1 = customerService.get(1);

        //test search
        List<Customer> customerList = customerService.search(customerView);

        //test update
        CustomerView updateView = new CustomerView();
        updateView.id = 1L;
        updateView.email = "kimi@test.com";
        updateView.firstName = "kimi123";
        updateView.lastName = null;
        customerService.update(updateView);

        //test delete
        customerService.delete(11L);

        //test count
        int count = customerService.count(CustomerStatus.INACTIVE);

        //test selectOne
        Customer customer2 = customerService.searchOne("kimi@test.com");

        List<CustomerInfoView> customerInfoViewList = customerService.search(1L);
    }
}
