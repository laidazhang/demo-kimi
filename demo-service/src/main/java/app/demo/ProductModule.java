package app.demo;

import app.demo.api.product.ProductView;
import app.demo.product.domain.Product;
import app.demo.product.domain.ProductStatus;
import app.demo.product.service.ProductService;
import core.framework.module.Module;
import core.framework.mongo.module.MongoConfig;

import java.util.List;

/**
 * @author kimi
 */
public class ProductModule extends Module {
    @Override
    protected void initialize() {
        MongoConfig mongo = config(MongoConfig.class);
        mongo.uri(requiredProperty("sys.mongo.uri"));

        mongo.collection(Product.class);

        bind(ProductService.class);
        test();
    }

    private void test() {
        ProductService productService = bean(ProductService.class);

        ProductView productView = new ProductView();
        productView.title = "this is product title";
        productView.description = "this is product description";

        String id = productService.create(productView);
        Product product = productService.get(id);
        productService.update(id, "title has been updated");
        List<Product> productList = productService.findByTitle("title");
        List<Product> activeList = productService.findByStatus(ProductStatus.ACTIVE);
        List<Product> inactiveList = productService.findByStatus(ProductStatus.INACTIVE);
        //productService.findOneByStatus(ProductStatus.ACTIVE);
        productService.findOneByStatus(ProductStatus.INACTIVE);
    }
}
