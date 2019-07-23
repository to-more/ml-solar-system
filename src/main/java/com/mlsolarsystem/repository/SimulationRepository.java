package com.mlsolarsystem.repository;

import com.mlsolarsystem.models.Simulation;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tomReq on 7/23/19.
 */
public interface SimulationRepository extends CrudRepository<Simulation, String> {
}
