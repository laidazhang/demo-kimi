package app.demo.order.service;

import app.demo.api.order.BOGetOrderResponse;
import app.demo.api.order.BOSearchOrderRequest;
import app.demo.api.order.BOSearchOrderResponse;
import app.demo.order.domain.Order;
import core.framework.db.Query;
import core.framework.db.Repository;
import core.framework.inject.Inject;
import core.framework.util.Strings;
import core.framework.web.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kimi
 */
public class OrderService {
    @Inject
    Repository<Order> orderRepository;

    public BOGetOrderResponse get(Long id) {
        Order order = orderRepository.get(id).orElseThrow(() -> new NotFoundException("order not found, id =" + id));
        BOGetOrderResponse response = new BOGetOrderResponse();
        response.id = order.id;
        response.customerId = order.customerId;
        response.addressId = order.addressId;
        response.createdTime = order.createdTime;
        response.status = order.status.name();
        return response;
    }

    public BOSearchOrderResponse search(BOSearchOrderRequest request) {
        Query<Order> query = orderRepository.select();
        query.skip(request.skip);
        query.limit(request.limit);
        if (request.customerId != null) {
            query.where("customer_id = ?", request.customerId);
        }
        if (!Strings.isBlank(request.status)) {
            query.where("status = ?", request.status);
        }
        List<Order> orderList = query.fetch();
        BOSearchOrderResponse response = new BOSearchOrderResponse();
        response.total = query.count();
        if (!orderList.isEmpty()) {
            response.orderList = orderList.stream().map(item -> {
                BOSearchOrderResponse.Order order = new BOSearchOrderResponse.Order();
                order.id = item.id;
                order.status = item.status.toString();
                order.customerId = item.customerId;
                order.addressId = item.addressId;
                order.totalPrice = item.totalPrice;
                order.createdTime = item.createdTime;
                return order;
            }).collect(Collectors.toList());
        }
        return response;
    }
}
