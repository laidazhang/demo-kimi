package app.demo.api.order;

import core.framework.api.json.Property;

import java.time.ZonedDateTime;

/**
 * @author kimi
 */
public class CreateOrderResponse {
    @Property(name = "id")
    public Long id;

    @Property(name = "customer_id")
    public Long customerId;

    @Property(name = "address_id")
    public Long addressId;

    @Property(name = "total_price")
    public Double totalPrice;

    @Property(name = "created_time")
    public ZonedDateTime createdTime;
}
