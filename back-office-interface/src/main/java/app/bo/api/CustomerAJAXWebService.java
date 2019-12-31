package app.bo.api;

import app.bo.api.ajax.CreateCustomerAJAXRequest;
import app.bo.api.ajax.CreateCustomerAJAXResponse;
import app.bo.api.ajax.GetCustomerAJAXResponse;
import app.bo.api.ajax.SearchCustomerAJAXRequest;
import app.bo.api.ajax.SearchCustomerAJAXResponse;
import app.bo.api.ajax.UpdateCustomerAJAXRequest;
import app.bo.api.ajax.UpdateCustomerAJAXResponse;
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
public interface CustomerAJAXWebService {
    @POST
    @Path("/ajax/customer")
    @ResponseStatus(HTTPStatus.CREATED)
    CreateCustomerAJAXResponse create(CreateCustomerAJAXRequest request);

    @GET
    @Path("/ajax/customer/:id")
    GetCustomerAJAXResponse get(@PathParam("id") Long id);

    @PUT
    @Path("/ajax/customer/:id")
    UpdateCustomerAJAXResponse update(@PathParam("id") Long id, UpdateCustomerAJAXRequest request);

    @DELETE
    @Path("/ajax/customer/:id")
    void delete(@PathParam("id") Long id);

    @GET
    @Path("/ajax/customer")
    SearchCustomerAJAXResponse search(SearchCustomerAJAXRequest request);
}
