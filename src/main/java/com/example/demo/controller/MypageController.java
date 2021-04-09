package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.bean.LectureroomVO;
import com.example.demo.bean.MajorVO;
import com.example.demo.bean.UserVO;
import com.example.demo.config.oauth.PrincipalDetails;
import com.example.demo.service.MajorService;
import com.example.demo.service.UserService;

@Controller
public class MypageController {

	@Autowired
	UserService userService;
	@Autowired
	MajorService majorService;
	
	@RequestMapping("/mypage")
	private String myInfo(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		UserVO user = principalDetails.getUserVO();
		UserVO u = userService.readUserByEmail(user.getEmail());
		
		model.addAttribute("user", u);
		
		if(u.getRole().equals("ROLE_ADMIN")) model.addAttribute("header", "./inc/adminHeader");
		else model.addAttribute("header", "./inc/logoutHeader");
		
		List<MajorVO> majorList = new ArrayList<>();
		
		majorList = majorService.readMajorList();
		
		for(int j=0;j<majorList.size();j++) {
			if(u.getMajor_id() == majorList.get(j).getId()) model.addAttribute("majorName", majorList.get(j).getName());
		}
		
		return "mypage";
	}
	
	//Update
	@RequestMapping("/update/{id}")
	private String fileBoardUpdateForm(@PathVariable("id") int id, Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
		UserVO user = principalDetails.getUserVO();
		UserVO u = userService.readUserByEmail(user.getEmail());
		
		model.addAttribute("user", u);
		
		if(u.getRole().equals("ROLE_ADMIN")) model.addAttribute("header", "./inc/adminHeader");
		else model.addAttribute("header", "./inc/logoutHeader");
		
		model.addAttribute("user", userService.readUserById(id));
		
		List<MajorVO> majorList = new ArrayList<>();
		
		majorList = majorService.readMajorList();
		
		for(int j=0;j<majorList.size();j++) {
			if(u.getMajor_id() == majorList.get(j).getId()) model.addAttribute("majorName", majorList.get(j).getName());
		}
		
		model.addAttribute("majorList", majorList);
		
		return "update";
	}
	@RequestMapping("/updateProc")
	private String fileBoardUpdateProc(@ModelAttribute("userVO") UserVO userVO) {
		userService.updateUser(userVO);
		
		return "redirect:/mypage";
	}
}
