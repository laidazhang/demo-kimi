package app.demo.api.customer;

import core.framework.api.json.Property;

import java.time.ZonedDateTime;

/**
 * @author kimi
 */
public class BOCreateCustomerResponse {
    @Property(name = "id")
    public Long id;

    @Property(name = "status")
    public String status;

    @Property(name = "email")
    public String email;

    @Property(name = "first_name")
    public String firstName;

    @Property(name = "last_name")
    public String lastName;

    @Property(name = "updated_time")
    public ZonedDateTime updatedTime;
}
