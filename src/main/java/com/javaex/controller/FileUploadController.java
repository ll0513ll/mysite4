package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.FileUploadService;
import com.javaex.vo.FileVo;

@Controller
@RequestMapping(value="/fileupload")
public class FileUploadController {

	@Autowired
	private FileUploadService fileUploadService;
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file")MultipartFile file,Model model) {
		
		FileVo fileVo = fileUploadService.restore(file);
		model.addAttribute("fileVo", fileVo);
		return "redirect:/fileupload/list";
	}
	
	@RequestMapping(value = "/list",method=RequestMethod.GET)
	public String getList(Model model) {
		System.out.println("list");
		List<FileVo> fileList = fileUploadService.getList();
		System.out.println(fileList.toString());
		model.addAttribute("fileList",fileList);
		return "fileupload/list";
	}
	
	@RequestMapping(value = "/delete",method=RequestMethod.GET)
	public String delete(@RequestParam("no")int no) {
		
		fileUploadService.delete(no);
		return "redirect:/fileupload/list";
	}
}
