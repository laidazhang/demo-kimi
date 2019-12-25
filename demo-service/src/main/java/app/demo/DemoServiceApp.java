package app.demo;

import core.framework.module.App;

/**
 * @author kimi
 */
public class DemoServiceApp extends App {
    @Override
    protected void initialize() {
        load(new PainterModule());
    }
}
