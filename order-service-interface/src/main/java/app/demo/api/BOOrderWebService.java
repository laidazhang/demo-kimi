package app.demo.api;

import app.demo.api.order.BOGetOrderResponse;
import app.demo.api.order.BOSearchOrderRequest;
import app.demo.api.order.BOSearchOrderResponse;
import core.framework.api.web.service.GET;
import core.framework.api.web.service.Path;
import core.framework.api.web.service.PathParam;

/**
 * @author kimi
 */
public interface BOOrderWebService {
    @GET
    @Path("/bo/order/:id")
    BOGetOrderResponse get(@PathParam("id") Long id);

    @GET
    @Path("/bo/order")
    BOSearchOrderResponse search(BOSearchOrderRequest request);

}
