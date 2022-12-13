package com.cqrs.demo.aggregates;

import com.cqrs.demo.commands.CreateProductCommand;
import com.cqrs.demo.events.ProductCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {
    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;

    private Integer quantity;
    @CommandHandler
    public ProductAggregate(CreateProductCommand command){
        // You can perform all the validations over here as well
        //And then finally create the event  here

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        //so whenever a state changes the values are updated
        BeanUtils.copyProperties(command,productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent); //
    }
    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        this.quantity = productCreatedEvent.getQuantity();
        this.name = productCreatedEvent.getName();
        this.productId = productCreatedEvent.getProductId();
        this.price = productCreatedEvent.getPrice();
    }
}
