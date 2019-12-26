package app.demo.product.service;

import app.demo.api.product.ProductView;
import app.demo.product.domain.Product;
import app.demo.product.domain.ProductStatus;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import core.framework.inject.Inject;
import core.framework.mongo.MongoCollection;
import core.framework.mongo.Query;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author kimi
 */
public class ProductService {
    @Inject
    MongoCollection<Product> productCollection;

    public String create(ProductView productView) {
        Product product = new Product();
        product.id = UUID.randomUUID().toString();
        product.title = productView.title;
        product.description = productView.description;
        product.status = ProductStatus.ACTIVE;
        product.createTime = ZonedDateTime.now();
        productCollection.insert(product);
        return product.id;
    }

    public Product get(String id) {
        return productCollection.get(id).orElseThrow();
    }

    public void update(String id, String title) {
        Optional<Product> product = productCollection.get(id);
        product.ifPresent(p -> {
            long updatedCount = productCollection.update(Filters.eq("_id", id), Updates.set("title", title));
        });
    }

    public List<Product> findByStatus(ProductStatus status) {
        return productCollection.find(Filters.eq("status", status));
    }

    public Product findOneByStatus(ProductStatus status) {
        return productCollection.findOne(Filters.eq("status", status)).orElseThrow();
    }

    public List<Product> findByTitle(String title) {
        Query query = new Query();
        query.filter = Filters.regex("title", title);
        query.limit = 10;
        return productCollection.find(query);
    }
}
