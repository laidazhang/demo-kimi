package app.demo;

import app.demo.api.CustomerWebService;
import app.demo.web.CustomerController;
import core.framework.module.Module;

import static core.framework.http.HTTPMethod.POST;

/**
 * @author kimi
 */
public class CustomerModule extends Module {
    @Override
    protected void initialize() {
        api().client(CustomerWebService.class, "http://localhost:8080");

        CustomerController customerController = bind(CustomerController.class);
        http().route(POST, "/customer", customerController::create);
    }
}
