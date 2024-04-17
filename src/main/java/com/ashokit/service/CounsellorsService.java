package com.ashokit.service;


import com.ashokit.entity.Counsellors;

public interface CounsellorsService {

	public boolean saveCounsellors(Counsellors counsellors);
	
	public Counsellors getCounsellors(String email, String password);	
}
