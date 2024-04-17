package com.ashokit.service;

import java.util.List;

import com.ashokit.dto.Dashboard;
import com.ashokit.entity.Enquiry;

public interface IEnquiryService {
	
	public Dashboard getDashboard(Integer counsellors_id);
	
	public boolean addEnquiry(Enquiry enquiry,Integer counsellors_id);
	
	public List<Enquiry> getEnquiries(Enquiry enquiry,Integer counsellors_id);
	
	public Enquiry getEnquiry(Integer Enquiry_id);

}
