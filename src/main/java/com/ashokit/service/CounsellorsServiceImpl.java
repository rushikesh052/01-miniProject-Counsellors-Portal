package com.ashokit.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.entity.Counsellors;
import com.ashokit.repository.ICounsellorsRepo;

@Service
public class CounsellorsServiceImpl implements CounsellorsService{

	
	@Autowired
	private ICounsellorsRepo repo;
	@Override
	public boolean saveCounsellors(Counsellors counsellors) {
		
		Counsellors findByEmail= repo.findByEmail(counsellors.getEmail());
		if(findByEmail !=null)
		{
			return false;
		}
		else {
		return repo.save(counsellors).getCounsellors_id() != null;
		}
	}
	
	
	
	@Override
	public Counsellors getCounsellors(String email, String password) {
		
		return repo.findByEmailAndPassword(email, password);
		
		 
	}
}
