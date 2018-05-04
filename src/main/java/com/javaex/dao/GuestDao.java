package com.javaex.dao;

import java.util.List;
import java.util.Map;

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
	public int insert2(GuestVo guestVo) {
		System.out.println(guestVo.toString());
		sqlSession.insert("guest.insert2",guestVo);
		System.out.println(guestVo.toString());
		return guestVo.getNo();
	}
	
	public GuestVo list2(int no) {
		
		return sqlSession.selectOne("guest.list2",no);
	}
	
	public int delete2(Map map) {
		
		return sqlSession.delete("guest.delete2",map);	
		
		}
}