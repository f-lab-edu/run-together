package com.srltas.runtogether.adapter.out.persistence.mybatis;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.srltas.runtogether.adapter.out.persistence.mybatis.converter.NeighborhoodConverter;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.domain.model.neighborhood.NeighborhoodRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MybatisNeighborhoodRepository implements NeighborhoodRepository {

	private final MybatisNeighborhoodMapper mapper;

	@Override
	public Optional<Neighborhood> findById(String neighborhoodId) {
		return mapper.findById(neighborhoodId).map(NeighborhoodConverter::toNeighborhood);
	}
}
