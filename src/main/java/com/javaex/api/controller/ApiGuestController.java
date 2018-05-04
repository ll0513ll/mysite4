package com.javaex.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value="/api/gb")

public class ApiGuestController {
	
	@Autowired
	private GuestService guestService;
	
	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public List<GuestVo> list() {
		System.out.println("ajax-list : guestbook");
		List<GuestVo> list = guestService.list();

		return list;
	}
	@ResponseBody
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public GuestVo add(@RequestBody GuestVo guestVo) {
		
		System.out.println("add");
		System.out.println(guestVo.toString());
		GuestVo vo = guestService.add(guestVo);
		System.out.println("controller"+vo.toString());
		
		return vo;
	}
	
	@ResponseBody
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public int delete(@RequestParam("no")int no,@RequestParam("password")String password){
		
		int result = guestService.delete2(no,password);
		if(result!=0) {
			return no;
		}else {
			return 0;
		}
		
	}
}
