package app.demo.order.web;

import app.demo.api.BOOrderWebService;
import app.demo.api.order.BOGetOrderResponse;
import app.demo.api.order.BOSearchOrderRequest;
import app.demo.api.order.BOSearchOrderResponse;
import app.demo.order.service.OrderService;
import core.framework.inject.Inject;

/**
 * @author kimi
 */
public class BOOrderWebServiceImpl implements BOOrderWebService {
    @Inject
    OrderService orderService;

    @Override
    public BOGetOrderResponse get(Long id) {
        return orderService.get(id);
    }

    @Override
    public BOSearchOrderResponse search(BOSearchOrderRequest request) {
        return orderService.search(request
        );
    }
}
