package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(GuestVo vo) {
		
		sqlSession.insert("guest.insert",vo);
		
	}
		public List<GuestVo> getList() {
			System.out.println("gb/list dao");
			return sqlSession.selectList("guest.list");
			
		}
	public void delete(GuestVo guestVo) {
			
		sqlSession.delete("guest.delete",guestVo);	
		
		}
}