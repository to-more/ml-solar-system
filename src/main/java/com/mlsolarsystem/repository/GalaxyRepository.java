package com.mlsolarsystem.repository;

import com.mlsolarsystem.models.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by tom
 */
public interface GalaxyRepository extends MongoRepository<Planet, String>{
}
