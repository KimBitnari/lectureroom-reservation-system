package com.example.demo.service;

import java.util.List;

import com.example.demo.bean.UserVO;

public interface UserService {

	//Create
	// 사용자 생성 
	int createUserinfo(UserVO user);
	
	
	//Read
	// 사용자 email를 가지고 사용자 id 출력
	UserVO readUserByEmail(String email);
	// 사용자 id를 가지고 admin 구분 출력
	int readIsAdminByUserID(int id);
	// 사용자 id를 가지고 이름 출력 
	UserVO readUserById(int id);
	// 
	List<UserVO> readUserList();
	
	
	//Update
	// 
	int updateUser(UserVO userVO);
	// 
	void updateUserRole(List<UserVO> userList);
}
