package com.example.demo.bean;

import java.sql.Time;
import java.sql.Date;
import java.sql.Timestamp;
//import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationVO {

	private int id;
	private int User_id;
	private int room_id;
	private Date period;
	private String start_time;
	private String end_time;
	private String purpose;
	private String content;
	private int status;
	private Timestamp reg_date;
	
	
	private String user_name;
	private String user_contact;
	private int major_id;
	private String majorName;
	private int room_name;
	private String state;
}
