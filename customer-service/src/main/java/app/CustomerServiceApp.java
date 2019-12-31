package app;

import core.framework.module.App;
import core.framework.module.SystemModule;

/**
 * @author kimi
 */
public class CustomerServiceApp extends App {
    @Override
    protected void initialize() {
        load(new SystemModule("sys.properties"));
        load(new CustomerModule());
    }
}
