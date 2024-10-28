package com.srltas.runtogether.adapter.in.web.common;

import static com.srltas.runtogether.adapter.in.web.common.SessionAttribute.*;

import com.srltas.runtogether.adapter.out.session.UserSessionDTO;

import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SessionUtils {

	public UserSessionDTO getUserSessionDTO(HttpSession session) {
		return (UserSessionDTO)session.getAttribute(USER_SESSION);
	}
}
