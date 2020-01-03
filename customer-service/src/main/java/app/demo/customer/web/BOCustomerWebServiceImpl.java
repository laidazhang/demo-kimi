package app.demo.customer.web;

import app.demo.api.BOCustomerWebService;
import app.demo.api.customer.BOCreateCustomerRequest;
import app.demo.api.customer.BOCreateCustomerResponse;
import app.demo.api.customer.BOGetCustomerResponse;
import app.demo.api.customer.BOSearchCustomerRequest;
import app.demo.api.customer.BOSearchCustomerResponse;
import app.demo.api.customer.BOUpdateCustomerRequest;
import app.demo.api.customer.BOUpdateCustomerResponse;
import app.demo.customer.service.CustomerService;
import core.framework.inject.Inject;
import core.framework.log.ActionLogContext;

/**
 * @author kimi
 */
public class BOCustomerWebServiceImpl implements BOCustomerWebService {
    @Inject
    CustomerService customerService;

    @Override
    public BOCreateCustomerResponse create(BOCreateCustomerRequest request) {
        ActionLogContext.put("email", request.email);
        ActionLogContext.put("firstName", request.firstName);
        ActionLogContext.put("lastName", request.lastName);
        return customerService.create(request);
    }

    @Override
    public BOGetCustomerResponse get(Long id) {
        return customerService.get(id);
    }

    @Override
    public BOUpdateCustomerResponse update(Long id, BOUpdateCustomerRequest request) {
        ActionLogContext.put("customerId", id);
        ActionLogContext.put("firstName", request.firstName);
        ActionLogContext.put("lastName", request.lastName);
        return customerService.update(id, request);
    }

    @Override
    public void delete(Long id) {
        customerService.delete(id);
    }

    @Override
    public BOSearchCustomerResponse search(BOSearchCustomerRequest request) {
        return customerService.search(request);
    }
}
