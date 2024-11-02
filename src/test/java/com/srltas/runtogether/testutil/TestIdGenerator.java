package com.srltas.runtogether.testutil;

import java.util.UUID;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TestIdGenerator {

	public String generateUserId() {
		return "test_usr_" + UUID.randomUUID();
	}

	public String generateNeighborhoodId() {
		return "test_neighborhood_" + UUID.randomUUID();
	}
}
