package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/joinform" ,  method = RequestMethod.GET)
	public String joinform() {
		
		System.out.println("joinform");
		return "user/joinform";
	}
	
	@RequestMapping(value = "/join" ,  method = RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("join");
		System.out.println(userVo.toString());
		userService.join(userVo);
		return "user/joinsuccess";
	}
	
	@RequestMapping(value = "/loginform", method = RequestMethod.GET)
	public String loginform() {
		System.out.println("loginform");
		return "user/loginform";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			HttpSession session) {

		UserVo authUser = userService.login(email, password);

		if (authUser != null) {
			session.setAttribute("authUser", authUser);
			return "redirect:/main";
		} else {
			return "redirect:/user/loginform?result=fail";
		}
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.GET)
	public String modify(Model model,HttpSession session) {
		System.out.println("modify");
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		String no = authUser.getNo();
		userService.getList(no);
		model.addAttribute(no);
		return "user/modifyform";
	}
	
	@RequestMapping(value = "/modifyform", method = RequestMethod.GET)
	public String modifyform(@ModelAttribute UserVo userVo,HttpSession session) {
		System.out.println("modifyform");
		if(userService.modifyform(userVo)) {
			UserVo authUser = (UserVo) session.getAttribute("authUser");
			authUser.setName(userVo.getName());	
		}
		return "/main/index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		
		session.removeAttribute("authUser");
		session.invalidate();
		System.out.println("logout");
		return "/main/index";
	}
	@ResponseBody
	@RequestMapping(value="/emailcheck" , method=RequestMethod.POST)
	public boolean exists(@RequestParam("email") String email) {
		System.out.println("ajax 이메일 체크"+ email);
		
		boolean isExists = userService.idCheck(email);
		
		return isExists;
	}

}
