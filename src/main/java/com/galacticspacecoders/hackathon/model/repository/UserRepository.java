package com.galacticspacecoders.hackathon.model.repository;

import com.galacticspacecoders.hackathon.model.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByEmail(String email);
}
