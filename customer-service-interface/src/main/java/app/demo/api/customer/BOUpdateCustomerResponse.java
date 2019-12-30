package app.demo.api.customer;

import core.framework.api.json.Property;

/**
 * @author kimi
 */
public class BOUpdateCustomerResponse {
    @Property(name = "first_name")
    public String firstName;

    @Property(name = "last_name")
    public String lastName;
}
