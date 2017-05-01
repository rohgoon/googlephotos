package com.dgit.googlephotos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dgit.googlephotos.domain.UserVO;
import com.dgit.googlephotos.persistence.UserDao;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao dao;

	@Override
	public UserVO login(UserVO vo) throws Exception {
		
		return dao.login(vo);
	}

	@Override
	public void registUser(UserVO vo) throws Exception {
		dao.insert(vo);
	}

	@Override
	public int searchId(String uid) throws Exception {
		return dao.searchId(uid);
	}
}
