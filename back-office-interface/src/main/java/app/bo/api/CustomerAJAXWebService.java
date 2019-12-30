package app.bo.api;

import app.bo.api.ajax.CustomerAJAXCreateRequest;
import app.bo.api.ajax.CustomerAJAXCreateResponse;
import app.bo.api.ajax.CustomerAJAXGetResponse;
import app.bo.api.ajax.CustomerAJAXSearchRequest;
import app.bo.api.ajax.CustomerAJAXSearchResponse;
import app.bo.api.ajax.CustomerAJAXUpdateRequest;
import app.bo.api.ajax.CustomerAJAXUpdateResponse;
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
    @Path("/customer")
    @ResponseStatus(HTTPStatus.CREATED)
    CustomerAJAXCreateResponse create(CustomerAJAXCreateRequest request);

    @GET
    @Path("/customer/:id")
    CustomerAJAXGetResponse get(@PathParam("id") Long id);

    @PUT
    @Path("/customer/:id")
    CustomerAJAXUpdateResponse update(@PathParam("id") Long id, CustomerAJAXUpdateRequest request);

    @DELETE
    @Path("/customer/:id")
    void delete(@PathParam("id") Long id);

    @GET
    @Path("/customer")
    CustomerAJAXSearchResponse search(CustomerAJAXSearchRequest request);
}
