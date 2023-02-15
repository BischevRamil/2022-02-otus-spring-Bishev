package ru.otus.service;

import ru.otus.domain.Pizza;
import ru.otus.domain.MenuItem;

public interface KitchenService {

    Pizza process(MenuItem menuItem);
}
