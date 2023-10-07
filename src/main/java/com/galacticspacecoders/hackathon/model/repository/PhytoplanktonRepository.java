package com.galacticspacecoders.hackathon.model.repository;

import com.galacticspacecoders.hackathon.model.entity.Phytoplankton;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhytoplanktonRepository extends MongoRepository<Phytoplankton, ObjectId> {

}
