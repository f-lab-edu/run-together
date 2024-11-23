package com.srltas.runtogether.adapter.out.persistence.mybatis.converter;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dto.LocationDTO;
import com.srltas.runtogether.domain.model.neighborhood.Location;

class LocationConverterTest {

	@Test
	@DisplayName("LocationDTO -> Location")
	void shouldConvertLocationDTOtoLocation() {
		LocationDTO dto = new LocationDTO(37.497947, 127.027632);
		Location expectedLocation = new Location(37.497947, 127.027632);

		Location resultLocation = LocationConverter.toLocation(dto);

		assertThat(resultLocation, is(expectedLocation));
	}
}
