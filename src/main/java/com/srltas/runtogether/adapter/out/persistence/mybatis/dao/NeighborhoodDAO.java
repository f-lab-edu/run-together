package com.srltas.runtogether.adapter.out.persistence.mybatis.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NeighborhoodDAO {
	private String id;
	private String name;
	private LocationDAO locationDAO;
	private double boundaryRadius;
}
