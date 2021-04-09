package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.bean.ReservationVO;
import com.example.demo.bean.UserVO;

@Mapper
public interface UserMapper {

	//Create
	// 사용자 생성 
	int createUserinfo(UserVO user);
		
		
	//Read
	// 사용자 id 출력
	UserVO readUserByEmail(String email);
	// 사용자 id를 가지고 admin 구분 출력
	int readIsAdminByUserID(int id);
	// 사용자 id를 가지고 이름 출력 
	UserVO readUserById(int id);
	//
	List<UserVO> readUserList();
	
	
	//Update
	// 전화번호와 전공 수정
	int updateContAndMajor(ReservationVO room);
	// 전화번호 수정 
	int updateContact(ReservationVO room);
	// 전공 수정 
	int updateMajor(ReservationVO room);
	// 
	int updateUser(UserVO userVO);
	//
	void updateUserRole(UserVO userVO);
}
