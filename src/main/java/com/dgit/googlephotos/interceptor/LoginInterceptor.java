package com.dgit.googlephotos.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dgit.googlephotos.interceptor.LoginInterceptor;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	public static final String LOGIN = "login";	
	private static final Logger LOGGER =  LoggerFactory.getLogger(LoginInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.info("pre Handle..........");
		
		//세션 안에 유저 로그인 정보 삭제
		HttpSession session = request.getSession();
		if (session.getAttribute(LOGIN) != null) {
			session.removeAttribute(LOGIN);
			LOGGER.info("clear login data in pre Handler..........");
		}
		
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.info("post Handle..........");
		HttpSession session = request.getSession();
		LOGGER.info("modelAndView:" +modelAndView+"..........");
		LOGGER.info("getModel : " +modelAndView.getModel()+"..........");
		Object userVO = modelAndView.getModel().get("userVO");
		if (userVO != null) {
			LOGGER.info("new login success..........");
			session.setAttribute(LOGIN, userVO);
			Object dest = session.getAttribute("dest");
			String path = dest!=null?(String)dest:request.getContextPath();			
			response.sendRedirect(path); //home
			
		}

	}
	
}
