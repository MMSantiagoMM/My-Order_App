package com.app.myorder.repositories;

import com.app.myorder.entities.Pub;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PubRepository extends MongoRepository<Pub, UUID> {
}
