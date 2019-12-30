package app.demo.api.order;

import core.framework.api.json.Property;

import java.time.ZonedDateTime;

/**
 * @author kimi
 */
public class BOGetOrderResponse {
    @Property(name = "id")
    public Long id;

    @Property(name = "status")
    public String status;

    @Property(name = "customer_id")
    public Long customerId;

    @Property(name = "address_id")
    public Long addressId;

    @Property(name = "created_time")
    public ZonedDateTime createdTime;
}
