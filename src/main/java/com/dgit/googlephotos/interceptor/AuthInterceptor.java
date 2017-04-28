package com.dgit.googlephotos.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dgit.googlephotos.interceptor.AuthInterceptor;
import com.dgit.googlephotos.interceptor.LoginInterceptor;

public class AuthInterceptor extends HandlerInterceptorAdapter{
	private static final Logger LOGGER =  LoggerFactory.getLogger(AuthInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.info("pre Handle..........");
		
		//세션에 들어있는 로그인 정보를 받아서 있으면 그대로 실행 없으면 로그인 화면으로 이동
		HttpSession session = request.getSession();
		Object login = session.getAttribute(LoginInterceptor.LOGIN);
		if (login == null) {
			LOGGER.info("go login..........");
			saveDest(request);
			response.sendRedirect(request.getContextPath()+"/");
			request.setAttribute("login", false);
			return false;
		}	
		request.setAttribute("login", true);
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		LOGGER.info("post Handle..........");		
	}
	private void saveDest(HttpServletRequest req){
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		if (query == null || query.equals("null")) {
			query = "";
		} else {
			query = "?"+query;
		}
		if (req.getMethod().equals("GET")) {
			LOGGER.info("dest : "+(uri+query));
			//매개변수가 있다면 매개변수 값까지 모두 기억해둔다.
			req.getSession().setAttribute("dest", uri+query);
			
		}
		
	}
}
