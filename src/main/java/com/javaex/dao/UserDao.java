package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;

	public int insert(UserVo userVo) {

		return sqlSession.insert("user.insert", userVo);

	}

	public UserVo getUser(String email, String password) {

		Map<String, String> userMap = new HashMap<String, String>();
		userMap.put("email", email);
		userMap.put("password", password);
		return  sqlSession.selectOne("user.selectUserByEmailPw", userMap);
		
	}
	
	public List<UserVo> getList(String  no) {
		
		int num = Integer.parseInt(no); 		
		return sqlSession.selectList("user.getList",num);
	
	}
	public boolean modify(UserVo userVo) {
	
		boolean flag=false;
		if(sqlSession.update("user.modify",userVo)!=0) {
			flag=true;
		}
		return flag;
	}
	
	public boolean idCheck(String email) {
		
		boolean flag=false;
		
		if(sqlSession.selectOne("user.idCheck",email)!=null) {
			flag=true;
		}
		return flag;
	}
}