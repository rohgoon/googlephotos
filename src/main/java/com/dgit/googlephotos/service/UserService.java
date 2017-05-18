package com.dgit.googlephotos.service;

import com.dgit.googlephotos.domain.UserVO;

public interface UserService {
	public UserVO login(UserVO vo) throws Exception;
	public void registUser(UserVO vo) throws Exception;
	public int searchId(String uid) throws Exception;
}
