package com.cqrs.demo.projection;

import com.cqrs.demo.entity.Product;
import com.cqrs.demo.model.ProductRestModel;
import com.cqrs.demo.queries.GetProductsQuery;
import com.cqrs.demo.repository.ProductRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProductProjection {
    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery){
        List<Product> products = productRepository.findAll();
        List<ProductRestModel> productRestModels = products.stream().map(product -> ProductRestModel.builder()
                .name(product.getName()).price(product.getPrice()).quantity(product.getQuantity()).build())
                .collect(Collectors.toList());
        return productRestModels;
    }
}
