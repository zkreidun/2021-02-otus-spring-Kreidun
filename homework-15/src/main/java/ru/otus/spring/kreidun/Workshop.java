package ru.otus.spring.kreidun;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import ru.otus.spring.kreidun.models.OrderItem;
import ru.otus.spring.kreidun.models.Product;

import java.util.Collection;

@MessagingGateway
public interface Workshop {

    @Gateway(requestChannel = "itemsChannel", replyChannel = "productChannel")
    Collection<Product> process(Collection<OrderItem> orderItem);
}

