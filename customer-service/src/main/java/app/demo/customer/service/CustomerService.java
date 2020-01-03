package app.demo.customer.service;

import app.demo.api.customer.BOCreateCustomerRequest;
import app.demo.api.customer.BOCreateCustomerResponse;
import app.demo.api.customer.BOGetCustomerResponse;
import app.demo.api.customer.BOSearchCustomerRequest;
import app.demo.api.customer.BOSearchCustomerResponse;
import app.demo.api.customer.BOUpdateCustomerRequest;
import app.demo.api.customer.BOUpdateCustomerResponse;
import app.demo.customer.domin.Customer;
import app.demo.customer.domin.CustomerStatus;
import core.framework.cache.Cache;
import core.framework.db.Database;
import core.framework.db.Query;
import core.framework.db.Repository;
import core.framework.inject.Inject;
import core.framework.util.Strings;
import core.framework.web.exception.NotFoundException;

import java.time.ZonedDateTime;
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
    @Inject
    Cache<BOGetCustomerResponse> customerCache;

    public BOGetCustomerResponse get(Long id) {
        return customerCache.get(String.valueOf(id), (key) -> {
            Customer customer = customerRepository.get(id).orElseThrow();
            return getView(customer);
        });
    }

    public BOCreateCustomerResponse create(BOCreateCustomerRequest request) {
        Customer customer = new Customer();
        customer.status = CustomerStatus.ACTIVE;
        customer.email = request.email;
        customer.firstName = request.firstName;
        customer.lastName = request.lastName;
        customer.updatedTime = ZonedDateTime.now();
        OptionalLong id = customerRepository.insert(customer);
        customer.id = id.orElseThrow();
        return createView(customer);
    }

    public BOUpdateCustomerResponse update(Long id, BOUpdateCustomerRequest request) {
        Customer customer = customerRepository.get(id).orElseThrow(() -> new NotFoundException("customer not found, id=" + id));
        customer.firstName = request.firstName;
        customer.lastName = request.lastName;
        customer.updatedTime = ZonedDateTime.now();
        customerRepository.update(customer);
        return updateView(customer);
    }

    public void delete(Long id) {
        customerRepository.delete(id);
    }

    public BOSearchCustomerResponse search(BOSearchCustomerRequest request) {
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
        BOSearchCustomerResponse response = new BOSearchCustomerResponse();
        response.customerList = query.fetch().stream().map(this::view).collect(Collectors.toList());
        response.total = query.count();
        return response;
    }

    private BOSearchCustomerResponse.Customer view(Customer customer) {
        BOSearchCustomerResponse.Customer customerView = new BOSearchCustomerResponse.Customer();
        customerView.id = customer.id;
        customerView.email = customer.email;
        customerView.firstName = customer.firstName;
        customerView.lastName = customer.lastName;
        customerView.status = customer.status.toString();
        customerView.updatedTime = customer.updatedTime;
        return customerView;
    }

    private BOGetCustomerResponse getView(Customer customer) {
        BOGetCustomerResponse response = new BOGetCustomerResponse();
        response.id = customer.id;
        response.email = customer.email;
        response.firstName = customer.firstName;
        response.lastName = customer.lastName;
        response.status = customer.status.toString();
        return response;
    }

    private BOCreateCustomerResponse createView(Customer customer) {
        BOCreateCustomerResponse response = new BOCreateCustomerResponse();
        response.id = customer.id;
        response.email = customer.email;
        response.firstName = customer.firstName;
        response.lastName = customer.lastName;
        response.status = customer.status.toString();
        response.updatedTime = customer.updatedTime;
        return response;
    }

    private BOUpdateCustomerResponse updateView(Customer customer) {
        BOUpdateCustomerResponse response = new BOUpdateCustomerResponse();
        response.firstName = customer.firstName;
        response.lastName = customer.lastName;
        return response;
    }
}
