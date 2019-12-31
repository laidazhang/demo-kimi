package app;

import core.framework.module.App;

/**
 * @author kimi
 */
public class DemoSchedulerApp extends App {
    @Override
    protected void initialize() {
        load(new JobModule());
    }
}
