package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.bean.LectureroomVO;

@Mapper
public interface LectureroomnMapper {

	//Read
	List<LectureroomVO> readRoomList();
}
