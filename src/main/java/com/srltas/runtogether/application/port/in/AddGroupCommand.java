package com.srltas.runtogether.application.port.in;

public record AddGroupCommand(
	String name,
	String description,
	String neighborhoodId,
	String createByUserId
) {
}
