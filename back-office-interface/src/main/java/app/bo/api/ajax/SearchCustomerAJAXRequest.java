package app.bo.api.ajax;

import core.framework.api.validate.NotNull;
import core.framework.api.web.service.QueryParam;

/**
 * @author kimi
 */
public class SearchCustomerAJAXRequest {
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
    @QueryParam(name = "limit")
    public Integer limit = 10;
}
