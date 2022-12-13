package com.cqrs.demo.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
public class CreateProductCommand {
    //Will store the payload/state of action to create a product
    @TargetAggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;

    private Integer quantity;
}
