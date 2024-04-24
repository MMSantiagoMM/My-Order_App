package com.app.myorder.repositories;

import com.app.myorder.entities.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface RestaurantRepository extends MongoRepository<Restaurant, UUID> {
}
