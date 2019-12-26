package app.demo;

import core.framework.module.App;
import core.framework.module.SystemModule;

/**
 * @author kimi
 */
public class DemoServiceApp extends App {
    @Override
    protected void initialize() {
        load(new SystemModule("sys.properties"));
        loadProperties("app.properties");
        load(new PainterModule());
        load(new CustomerModule());
        load(new ProductModule());
    }
}
