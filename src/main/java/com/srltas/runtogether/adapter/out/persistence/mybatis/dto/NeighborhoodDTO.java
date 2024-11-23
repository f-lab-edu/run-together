package com.srltas.runtogether.adapter.out.persistence.mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NeighborhoodDTO {
	private String id;
	private String name;
	private LocationDTO locationDTO;
	private double boundaryRadius;
}
