package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.LectureroomVO;
import com.example.demo.mapper.LectureroomnMapper;

@Service
public class LectureroomServiceImpl implements LectureroomService {

	@Autowired
	LectureroomnMapper lectureroomMapper;
	
	//Read
	@Override
	public List<LectureroomVO> readRoomList() {
		return lectureroomMapper.readRoomList();
	}
}
