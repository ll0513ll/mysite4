package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;

	public void add(BoardVo boardVo) {
		
		sqlSession.insert("board.insert",boardVo);
	
	}
	
	public List<BoardVo> getList() {
		
		return sqlSession.selectList("board.list");
	}
	
	public BoardVo view(int no) {
		
		return sqlSession.selectOne("board.view",no);
	}
	
	public BoardVo search(int no) {
		
		return sqlSession.selectOne("board.search",no);
		
	}
	public void modify(BoardVo boardVo) {
		
		System.out.println(3);
		sqlSession.update("board.modify",boardVo);
		
	}
	
	public List<BoardVo> searchwhere(String kwd){
		System.out.println(3);
		kwd="%"+kwd+"%";
		return sqlSession.selectList("board.searchTitle",kwd);
	}
	
	public void delete(int no) {

		sqlSession.delete("board.delete",no);
	}
	
	/*public List<BoardVo> frontPage() {

		sqlSession.delete("board.delete",no);
	}*/
	
}
