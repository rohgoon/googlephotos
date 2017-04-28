package com.dgit.googlephotos.persistence;

import com.dgit.googlephotos.domain.UserVO;

public interface UserDao {
	public void insert(UserVO vo) throws Exception;
	public UserVO selectUser(int uno) throws Exception;
}
