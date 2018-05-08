package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/*@RequestMapping(value = "/list",method = RequestMethod.GET)
	public String list(Model model) {
		
		List<BoardVo> list = boardService.getList();
		model.addAttribute("allList",list);
		System.out.println("list");
		return "board/list";
	}*/
	
	@RequestMapping(value="/list", method= {RequestMethod.GET,RequestMethod.POST})
	public String list( @RequestParam( value="crtPage", required=false, defaultValue="1") Integer crtPage,
			            @RequestParam( value="kwd", required=false, defaultValue="") String kwd,
						Model model) {
		Map<String, Object> bMap = boardService.getList(crtPage, kwd);
		model.addAttribute("bMap", bMap);
		System.out.println("list");
		return "board/list";
	}
	
	@RequestMapping(value="/write",method=RequestMethod.GET)
	public String write() {
		System.out.println("글쓰기");
		return "board/write";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(@ModelAttribute BoardVo boardVo) {
		
		boardService.add(boardVo);
	
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public String view(@RequestParam("no")int no,Model model) {
		
		BoardVo boardVo = boardService.view(no);
		model.addAttribute("view", boardVo);
		return "board/view";
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public String modify(@RequestParam("no")int no,Model model) {
		
		BoardVo boardVo =boardService.search(no);
		model.addAttribute("selectNo",boardVo);
		return "board/modify";
	}
	
	@RequestMapping(value="/modifyform")
	public String modifyform(@ModelAttribute BoardVo boardVo) {
		System.out.println(boardVo.toString());
		boardService.modify(boardVo);
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/searchwhere",method=RequestMethod.POST)
	public String searchwhere(@RequestParam("kwd")String kwd,Model model) {
		System.out.println(kwd);
		List<BoardVo> list = boardService.searchwhere(kwd);
		System.out.println(1);
		model.addAttribute("allList",list);
		return "board/list";
	}

	@RequestMapping(value="/delete")
	public String delete(@RequestParam("no")int no) {
		boardService.delete(no);
		return "redirect:/board/list";
		
	}
	
	/*@RequestMapping(value="/frontPage",method=RequestMethod.GET)
	public String frontPage() {
		
		boardService.frontPage();
		
		return "redirect:/board/list";
		
	}*/
}


