package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestDao;
import com.javaex.vo.GuestVo;
@Service
public class GuestService {
	
	@Autowired
	private GuestDao guestDao;
	
	
	public List<GuestVo> list(){
		System.out.println("gb/list service");
		return guestDao.getList();
	}
	
	public void insert(GuestVo guestVo) {
		
		guestDao.insert(guestVo);
	}
	
	public void delete(GuestVo guestVo) {
		
		guestDao.delete(guestVo);
	}
	
	
}
