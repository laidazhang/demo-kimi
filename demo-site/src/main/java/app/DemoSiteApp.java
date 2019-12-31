package app;

import app.demo.web.interceptor.LoginInterceptor;
import core.framework.module.App;
import core.framework.module.SystemModule;

/**
 * @author kimi
 */
public class DemoSiteApp extends App {
    @Override
    protected void initialize() {
        load(new SystemModule("sys.properties"));

        http().intercept(new LoginInterceptor());
        load(new LoginModule());
        load(new CustomerModule());
    }
}
