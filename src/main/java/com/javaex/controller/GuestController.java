package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;


@Controller
@RequestMapping("/guestbook")
public class GuestController {
	
	@Autowired
	private GuestService guestService;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) {
		System.out.println("gb/list controller");
		List<GuestVo> list = guestService.list();
		System.out.println(list.toString());
		model.addAttribute("userList", list);
		return "guestbook/list";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(@ModelAttribute GuestVo guestVo) {
		guestService.insert(guestVo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("no")int no,Model model) {
		model.addAttribute("selectNo",no);
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value="/deleteform",method=RequestMethod.POST)
	public String deleteform(@ModelAttribute GuestVo guestVo) {
		
		guestService.delete(guestVo);
		return "redirect:/guestbook/list";
	}

}
