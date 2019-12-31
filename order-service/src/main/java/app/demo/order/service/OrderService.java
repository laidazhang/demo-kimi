package app.demo.order.service;

import app.demo.api.kafka.OrderCreatedMessage;
import app.demo.api.order.BOGetOrderResponse;
import app.demo.api.order.BOSearchOrderRequest;
import app.demo.api.order.BOSearchOrderResponse;
import app.demo.api.order.CreateOrderRequest;
import app.demo.api.order.CreateOrderResponse;
import app.demo.order.domain.Order;
import app.demo.order.domain.OrderStatus;
import core.framework.async.Executor;
import core.framework.db.Query;
import core.framework.db.Repository;
import core.framework.inject.Inject;
import core.framework.kafka.MessagePublisher;
import core.framework.util.Strings;
import core.framework.web.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kimi
 */
public class OrderService {
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Inject
    Repository<Order> orderRepository;
    @Inject
    MessagePublisher<OrderCreatedMessage> orderCreatedMessagePublisher;
    @Inject
    Executor executor;

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
            response.orders = orderList.stream().map(item -> {
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

    public CreateOrderResponse create(CreateOrderRequest request) {
        Order order = new Order();
        order.customerId = request.customerId;
        order.addressId = request.addressId;
        order.status = OrderStatus.PENDING;
        order.totalPrice = request.totalPrice;
        order.createdTime = ZonedDateTime.now();
        long orderId = orderRepository.insert(order).orElseThrow();

        //executor
        printOrder(order);
        //kafka
        OrderCreatedMessage orderCreatedMessage = new OrderCreatedMessage();
        orderCreatedMessage.id = String.valueOf(orderId);
        orderCreatedMessage.customerId = order.customerId;
        orderCreatedMessage.createdTime = order.createdTime;
        orderCreatedMessagePublisher.publish(orderCreatedMessage.id, orderCreatedMessage);

        CreateOrderResponse response = new CreateOrderResponse();
        response.id = orderId;
        response.customerId = order.customerId;
        response.addressId = order.addressId;
        response.totalPrice = order.totalPrice;
        response.createdTime = order.createdTime;
        return response;
    }

    private void printOrder(final Order order) {
        executor.submit("order-created", () -> logger.info("executor submit, orderId={}, time={}", order.id, ZonedDateTime.now()));
    }
}
