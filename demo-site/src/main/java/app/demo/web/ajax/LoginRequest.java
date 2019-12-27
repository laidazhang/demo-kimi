package app.demo.web.ajax;

import core.framework.api.json.Property;
import core.framework.api.validate.NotBlank;
import core.framework.api.validate.NotNull;

/**
 * @author kimi
 */
public class LoginRequest {
    @NotNull
    @NotBlank
    @Property(name = "user_name")
    public String userName;
}
