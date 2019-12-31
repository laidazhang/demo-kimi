package app.demo.order.web;

import app.demo.api.OrderWebService;
import app.demo.api.order.CreateOrderRequest;
import app.demo.api.order.CreateOrderResponse;
import app.demo.order.service.OrderService;
import core.framework.inject.Inject;

/**
 * @author kimi
 */
public class OrderWebServiceImpl implements OrderWebService {
    @Inject
    OrderService orderService;

    @Override
    public CreateOrderResponse create(CreateOrderRequest request) {
        return orderService.create(request);
    }
}
