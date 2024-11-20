package com.srltas.runtogether.adapter.out.persistence.mybatis.converter;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.LocationDAO;
import com.srltas.runtogether.adapter.out.persistence.mybatis.dao.NeighborhoodDAO;
import com.srltas.runtogether.domain.model.neighborhood.Location;
import com.srltas.runtogether.domain.model.neighborhood.Neighborhood;
import com.srltas.runtogether.testutil.TestIdGenerator;

class NeighborhoodConverterTest {

	@Test
	@DisplayName("NeighborhoodDAO -> Neighborhood")
	void shouldConvertNeighborhoodDAOtoNeighborhood() {
		String neighborhoodId = TestIdGenerator.generateNeighborhoodId();
		String neighborhoodName = "Test_Neighborhood";
		Location mockLocation = mock(Location.class);

		NeighborhoodDAO neighborhoodDAO = new NeighborhoodDAO(
			neighborhoodId, neighborhoodName, mock(LocationDAO.class), 10.0);
		Neighborhood expectedNeighborhood = new Neighborhood(
			neighborhoodId, neighborhoodName, mockLocation, 10.0);

		Neighborhood resultNeighborhood;
		try(MockedStatic<LocationConverter> locationConverterMock = mockStatic(LocationConverter.class)) {
			locationConverterMock.when(() -> LocationConverter.toLocation(any(LocationDAO.class)))
				.thenReturn(mockLocation);

			resultNeighborhood = NeighborhoodConverter.toNeighborhood(neighborhoodDAO);
		}

		assertThat(resultNeighborhood, is(expectedNeighborhood));
	}
}
