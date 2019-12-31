package app.demo.api.order;

import core.framework.api.json.Property;
import core.framework.api.validate.NotNull;

/**
 * @author kimi
 */
public class CreateOrderRequest {
    @NotNull
    @Property(name = "customer_id")
    public Long customerId;

    @NotNull
    @Property(name = "address_id")
    public Long addressId;

    @NotNull
    @Property(name = "total_price")
    public Double totalPrice;
}
