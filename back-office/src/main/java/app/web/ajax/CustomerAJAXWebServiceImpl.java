package app.web.ajax;

import app.bo.api.CustomerAJAXWebService;
import app.bo.api.ajax.CustomerAJAXCreateRequest;
import app.bo.api.ajax.CustomerAJAXCreateResponse;
import app.bo.api.ajax.CustomerAJAXGetResponse;
import app.bo.api.ajax.CustomerAJAXSearchRequest;
import app.bo.api.ajax.CustomerAJAXSearchResponse;
import app.bo.api.ajax.CustomerAJAXUpdateRequest;
import app.bo.api.ajax.CustomerAJAXUpdateResponse;
import app.bo.api.ajax.Order;
import app.demo.api.BOCustomerWebService;
import app.demo.api.BOOrderWebService;
import app.demo.api.customer.BOCreateCustomerRequest;
import app.demo.api.customer.BOCreateCustomerResponse;
import app.demo.api.customer.BOGetCustomerResponse;
import app.demo.api.customer.BOSearchCustomerRequest;
import app.demo.api.customer.BOSearchCustomerResponse;
import app.demo.api.customer.BOUpdateCustomerRequest;
import app.demo.api.customer.BOUpdateCustomerResponse;
import app.demo.api.order.BOSearchOrderRequest;
import app.demo.api.order.BOSearchOrderResponse;
import core.framework.inject.Inject;

import java.util.stream.Collectors;

/**
 * @author kimi
 */
public class CustomerAJAXWebServiceImpl implements CustomerAJAXWebService {
    @Inject
    BOCustomerWebService customerWebService;
    @Inject
    BOOrderWebService orderWebService;

    @Override
    public CustomerAJAXCreateResponse create(CustomerAJAXCreateRequest request) {
        BOCreateCustomerRequest createCustomerRequest = new BOCreateCustomerRequest();
        createCustomerRequest.email = request.email;
        createCustomerRequest.firstName = request.firstName;
        createCustomerRequest.lastName = request.lastName;
        BOCreateCustomerResponse createCustomerResponse = customerWebService.create(createCustomerRequest);
        CustomerAJAXCreateResponse response = new CustomerAJAXCreateResponse();
        response.id = createCustomerResponse.id;
        response.firstName = createCustomerResponse.firstName;
        return response;
    }

    @Override
    public CustomerAJAXGetResponse get(Long id) {
        BOGetCustomerResponse getCustomerResponse = customerWebService.get(id);
        CustomerAJAXGetResponse response = new CustomerAJAXGetResponse();
        response.id = getCustomerResponse.id;
        response.email = getCustomerResponse.email;
        response.firstName = getCustomerResponse.firstName;
        BOSearchOrderRequest searchOrderRequest = new BOSearchOrderRequest();
        searchOrderRequest.customerId = id;
        searchOrderRequest.skip = 0;
        searchOrderRequest.limit = 3;
        BOSearchOrderResponse searchOrderResponse = orderWebService.search(searchOrderRequest);
        if (searchOrderResponse.total > 0) {
            response.orderList = searchOrderResponse.orderList.stream().map(item -> {
                Order order = new Order();
                order.id = item.id;
                order.totalPrice = item.totalPrice;
                order.status = item.status;
                return order;
            }).collect(Collectors.toList());
        }
        return response;
    }

    @Override
    public CustomerAJAXUpdateResponse update(Long id, CustomerAJAXUpdateRequest request) {
        BOUpdateCustomerRequest updateCustomerRequest = new BOUpdateCustomerRequest();
        updateCustomerRequest.firstName = request.firstName;
        updateCustomerRequest.lastName = request.lastName;
        BOUpdateCustomerResponse updateCustomerResponse = customerWebService.update(id, updateCustomerRequest);
        CustomerAJAXUpdateResponse response = new CustomerAJAXUpdateResponse();
        response.firstName = updateCustomerResponse.firstName;
        response.lastName = updateCustomerResponse.lastName;
        return response;
    }

    @Override
    public void delete(Long id) {
        customerWebService.delete(id);
    }

    @Override
    public CustomerAJAXSearchResponse search(CustomerAJAXSearchRequest request) {
        BOSearchCustomerRequest searchCustomerRequest = new BOSearchCustomerRequest();
        searchCustomerRequest.email = request.email;
        searchCustomerRequest.firstName = request.firstName;
        searchCustomerRequest.status = request.status;
        searchCustomerRequest.skip = request.skip;
        searchCustomerRequest.limit = request.limit;
        BOSearchCustomerResponse searchCustomerResponse = customerWebService.search(searchCustomerRequest);
        CustomerAJAXSearchResponse response = new CustomerAJAXSearchResponse();
        response.total = searchCustomerResponse.total;
        if (searchCustomerResponse.customerList != null && !searchCustomerResponse.customerList.isEmpty()) {
            response.customerList = searchCustomerResponse.customerList.stream().map(item -> {
                CustomerAJAXSearchResponse.Customer customer = new CustomerAJAXSearchResponse.Customer();
                customer.id = item.id;
                customer.email = item.email;
                customer.firstName = item.firstName;
                customer.status = item.status;
                return customer;
            }).collect(Collectors.toList());
        }
        return response;
    }
}
