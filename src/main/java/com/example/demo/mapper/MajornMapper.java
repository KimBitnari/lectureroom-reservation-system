package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.bean.MajorVO;

@Mapper
public interface MajornMapper {

	//Read
	List<MajorVO> readMajorList();
}
