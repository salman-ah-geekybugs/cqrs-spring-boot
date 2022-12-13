package com.cqrs.demo.controller;

import com.cqrs.demo.model.ProductRestModel;
import com.cqrs.demo.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway){
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getProducts(){
        GetProductsQuery getProductsQuery = new GetProductsQuery();
        List<ProductRestModel> productRestModels =
                queryGateway.query(getProductsQuery,
                        ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();

        return productRestModels;
    }
}
