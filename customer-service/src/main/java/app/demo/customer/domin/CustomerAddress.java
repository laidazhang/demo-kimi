package app.demo.customer.domin;

import core.framework.api.validate.NotBlank;
import core.framework.api.validate.NotNull;
import core.framework.db.Column;
import core.framework.db.PrimaryKey;
import core.framework.db.Table;

/**
 * @author kimi
 */
@Table(name = "customer_addresses")
public class CustomerAddress {
    @PrimaryKey(autoIncrement = true)
    @Column(name = "id")
    public Long id;

    @NotNull
    @Column(name = "customer_id")
    public Long customerId;

    @NotNull
    @NotBlank
    @Column(name = "address")
    public String address;
}
