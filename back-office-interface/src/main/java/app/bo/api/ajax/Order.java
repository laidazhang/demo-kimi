package app.bo.api.ajax;

import core.framework.api.json.Property;

import java.time.ZonedDateTime;

public class Order {
    @Property(name = "id")
    public Long id;

    @Property(name = "status")
    public String status;

    @Property(name = "total_price")
    public Double totalPrice;

    @Property(name = "customer_id")
    public Long customerId;

    @Property(name = "address_id")
    public Long firstName;

    @Property(name = "created_time")
    public ZonedDateTime createdTime;

    @Property(name = "updated_time")
    public ZonedDateTime updatedTime;
}