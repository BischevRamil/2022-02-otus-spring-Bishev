package ru.otus.service;

import org.springframework.stereotype.Service;

import ru.otus.domain.Pizza;
import ru.otus.domain.MenuItem;

@Service
public class PizzaKitchenService implements KitchenService {

    @Override
    public Pizza process(MenuItem menuItem) {
        return Pizza.builder().name(menuItem.getName()).build();
    }

}
