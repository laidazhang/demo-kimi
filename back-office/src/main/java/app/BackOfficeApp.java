package app;

import core.framework.module.App;
import core.framework.module.SystemModule;

/**
 * @author kimi
 */
public class BackOfficeApp extends App {
    @Override
    protected void initialize() {
        load(new SystemModule("sys.properties"));
        loadProperties("app.properties");
        load(new WebModule());
    }
}
