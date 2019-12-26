package app.demo.product.domain;

import core.framework.mongo.MongoEnumValue;

/**
 * @author kimi
 */
public enum ProductStatus {
    @MongoEnumValue("ACTIVE")
    ACTIVE,
    @MongoEnumValue("INACTIVE")
    INACTIVE
}
