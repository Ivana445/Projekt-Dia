package com.example.demo.Service;


import com.example.demo.Perzistent.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
}
