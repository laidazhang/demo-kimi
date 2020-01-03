package demo;

import app.CustomerServiceApp;
import core.framework.test.module.AbstractTestModule;

/**
 * @author neo
 */
public class TestModule extends AbstractTestModule {
    @Override
    protected void initialize() {
        load(new CustomerServiceApp());
        initDB().createSchema();
    }
}
