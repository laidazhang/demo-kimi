package app.demo.api.customer;

import core.framework.api.validate.Max;
import core.framework.api.validate.Min;
import core.framework.api.validate.NotNull;
import core.framework.api.web.service.QueryParam;

/**
 * @author kimi
 */
public class BOSearchCustomerRequest {
    @QueryParam(name = "status")
    public String status;

    @QueryParam(name = "email")
    public String email;

    @QueryParam(name = "first_name")
    public String firstName;

    @QueryParam(name = "last_name")
    public String lastName;

    @NotNull
    @QueryParam(name = "skip")
    public Integer skip = 0;

    @NotNull
    @Min(1)
    @Max(100)
    @QueryParam(name = "limit")
    public Integer limit = 10;
}
