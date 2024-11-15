package com.srltas.runtogether.adapter.out.session.converter;

import com.srltas.runtogether.adapter.out.session.UserSessionDTO;
import com.srltas.runtogether.adapter.out.session.dao.UserSessionDAO;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserSessionConverter {

	public UserSessionDAO toUserSessionDAO(UserSessionDTO userSessionDTO) {
		return UserSessionDAO.builder()
			.userId(userSessionDTO.userId())
			.build();
	}

	public UserSessionDTO toUserSessionDTO(UserSessionDAO userSessionDAO) {
		return UserSessionDTO.builder()
			.userId(userSessionDAO.userId())
			.build();
	}
}
