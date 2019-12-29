package app.demo;

import app.demo.product.domain.Product;
import app.demo.product.domain.ProductAggregateView;
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
        mongo.view(ProductAggregateView.class);

        bind(ProductService.class);
        test();
    }

    private void test() {
        ProductService productService = bean(ProductService.class);

        List<ProductAggregateView> aggregate = productService.aggregate();

        /*ProductView productView = new ProductView();
        productView.title = "this is product title";
        productView.description = "this is product description";

        String id = productService.create(productView);
        Product product = productService.get(id);
        product.title = "replace title";
        productService.replace(product);
        productService.update("replace title", "new title");
        List<Product> productList = productService.findByTitle("title");
        List<Product> activeList = productService.findByStatus(ProductStatus.ACTIVE);
        List<Product> inactiveList = productService.findByStatus(ProductStatus.INACTIVE);
        productService.update("new title", "another new title ");
        //productService.findOneByStatus(ProductStatus.ACTIVE);
        //productService.findOneByStatus(ProductStatus.INACTIVE);*/
    }
}
