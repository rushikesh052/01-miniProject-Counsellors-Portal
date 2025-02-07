package com.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.dto.Dashboard;
import com.ashokit.entity.Counsellors;
import com.ashokit.entity.Enquiry;
import com.ashokit.repository.ICounsellorsRepo;
import com.ashokit.repository.IEnquiryRepo;


@Service
public class IEnquiryServiceImpl implements IEnquiryService {

	@Autowired
	private IEnquiryRepo repo;
	
	@Autowired
	private ICounsellorsRepo counsRepo;
	

	@Override
	public Dashboard getDashboard(Integer counsellors_id) {
		Long totalEnqus=repo.getEnquiries(counsellors_id);
	    Long openCnt=	repo.getEnquiries(counsellors_id, "new");
		Long lostCnt=repo.getEnquiries(counsellors_id,"lost");
		Long enrolledCnt=repo.getEnquiries(counsellors_id, "enrolled");
		
		Dashboard d=new Dashboard();
		d.setEnrolledEnqs(enrolledCnt);
		d.setOpenEnqs(openCnt);
		d.setLostEnqs(lostCnt);
		d.setTotalEnquiry(totalEnqus);
		return d;
	}

	@Override
	public boolean addEnquiry(Enquiry enquiry,Integer counsellors_id) {
		
		Counsellors counsellors=counsRepo.findById(counsellors_id).orElseThrow();
		enquiry.setCounsellors(counsellors);
		return repo.save(enquiry).getEnquiry_id()!=null;
	}

	@Override
	public List<Enquiry> getEnquiries(Enquiry enquiry, Integer counsellors_id) {

	
		Counsellors counsellors=new Counsellors();
		counsellors.setCounsellors_id(counsellors_id);
		
		Enquiry searchCriteria =new Enquiry();
		searchCriteria.setCounsellors(counsellors);
		if(null!=enquiry.getCourse() &&!"".equals(enquiry.getCourse())){
			searchCriteria.setCourse(enquiry.getCourse());
		}
		if(null != enquiry.getClassMode() &&!"".equals(enquiry.getClassMode())) {
			searchCriteria.setClassMode(enquiry.getClassMode());
		}
		if(null != enquiry.getStatus() && !"".equals(enquiry.getStatus())) {
			searchCriteria.setStatus(enquiry.getStatus());
		}
		
		
		Example<Enquiry> of=Example.of(searchCriteria);
		return repo.findAll(of);
	}
	
	@Override
	public Enquiry getEnquiry(Integer Enquiry_id) {
		return repo.findById(Enquiry_id).orElseThrow();
	}

	@Override
	public String editEnquiry(Enquiry enq) {
		Optional<Enquiry> opt=repo.findById(enq.getEnquiry_id());
		if(opt.isPresent()) {
			repo.save(enq);
			return enq.getEnquiry_id()+" is updated";
		}
		return enq.getEnquiry_id()+" not found for edit";
	}
	
	
}
