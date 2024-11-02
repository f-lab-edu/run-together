package com.srltas.runtogether.domain.model.neighborhood;

import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public interface NeighborhoodRepository {
	Optional<Neighborhood> findById(String neighborhoodId);
}
