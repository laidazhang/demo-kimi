package app.demo.product.domain;

import core.framework.mongo.Collection;
import core.framework.mongo.Field;
import core.framework.mongo.Id;

import java.time.ZonedDateTime;

/**
 * @author kimi
 */
@Collection(name = "products")
public class Product {
    @Id
    @Field(name = "id")
    public String id;

    @Field(name = "status")
    public ProductStatus status;

    @Field(name = "title")
    public String title;

    @Field(name = "description")
    public String description;

    @Field(name = "create_time")
    public ZonedDateTime createTime;

    @Field(name = "update_time")
    public ZonedDateTime updateTime;
}
