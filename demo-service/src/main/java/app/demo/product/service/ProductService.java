package app.demo.product.service;

import app.demo.api.product.ProductView;
import app.demo.product.domain.Product;
import app.demo.product.domain.ProductAggregateView;
import app.demo.product.domain.ProductStatus;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import core.framework.inject.Inject;
import core.framework.mongo.Aggregate;
import core.framework.mongo.MongoCollection;
import core.framework.mongo.Query;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
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

    public void replace(Product product) {
        productCollection.replace(product);
    }

    public void update(String title, String newTitle) {
        productCollection.update(Filters.eq("title", title), Updates.set("title", newTitle));
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

    public List<ProductAggregateView> aggregate() {
        Aggregate<ProductAggregateView> aggregate = new Aggregate<>();
        aggregate.pipeline = Arrays.asList(
                Aggregates.group("$title", Accumulators.sum("count", 1)),
                Aggregates.sort(Sorts.ascending("_id")));
        aggregate.resultClass = ProductAggregateView.class;
        return productCollection.aggregate(aggregate);
    }


}
