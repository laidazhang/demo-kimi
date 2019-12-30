package app.demo.order.domain;

import core.framework.db.DBEnumValue;

/**
 * @author kimi
 */
public enum OrderStatus {
    @DBEnumValue("PENDING")
    PENDING,
    @DBEnumValue("COMPLETE")
    COMPLETE
}
