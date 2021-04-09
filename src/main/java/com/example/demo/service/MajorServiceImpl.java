package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.MajorVO;
import com.example.demo.mapper.MajornMapper;

@Service
public class MajorServiceImpl implements MajorService {

	@Autowired
	MajornMapper majornMapper;
	
	//Read
	@Override
	public List<MajorVO> readMajorList() {
		return majornMapper.readMajorList();
	}
}
