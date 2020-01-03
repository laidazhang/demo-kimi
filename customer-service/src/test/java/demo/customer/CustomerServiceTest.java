package demo.customer;

import app.demo.api.customer.BOCreateCustomerRequest;
import app.demo.api.customer.BOCreateCustomerResponse;
import app.demo.api.customer.BOSearchCustomerRequest;
import app.demo.api.customer.BOSearchCustomerResponse;
import app.demo.customer.service.CustomerService;
import core.framework.inject.Inject;
import core.framework.internal.db.DatabaseImpl;
import demo.IntegrationTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author kimi
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerServiceTest extends IntegrationTest {
    @Inject
    CustomerService customerService;
    private DatabaseImpl database;

    @BeforeAll
    void createDatabase() {
        database = new DatabaseImpl("db");
        database.url("jdbc:hsqldb:mem:.;sql.syntax_mys=true");
        database.execute("CREATE TABLE IF NOT EXISTS `customers` (\n" +
            "  `id`           INT AUTO_INCREMENT,\n" +
            "  `status`       VARCHAR(15)                 NOT NULL,\n" +
            "  `email`        VARCHAR(50)                 NOT NULL UNIQUE,\n" +
            "  `first_name`   VARCHAR(50)                 NOT NULL,\n" +
            "  `last_name`    VARCHAR(50),\n" +
            "  `updated_time` DATETIME                    NOT NULL,\n" +
            "  PRIMARY KEY (`id`)\n" +
            ");");
    }

    @AfterAll
    void cleanupDatabase() {
        database.execute("DROP TABLE customers");
    }

    @Test
    public void testCreate() {
        BOCreateCustomerRequest createCustomerRequest = new BOCreateCustomerRequest();
        createCustomerRequest.email = "kkk1@test.com";
        createCustomerRequest.firstName = "kkki";
        BOCreateCustomerResponse createCustomerResponse = customerService.create(createCustomerRequest);
        assertThat(createCustomerResponse.email).isEqualTo("kkk1@test.com");
    }

    @Test
    public void testSearch() {
        BOSearchCustomerRequest request = new BOSearchCustomerRequest();
        BOSearchCustomerResponse response = customerService.search(request);
        assertThat(response.total).isEqualTo(1);
    }
}
