package com.srltas.runtogether.adapter.out.persistence.mybatis.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NeighborhoodDAO {
	private String id;
	private String name;
	private LocationDAO locationDAO;
	private double boundaryRadius;
}
