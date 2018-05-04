package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public GuestVo add(GuestVo guestVo) {
		//insert
		int no = guestDao.insert2(guestVo);
		//select
		return guestDao.list2(no);
	}
	
	public int delete2(int no,String password) {
		
		Map<String,Object> userMap = new HashMap<String,Object>();
		userMap.put("no", no);
		userMap.put("password", password);
		
		return guestDao.delete2(userMap);
	}
	
}
