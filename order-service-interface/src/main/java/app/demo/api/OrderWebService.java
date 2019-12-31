package app.demo.api;

import app.demo.api.order.CreateOrderRequest;
import app.demo.api.order.CreateOrderResponse;
import core.framework.api.http.HTTPStatus;
import core.framework.api.web.service.POST;
import core.framework.api.web.service.Path;
import core.framework.api.web.service.ResponseStatus;

/**
 * @author kimi
 */
public interface OrderWebService {
    @POST
    @Path("/order")
    @ResponseStatus(HTTPStatus.CREATED)
    CreateOrderResponse create(CreateOrderRequest request);
}
