package com.srltas.runtogether.adapter.out.persistence.mybatis.converter;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.LocationDTO;
import com.srltas.runtogether.domain.model.neighborhood.Location;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LocationConverter {

	public Location toLocation(LocationDTO dto) {
		return Location.builder()
			.latitude(dto.getLatitude())
			.longitude(dto.getLongitude())
			.build();
	}
}
