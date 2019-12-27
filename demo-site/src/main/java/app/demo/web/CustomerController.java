package app.demo.web;

import app.demo.api.CustomerWebService;
import app.demo.api.customer.CreateCustomerRequest;
import app.demo.api.customer.CustomerView;
import app.demo.web.interceptor.Protected;
import core.framework.api.http.HTTPStatus;
import core.framework.inject.Inject;
import core.framework.web.Request;
import core.framework.web.Response;

/**
 * @author kimi
 */
public class CustomerController {
    @Inject
    CustomerWebService customerService;

    //@Protected
    public Response create(Request request) {
        CreateCustomerRequest createCustomerRequest = request.bean(CreateCustomerRequest.class);
        CustomerView customerView = customerService.create(createCustomerRequest);
        return Response.bean(customerView).status(HTTPStatus.OK);
    }
}
