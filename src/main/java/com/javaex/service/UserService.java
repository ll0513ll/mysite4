package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public int join(UserVo userVo) {
		return userDao.insert(userVo);
	}
	
	public UserVo login(String email, String password) {
		return userDao.getUser(email,password);
		
	}
	
	public List<UserVo> getList(String no) {
		
		return userDao.getList(no);
	
	}
	
	public boolean modifyform(UserVo userVo) {
		
		return userDao.modify(userVo);
	}

	public boolean idCheck(String email) {

		return userDao.idCheck(email);
	}

}
