package app.demo.api;

import app.demo.api.customer.CreateCustomerRequest;
import app.demo.api.customer.CustomerView;
import app.demo.api.customer.SearchCustomerRequest;
import app.demo.api.customer.SearchCustomerResponse;
import app.demo.api.customer.UpdateCustomerRequest;
import core.framework.api.http.HTTPStatus;
import core.framework.api.web.service.DELETE;
import core.framework.api.web.service.GET;
import core.framework.api.web.service.POST;
import core.framework.api.web.service.PUT;
import core.framework.api.web.service.Path;
import core.framework.api.web.service.PathParam;
import core.framework.api.web.service.ResponseStatus;

/**
 * @author kimi
 */
public interface CustomerWebService {
    @POST
    @Path("/customer")
    @ResponseStatus(HTTPStatus.CREATED)
    CustomerView create(CreateCustomerRequest request);

    @GET
    @Path("/customer/:id")
    CustomerView get(@PathParam("id") Long id);

    @PUT
    @Path("/customer/:id")
    CustomerView update(@PathParam("id") Long id, UpdateCustomerRequest request);

    @DELETE
    @Path("/customer/:id")
    void delete(@PathParam("id") Long id);

    @GET
    @Path("/customers")
    SearchCustomerResponse search(SearchCustomerRequest request);
}
