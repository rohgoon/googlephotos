package com.dgit.googlephotos.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.googlephotos.domain.UserVO;

@Repository
public class UserDaoImpl implements UserDao {
	@Autowired
	private SqlSession session;
	
	private static String namespace ="com.dgit.googlephotos.mappers.UserMapper";
	
	@Override
	public void insert(UserVO vo) throws Exception {
		session.insert(namespace+".insert", vo);
	}

	@Override
	public UserVO selectUserByUno(int uno) throws Exception {
		return session.selectOne(namespace+".selectUserByUno", uno);
	}

	@Override
	public UserVO selectUserByInfo(UserVO vo) throws Exception {
		return session.selectOne(namespace+".selectUserByInfo", vo);
	}

	@Override
	public void updateUpath(UserVO vo) throws Exception {
		session.update(namespace+".updateUpath", vo);
	}

}
