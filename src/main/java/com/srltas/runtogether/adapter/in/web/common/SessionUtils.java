package com.srltas.runtogether.adapter.in.web.common;

import static com.srltas.runtogether.adapter.in.web.common.SessionAttribute.*;

import com.srltas.runtogether.adapter.out.session.UserSession;

import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SessionUtils {

	public UserSession getUserSession(HttpSession session) {
		return (UserSession)session.getAttribute(USER_SESSION);
	}
}
