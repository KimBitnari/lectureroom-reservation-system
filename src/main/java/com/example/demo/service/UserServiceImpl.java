package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.UserVO;
import com.example.demo.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	//Create
	@Override
	public int createUserinfo(UserVO user) {
		return userMapper.createUserinfo(user);
	}
	
	
	//Read
	@Override
	public UserVO readUserByEmail(String email) {
		return userMapper.readUserByEmail(email);
	}
	@Override
	public int readIsAdminByUserID(int id) {
		return userMapper.readIsAdminByUserID(id);
	}
	@Override
	public UserVO readUserById(int id) {
		return userMapper.readUserById(id);
	}
	@Override
	public List<UserVO> readUserList() {
		return userMapper.readUserList();
	}
	
	
	//Update
	@Override
	public int updateUser(UserVO userVO) {
		return userMapper.updateUser(userVO);
	}
	@Override
	public void updateUserRole(List<UserVO> userList) {
		for(int i=0;i<userList.size();i++) {
			userMapper.updateUserRole(userList.get(i));
		}
	}
}
