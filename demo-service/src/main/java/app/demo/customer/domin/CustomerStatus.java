package app.demo.customer.domin;

import core.framework.db.DBEnumValue;

/**
 * @author kimi
 */
public enum CustomerStatus {
    @DBEnumValue("ACTIVE")
    ACTIVE,
    @DBEnumValue("INACTIVE")
    INACTIVE
}
