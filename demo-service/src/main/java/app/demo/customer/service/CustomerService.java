package app.demo.customer.service;

import app.demo.api.customer.CreateCustomerRequest;
import app.demo.api.customer.CustomerView;
import app.demo.api.customer.SearchCustomerRequest;
import app.demo.api.customer.SearchCustomerResponse;
import app.demo.api.customer.UpdateCustomerRequest;
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
import java.util.stream.Collectors;

/**
 * @author kimi
 */
public class CustomerService {
    @Inject
    Repository<Customer> customerRepository;
    @Inject
    Database database;

    public CustomerView get(Long id) {
        Customer customer = customerRepository.get(id).orElseThrow();
        return view(customer);
    }

    public CustomerView create(CreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.status = CustomerStatus.ACTIVE;
        customer.email = request.email;
        customer.firstName = request.firstName;
        customer.lastName = request.lastName;
        customer.updatedTime = ZonedDateTime.now();
        OptionalLong id = customerRepository.insert(customer);
        customer.id = id.orElseThrow();
        return view(customer);
    }

    public void batchCreate(List<Customer> customerList) {
        customerRepository.batchInsert(customerList);
    }

    public CustomerView update(Long id, UpdateCustomerRequest request) {
        Optional<Customer> customer = customerRepository.get(id);
        customer.ifPresent(cus -> {
            cus.firstName = request.firstName;
            cus.lastName = request.lastName;
            cus.updatedTime = ZonedDateTime.now();
            customerRepository.update(cus);
        });
        return view(customer.orElseThrow());
    }

    public void delete(Long id) {
        customerRepository.delete(id);
    }

    public SearchCustomerResponse search(SearchCustomerRequest request) {
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
        query.skip(request.skip);
        query.limit(request.limit);
        SearchCustomerResponse response = new SearchCustomerResponse();
        response.customers = query.fetch().stream().map(this::view).collect(Collectors.toList());
        response.total = query.count();
        return response;
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

    private CustomerView view(Customer customer) {
        CustomerView customerView = new CustomerView();
        customerView.id = customer.id;
        customerView.email = customer.email;
        customerView.firstName = customer.firstName;
        customerView.lastName = customer.lastName;
        customerView.status = customer.status.toString();
        customerView.updatedTime = customer.updatedTime;
        return customerView;
    }
}
