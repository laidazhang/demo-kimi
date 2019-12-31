package app.bo.api.ajax;

import core.framework.api.json.Property;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author kimi
 */
public class SearchCustomerAJAXResponse {
    @Property(name = "total")
    public Integer total;

    @Property(name = "customers")
    public List<Customer> customers;

    public static class Customer {
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
}
