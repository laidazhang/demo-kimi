package app.bo.api.ajax;

import core.framework.api.json.Property;

/**
 * @author kimi
 */
public class CustomerAJAXUpdateResponse {
    @Property(name = "first_name")
    public String firstName;

    @Property(name = "last_name")
    public String lastName;
}
