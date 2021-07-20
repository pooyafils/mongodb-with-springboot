package com.example.mongo.repository;

import com.example.mongo.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends MongoRepository<Team, String> {
Optional<Team> findById(String id);
}