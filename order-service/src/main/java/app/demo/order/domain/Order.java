package app.demo.order.domain;

import core.framework.api.validate.Min;
import core.framework.api.validate.NotNull;
import core.framework.db.Column;
import core.framework.db.PrimaryKey;
import core.framework.db.Table;

import java.time.ZonedDateTime;

/**
 * @author kimi
 */
@Table(name = "orders")
public class Order {
    @PrimaryKey(autoIncrement = true)
    @Column(name = "id")
    public Long id;

    @NotNull
    @Column(name = "status")
    public OrderStatus status;

    @NotNull
    @Min(0.01)
    @Column(name = "total_price")
    public Double totalPrice;

    @NotNull
    @Column(name = "customer_id")
    public Long customerId;

    @NotNull
    @Column(name = "address_id")
    public Long addressId;

    @NotNull
    @Column(name = "created_time")
    public ZonedDateTime createdTime;

    @Column(name = "updated_time")
    public ZonedDateTime updatedTime;
}
