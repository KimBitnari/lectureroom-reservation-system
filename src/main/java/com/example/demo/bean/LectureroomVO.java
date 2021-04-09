package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LectureroomVO {

	private int id;
	private int room_number;
	private int people_number;
	private int projector;
}
