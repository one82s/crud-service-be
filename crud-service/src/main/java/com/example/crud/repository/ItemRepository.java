package com.example.crud.repository;


import com.example.crud.model.Item;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository <Item, ObjectId> {
	

}
