package app.demo.api.kafka;

import core.framework.api.json.Property;
import core.framework.api.validate.NotBlank;
import core.framework.api.validate.NotNull;

import java.time.ZonedDateTime;

/**
 * @author kimi
 */
public class OrderCreatedMessage {
    @NotNull
    @NotBlank
    @Property(name = "id")
    public String id;

    @NotNull
    @Property(name = "customer_id")
    public Long customerId;

    @NotNull
    @Property(name = "created_time")
    public ZonedDateTime createdTime;
}
