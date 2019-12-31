package app.bo.api.ajax;

import core.framework.api.json.Property;

import java.util.List;

/**
 * @author kimi
 */
public class GetCustomerAJAXResponse {
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

    @Property(name = "orders")
    public List<OrderView> orders;
}
