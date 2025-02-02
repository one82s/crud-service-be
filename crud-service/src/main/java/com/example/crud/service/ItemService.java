package com.example.crud.service;

import com.example.crud.model.Item;
import com.example.crud.repository.ItemRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(ObjectId id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item updateItem(ObjectId id, Item itemDetails) {
        Item item = itemRepository.findById(id).orElse(null);
        item.setName(itemDetails.getName());
        item.setDescription(itemDetails.getDescription());
        return itemRepository.save(item);
    }

    public void deleteItem(ObjectId id) {
        itemRepository.deleteById(id);
    }

}
