package ru.otus.integration;

import java.util.List;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import ru.otus.domain.Pizza;
import ru.otus.domain.MenuItem;

@MessagingGateway
public interface PizzeriaGateway {

    @Gateway(requestChannel = "ordersChannel", replyChannel = "dishesChannel")
    List<Pizza> processOrder(List<MenuItem> items);
}
