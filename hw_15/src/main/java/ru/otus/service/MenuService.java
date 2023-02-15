package ru.otus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.otus.domain.MenuItem;

@Service
public interface MenuService {
    
    List<MenuItem> getMenu();
    
    MenuItem findById(Integer id);

}
