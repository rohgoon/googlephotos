package com.dgit.googlephotos.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static String makeThumbnail(String uploadPath, String filename) throws IOException{
		String thumbnailName = "";
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath, filename));//원본 가져오기
		
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 200);//썸네일 이미지 데이터 만들기
		
		thumbnailName = uploadPath+"/s_"+filename; //s_일시_시간_원본파일명
		File newFile = new File(thumbnailName);
		String format = filename.substring(filename.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, format.toUpperCase(), newFile);
		
		return "s_"+filename;
		
	}
	public static String uploadFile(String uploadPath, String originalName, String uid, byte[] fileData) throws Exception{
		
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
		String dateStr = sdf.format(date); 
		String savedName = dateStr+"_"+originalName;
		
		File target = new File(uploadPath+uid, savedName); //외부 경로에 파일 생성
		FileCopyUtils.copy(fileData, target);//업로드
		
		String thumbFile = UploadFileUtils.makeThumbnail(uploadPath+uid, savedName);
		
		return uid+"/"+thumbFile;
		
	}
	
	public static void makeDir(String uploadPath, String... paths) throws Exception{
		for (String p : paths) {
			File dirPath = new File(uploadPath+p);
			if (!dirPath.exists()) {
				dirPath.mkdirs();
			}
		}
	}
}
