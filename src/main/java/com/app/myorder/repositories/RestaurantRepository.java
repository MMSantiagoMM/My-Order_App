package com.app.myorder.repositories;

import com.app.myorder.entities.Restaurant;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, UUID> {
}
