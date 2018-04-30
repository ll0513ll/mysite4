package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> getList(){
		
		return boardDao.getList();
		
	}
	
	public void add(BoardVo boardVo) {
		
		boardDao.add(boardVo);
	}
	
	public BoardVo view(int no) {
		
		return boardDao.view(no);
	}
	
	public BoardVo search(int no) {
		
		return boardDao.search(no);
	}
	
	public void modify(BoardVo boardVo) {
		System.out.println(boardVo.toString());
		 boardDao.modify(boardVo);
	}
	
	public List<BoardVo> searchwhere(String kwd){
		
		System.out.println(2);
		System.out.println(boardDao.searchwhere(kwd));
		return boardDao.searchwhere(kwd);
	}

	public void delete(int no) {

		boardDao.delete(no);
	}
	
	/*public List<BoardVo> frontPage() {
		 
		boardDao.getList();
		
	}*/

}
