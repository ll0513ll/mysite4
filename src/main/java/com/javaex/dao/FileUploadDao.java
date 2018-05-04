package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.FileVo;

@Repository
public class FileUploadDao {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(FileVo fileVo) {
		System.out.println("insert dao");
		sqlSession.insert("file.insert",fileVo);
		
	}
	
	public List<FileVo> getList(){
		System.out.println("list Dao");
		return sqlSession.selectList("file.list");
	}
	
	public void delete(int no) {
		
		sqlSession.delete("file.delete",no);
	}
}
