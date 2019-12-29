package app.demo.product.domain;

import core.framework.mongo.Field;
import core.framework.mongo.Id;

/**
 * @author kimi
 */
public class ProductAggregateView {
    @Id
    public String id;

    @Field(name = "count")
    public Integer count;
}
