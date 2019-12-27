package app.demo;

import app.demo.web.LoginController;
import app.demo.web.ajax.LoginRequest;
import core.framework.module.Module;

import static core.framework.http.HTTPMethod.PUT;

/**
 * @author kimi
 */
public class LoginModule extends Module {
    @Override
    protected void initialize() {
        LoginController loginController = bind(LoginController.class);
        http().bean(LoginRequest.class);
        http().route(PUT, "/login", loginController::login);
    }
}
