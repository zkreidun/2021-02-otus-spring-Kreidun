package ru.otus.spring.kreidun.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.kreidun.models.OrderItem;
import ru.otus.spring.kreidun.models.Product;

@Service
public class PotteryWorkshopService {

    public Product made(OrderItem orderItem) throws Exception {

        System.out.println("Doing " + orderItem.getItemName());
        Thread.sleep(3000);
        System.out.println("Doing " + orderItem.getItemName() + " done");
        return new Product(orderItem.getItemName());
    }
}
