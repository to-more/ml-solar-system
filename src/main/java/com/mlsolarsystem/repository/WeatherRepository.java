package com.mlsolarsystem.repository;

import com.mlsolarsystem.models.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tom
 */
@Repository
public interface WeatherRepository extends MongoRepository<Weather, String> {

}
