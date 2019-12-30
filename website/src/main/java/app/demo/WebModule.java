package app.demo;

import app.demo.web.HomeController;
import core.framework.http.HTTPMethod;
import core.framework.module.Module;

/**
 * @author kimi
 */
public class WebModule extends Module {

    @Override
    protected void initialize() {
        HomeController homeController = bind(HomeController.class);
        http().route(HTTPMethod.GET, "/hello", homeController::sayHello);
    }
}
