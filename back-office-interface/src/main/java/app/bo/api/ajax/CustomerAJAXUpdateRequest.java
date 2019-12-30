package app.bo.api.ajax;

import core.framework.api.json.Property;
import core.framework.api.validate.NotBlank;
import core.framework.api.validate.NotNull;

/**
 * @author kimi
 */
public class CustomerAJAXUpdateRequest {
    @NotNull
    @NotBlank
    @Property(name = "first_name")
    public String firstName;

    @NotBlank
    @Property(name = "last_name")
    public String lastName;
}
