package app.web.ajax;

import app.bo.api.CustomerAJAXWebService;
import app.bo.api.ajax.CreateCustomerAJAXRequest;
import app.bo.api.ajax.CreateCustomerAJAXResponse;
import app.bo.api.ajax.GetCustomerAJAXResponse;
import app.bo.api.ajax.SearchCustomerAJAXRequest;
import app.bo.api.ajax.SearchCustomerAJAXResponse;
import app.bo.api.ajax.UpdateCustomerAJAXRequest;
import app.bo.api.ajax.UpdateCustomerAJAXResponse;
import app.bo.api.ajax.OrderView;
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
    public CreateCustomerAJAXResponse create(CreateCustomerAJAXRequest request) {
        BOCreateCustomerRequest createCustomerRequest = new BOCreateCustomerRequest();
        createCustomerRequest.email = request.email;
        createCustomerRequest.firstName = request.firstName;
        createCustomerRequest.lastName = request.lastName;
        BOCreateCustomerResponse createCustomerResponse = customerWebService.create(createCustomerRequest);
        CreateCustomerAJAXResponse response = new CreateCustomerAJAXResponse();
        response.id = createCustomerResponse.id;
        response.firstName = createCustomerResponse.firstName;
        return response;
    }

    @Override
    public GetCustomerAJAXResponse get(Long id) {
        BOGetCustomerResponse getCustomerResponse = customerWebService.get(id);
        GetCustomerAJAXResponse response = new GetCustomerAJAXResponse();
        response.id = getCustomerResponse.id;
        response.email = getCustomerResponse.email;
        response.firstName = getCustomerResponse.firstName;
        BOSearchOrderRequest searchOrderRequest = new BOSearchOrderRequest();
        searchOrderRequest.customerId = id;
        searchOrderRequest.skip = 0;
        searchOrderRequest.limit = 3;
        BOSearchOrderResponse searchOrderResponse = orderWebService.search(searchOrderRequest);
        if (searchOrderResponse.total > 0) {
            response.orders = searchOrderResponse.orders.stream().map(item -> {
                OrderView orderView = new OrderView();
                orderView.id = item.id;
                orderView.totalPrice = item.totalPrice;
                orderView.status = item.status;
                return orderView;
            }).collect(Collectors.toList());
        }
        return response;
    }

    @Override
    public UpdateCustomerAJAXResponse update(Long id, UpdateCustomerAJAXRequest request) {
        BOUpdateCustomerRequest updateCustomerRequest = new BOUpdateCustomerRequest();
        updateCustomerRequest.firstName = request.firstName;
        updateCustomerRequest.lastName = request.lastName;
        BOUpdateCustomerResponse updateCustomerResponse = customerWebService.update(id, updateCustomerRequest);
        UpdateCustomerAJAXResponse response = new UpdateCustomerAJAXResponse();
        response.firstName = updateCustomerResponse.firstName;
        response.lastName = updateCustomerResponse.lastName;
        return response;
    }

    @Override
    public void delete(Long id) {
        customerWebService.delete(id);
    }

    @Override
    public SearchCustomerAJAXResponse search(SearchCustomerAJAXRequest request) {
        BOSearchCustomerRequest searchCustomerRequest = new BOSearchCustomerRequest();
        searchCustomerRequest.email = request.email;
        searchCustomerRequest.firstName = request.firstName;
        searchCustomerRequest.status = request.status;
        searchCustomerRequest.skip = request.skip;
        searchCustomerRequest.limit = request.limit;
        BOSearchCustomerResponse searchCustomerResponse = customerWebService.search(searchCustomerRequest);
        SearchCustomerAJAXResponse response = new SearchCustomerAJAXResponse();
        response.total = searchCustomerResponse.total;
        if (searchCustomerResponse.customerList != null && !searchCustomerResponse.customerList.isEmpty()) {
            response.customers = searchCustomerResponse.customerList.stream().map(item -> {
                SearchCustomerAJAXResponse.Customer customer = new SearchCustomerAJAXResponse.Customer();
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
