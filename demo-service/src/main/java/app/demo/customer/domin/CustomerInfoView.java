package app.demo.customer.domin;

import core.framework.db.Column;

/**
 * @author kimi
 */
public class CustomerInfoView {
    @Column(name = "id")
    public Long id;

    @Column(name = "email")
    public String email;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "address")
    public String address;
}
