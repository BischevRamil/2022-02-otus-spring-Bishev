package ru.otus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.otus.domain.Pizza;

@Service
public interface OrderService {

    List<Pizza> processOrder();
}
