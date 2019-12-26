package app.demo.customer.service;

import app.demo.api.customer.CustomerView;
import app.demo.customer.domin.Customer;
import app.demo.customer.domin.CustomerInfoView;
import app.demo.customer.domin.CustomerStatus;
import core.framework.db.Database;
import core.framework.db.Query;
import core.framework.db.Repository;
import core.framework.inject.Inject;
import core.framework.util.Strings;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

/**
 * @author kimi
 */
public class CustomerService {
    @Inject
    Repository<Customer> customerRepository;
    @Inject
    Database database;

    public Customer get(Integer id) {
        return customerRepository.get(id).orElseThrow();
    }

    public Customer create(CustomerView customerView) {
        Customer customer = new Customer();
        customer.status = CustomerStatus.ACTIVE;
        customer.email = customerView.email;
        customer.firstName = customerView.firstName;
        customer.lastName = customerView.lastName;
        customer.updatedTime = ZonedDateTime.now();
        OptionalLong id = customerRepository.insert(customer);
        customer.id = id.orElseThrow();
        return customer;
    }

    public void batchCreate(List<Customer> customerList) {
        customerRepository.batchInsert(customerList);
    }

    public Customer update(CustomerView customerView) {
        Optional<Customer> customer = customerRepository.get(customerView.id);
        customer.ifPresent(cus -> {
            cus.email = customerView.email;
            cus.firstName = customerView.firstName;
            cus.lastName = customerView.lastName;
            cus.updatedTime = ZonedDateTime.now();
            customerRepository.update(cus);
        });
        return customer.orElseThrow();
    }

    public void delete(Long id) {
        customerRepository.delete(id);
    }

    public List<Customer> search(CustomerView request) {
        Query<Customer> query = customerRepository.select();
        if (!Strings.isBlank(request.status)) {
            query.where("status = ?", request.status);
        }
        if (!Strings.isBlank(request.email)) {
            query.where("email = ?", request.email);
        }
        if (!Strings.isBlank(request.firstName)) {
            query.where("first_name like ?", Strings.format("{}%", request.firstName));
        }
        if (!Strings.isBlank(request.lastName)) {
            query.where("last_name like ?", Strings.format("{}%", request.lastName));
        }
        query.skip(0);
        query.limit(10);
        return query.fetch();
    }

    public List<CustomerInfoView> search(Long id) {
        String sql = "select c.id, c.email, c.first_name, c.last_name, ca.address from customers as c inner join customer_addresses as ca on ca.customer_id = c.id where c.id = ?";
        return database.select(sql, CustomerInfoView.class, id);
    }

    public int count(CustomerStatus status) {
        return customerRepository.count("status = ?", status);
    }

    public Customer searchOne(String email) {
        Optional<Customer> customer = customerRepository.selectOne("email = ?", email);
        return customer.orElseThrow();
    }
}
