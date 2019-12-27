package app.demo;

import app.demo.api.CustomerWebService;
import core.framework.module.App;

/**
 * @author kimi
 */
public class DemoSiteApp extends App {
    @Override
    protected void initialize() {
        api().client(CustomerWebService.class, "localhost:8080");
    }
}
