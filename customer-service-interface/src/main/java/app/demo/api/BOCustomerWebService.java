package app.demo.api;

import app.demo.api.customer.BOCreateCustomerRequest;
import app.demo.api.customer.BOCreateCustomerResponse;
import app.demo.api.customer.BOGetCustomerResponse;
import app.demo.api.customer.BOSearchCustomerRequest;
import app.demo.api.customer.BOSearchCustomerResponse;
import app.demo.api.customer.BOUpdateCustomerRequest;
import app.demo.api.customer.BOUpdateCustomerResponse;
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
public interface BOCustomerWebService {
    @POST
    @Path("/bo/customer")
    @ResponseStatus(HTTPStatus.CREATED)
    BOCreateCustomerResponse create(BOCreateCustomerRequest request);

    @GET
    @Path("/bo/customer/:id")
    BOGetCustomerResponse get(@PathParam("id") Long id);

    @PUT
    @Path("/bo/customer/:id")
    BOUpdateCustomerResponse update(@PathParam("id") Long id, BOUpdateCustomerRequest request);

    @DELETE
    @Path("/bo/customer/:id")
    void delete(@PathParam("id") Long id);

    @GET
    @Path("/bo/customer")
    BOSearchCustomerResponse search(BOSearchCustomerRequest request);
}
