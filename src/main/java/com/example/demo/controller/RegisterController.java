package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bean.MajorVO;
import com.example.demo.bean.UserVO;
import com.example.demo.service.MajorService;
import com.example.demo.service.UserService;

@Controller
public class RegisterController {

	@Autowired
	UserService userService;
	@Autowired
	MajorService majorService;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	private String register(@ModelAttribute("user") UserVO user, Model model) {
		List<MajorVO> majorList = new ArrayList<>();
		
		majorList = majorService.readMajorList();
		model.addAttribute("majorList", majorList);
		
		return "register";
	}
	
	@RequestMapping(value = "/registerUserinfo")
	private String registerUserinfo(@ModelAttribute UserVO user, HttpServletRequest httpServeletRequest) {

		userService.createUserinfo(user);
		
		UserVO u = userService.readUserByEmail(user.getEmail());
		user.setId(u.getId());
		httpServeletRequest.getSession().setAttribute("user", user);

		return "redirect:/home";
	}
}
