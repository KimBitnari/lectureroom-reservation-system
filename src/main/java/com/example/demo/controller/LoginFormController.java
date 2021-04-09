package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginFormController {

//	2가지 type  
//	@RequestMapping("/test/oauth/login")
//	private @ResponseBody String testOAuthLogin(Authentication authentication) {		//DI(의존성 주입)
//		System.out.println("/test/oauth/login ============");
//		
//		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal(); 
//		System.out.println("authentication: " + oAuth2User.getAttributes());
//		
//		return "세션정보 확인하기";
//	}
//	
//	@RequestMapping("/test/oauth/login")
//	private @ResponseBody String testOAuthLogin(@AuthenticationPrincipal OAuth2User oAuth2User) {		//DI(의존성 주입)
//		System.out.println("/test/oauth/login ============");
//
//		System.out.println("authentication: " + oAuth2User.getAttributes());
//		
//		return "세션정보 확인하기";
//	}
	
	//Create
	@RequestMapping("/loginForm")
	private String loginForm() {
		return "loginForm";
	}
}
