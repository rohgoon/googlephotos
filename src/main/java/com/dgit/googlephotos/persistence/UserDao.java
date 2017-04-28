package com.dgit.googlephotos.persistence;

import com.dgit.googlephotos.domain.UserVO;

public interface UserDao {
	public void insert(UserVO vo) throws Exception;
	public UserVO selectUserByUno(int uno) throws Exception;
	public UserVO selectUserByInfo(UserVO vo) throws Exception;
	public void updateUpath(UserVO vo) throws Exception;
	public UserVO login(UserVO vo) throws Exception;
}
