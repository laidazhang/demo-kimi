package app.demo.api.order;

import core.framework.api.json.Property;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author kimi
 */
public class BOSearchOrderResponse {
    @Property(name = "total")
    public Integer total;

    @Property(name = "orders")
    public List<Order> orderList;

    public static class Order {
        @Property(name = "id")
        public Long id;

        @Property(name = "status")
        public String status;

        @Property(name = "total_price")
        public Double totalPrice;

        @Property(name = "customer_id")
        public Long customerId;

        @Property(name = "address_id")
        public Long addressId;

        @Property(name = "created_time")
        public ZonedDateTime createdTime;

        @Property(name = "updated_time")
        public ZonedDateTime updatedTime;
    }
}
