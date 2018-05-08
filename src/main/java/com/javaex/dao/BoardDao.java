package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public List<BoardVo> selectAll(int crtPage, String kwd, int startRnum, int endRnum){
		Map<String, Object> mapCri = new HashMap<String, Object>();
		mapCri.put("crtPage", crtPage);
		mapCri.put("kwd", kwd);
		mapCri.put("startRnum", startRnum);
		mapCri.put("endRnum", endRnum);
		System.out.println("dao cri" + mapCri.toString());
		return sqlSession.selectList("board.selectAll", mapCri);
	}
	
	public int selectTotalCount(String kwd){
		System.out.println("dao kwd" + kwd);
		return sqlSession.selectOne("board.totalCount", kwd);
	}
	
	public List<BoardVo> getList() {
		
		return sqlSession.selectList("board.list");
	}
	
	public void hitup(int no){
		sqlSession.update("board.hitup",no);
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
