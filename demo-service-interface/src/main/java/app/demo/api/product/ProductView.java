package app.demo.api.product;

import core.framework.api.json.Property;

import java.time.ZonedDateTime;

/**
 * @author kimi
 */
public class ProductView {
    @Property(name = "id")
    public String id;

    @Property(name = "status")
    public String status;

    @Property(name = "title")
    public String title;

    @Property(name = "description")
    public String description;

    @Property(name = "create_time")
    public ZonedDateTime createTime;

    @Property(name = "update_time")
    public ZonedDateTime updateTime;
}
