package com.example.demo.bean;

import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserVO {
	
	private int id;
	private String name;
	private String email;
	private String contact;
	private int major_id;
	private int provider_id;
	private int isAdmin;
	private String role;
	private Timestamp reg_date;
	
	private String provider; //google
}
