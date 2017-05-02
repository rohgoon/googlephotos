package com.dgit.googlephotos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dgit.googlephotos.domain.UserVO;
import com.dgit.googlephotos.interceptor.LoginInterceptor;
import com.dgit.googlephotos.service.UserService;
import com.dgit.googlephotos.util.MediaUtils;
import com.dgit.googlephotos.util.UploadFileUtils;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Inject
	private UserService service;
	@Resource(name="uploadPath") 
	private String uploadPath;
	
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
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginPOST(UserVO dto, Model model,HttpServletResponse response,HttpServletRequest request) throws Exception{
		logger.info("login POST....");
		UserVO vo = service.login(dto);
		
		if (vo == null) {
			logger.info("login is null....");
			return "home";
		}		
		model.addAttribute("userVO", vo);
		logger.info("addAttribute login....");
		//response.sendRedirect(request.getContextPath()+"/");
		//로그인시 폴더 이미지파일 불러오기.
		File f = new File(vo.getUpath()); 
		File [] files = f.listFiles(); //uid+"s_"+filename		
		request.setAttribute("fileList", files);
		return "";
	}
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public void logoutGET(HttpServletResponse response,HttpServletRequest request) throws IOException{
		logger.info("logout GET....");
		
		HttpSession session = request.getSession();
		if (session.getAttribute(LoginInterceptor.LOGIN) != null) {
			session.removeAttribute(LoginInterceptor.LOGIN);
			//session.invalidate();
			logger.info("clear login data..........");
			response.sendRedirect(request.getContextPath()+"/");
		}
	}
	//upload
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public String multiUploadResult(@ModelAttribute("userVo") UserVO vo, List<MultipartFile> files, HttpServletRequest request, Model model) throws Exception{
		File dir = new File(uploadPath + "/"+vo.getUid());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		for (MultipartFile file : files) {
			logger.info(file.getOriginalFilename());
			logger.info(file.getSize()+"");
			logger.info(file.getContentType());
		}		
		logger.info(vo.getUid());		
		
		ArrayList<String> fileNames = new ArrayList<>();
		ArrayList<String> thumbFiles = new ArrayList<>();
		
		for (MultipartFile file : files) {
			
			String thumbFile = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), vo.getUid(), file.getBytes());
			thumbFiles.add(thumbFile);
			
		}
		model.addAttribute("files", fileNames);
		model.addAttribute("thumbFiles", thumbFiles);
		
		return "";
	}
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String filename, String uid) throws IOException{
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		logger.info("[displayFile] filename : "+filename);
		
		try{
			String format = filename.substring(filename.lastIndexOf(".")+1); //파일 확장자만 쓰기
			MediaType mType = MediaUtils.getMediaType(format);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(mType);
			
			in = new FileInputStream(uploadPath+"/"+filename);
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		}finally{
			in.close();
		}
		
		return entity;
		
	}
	//register
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public void registUserPOST(UserVO vo, Model model,HttpServletResponse response,HttpServletRequest request) throws Exception{
		
		File dir = new File(uploadPath + "/"+vo.getUid());
		if (!dir.exists()) {
			dir.mkdirs();
		}
		vo.setUpath(uploadPath+"/"+vo.getUid());		
		
		
		service.registUser(vo);
		response.sendRedirect(request.getContextPath()+"/");
	}
	@RequestMapping(value="/searchId", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, String> searchId(String uid) throws Exception{
		logger.info("searchId GET>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+uid);
		int count = service.searchId(uid);
		Map<String, String> map = new HashMap<>();
		if (count > 0) {
			map.put("checkId", "1");
		}else{
			map.put("checkId", "0");//0일때 사용가능
		}
		
		
		return map;
	}
}
