package com.dgit.googlephotos;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dgit.googlephotos.domain.UserVO;
import com.dgit.googlephotos.interceptor.LoginInterceptor;
import com.dgit.googlephotos.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Inject
	private UserService service;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") UserVO dto){
		logger.info("login GET....");
		
	}
	@RequestMapping(value="/loginPost", method=RequestMethod.POST)
	public void loginPOST(UserVO dto, Model model) throws Exception{
		logger.info("login POST....");
		UserVO vo = service.login(dto);
		if (vo == null) {
			return;
		}
		model.addAttribute("userVO", vo);
	}
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logoutGET(@ModelAttribute("dto") UserVO dto,HttpServletResponse response,HttpServletRequest request) throws IOException{
		logger.info("logout GET....");
		
		HttpSession session = request.getSession();
		if (session.getAttribute(LoginInterceptor.LOGIN) != null) {
			session.removeAttribute(LoginInterceptor.LOGIN);
			//session.invalidate();
			logger.info("clear login data..........");
			response.sendRedirect(request.getContextPath()+"/");
		}
	}
}
