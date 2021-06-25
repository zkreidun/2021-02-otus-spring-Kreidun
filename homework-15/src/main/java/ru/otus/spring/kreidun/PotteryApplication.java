package ru.otus.spring.kreidun;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;
import ru.otus.spring.kreidun.models.OrderItem;
import ru.otus.spring.kreidun.models.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@IntegrationComponentScan
@SuppressWarnings({"resource", "Duplicates", "InfiniteLoopStatement"})
@ComponentScan
@Configuration
@EnableIntegration
public class PotteryApplication {

    private static final String[] VARIANTS = {"pot", "jug", "bowl", "cup", "tile", "teapot", "candlestick"};

    @Bean
    public QueueChannel itemsChannel() {
        return MessageChannels.queue(20).get();
    }

    @Bean
    public PublishSubscribeChannel productChannel() {
        return MessageChannels.publishSubscribe().get();
    }


    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(3).get();
    }

    @Bean
    public IntegrationFlow potteryFlow() {

        return IntegrationFlows.from("itemsChannel")
                .split()
                .log()
                .handle("potteryWorkshopService", "made")
                .aggregate()
                .channel("productChannel")
                .get();
    }

    public static void main(String[] args) throws Exception {

        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(PotteryApplication.class);
        Workshop workshop = ctx.getBean(Workshop.class);

        while (true) {
            Thread.sleep(1000);
            Collection<OrderItem> items = generateOrderItems();
            System.out.println("New orderItems: " +
                    items.stream().map(OrderItem::getItemName).collect(Collectors.joining(",")));
            Collection<Product> products = workshop.process(items);
            System.out.println("Ready products: " + products.stream().map(Product::getName).collect(Collectors.joining(",")));
        }
    }

    private static OrderItem generateOrderItem() {
        return new OrderItem(VARIANTS[RandomUtils.nextInt(0, VARIANTS.length)]);
    }

    private static Collection<OrderItem> generateOrderItems() {

        List<OrderItem> items = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt(1, 5); ++i) {
            items.add(generateOrderItem());
        }
        return items;
    };
}
