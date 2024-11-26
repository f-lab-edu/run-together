package com.srltas.runtogether.adapter.out.session.converter;

import com.srltas.runtogether.adapter.out.session.UserSession;
import com.srltas.runtogether.adapter.out.session.dto.UserSessionDTO;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserSessionConverter {

	public UserSessionDTO toUserSessionDTO(UserSession userSession) {
		return UserSessionDTO.builder()
			.userId(userSession.userId())
			.build();
	}

	public UserSession toUserSession(UserSessionDTO userSessionDTO) {
		return UserSession.builder()
			.userId(userSessionDTO.userId())
			.build();
	}
}
