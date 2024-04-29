package com.ashokit.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ashokit.entity.Enquiry;

@Repository
public interface IEnquiryRepo extends JpaRepository<Enquiry, Integer>{
	

	
	
//	@Query("from Enquiry where counsellors.counsellors_id=:counsellorsId")
//	public List<Enquiry> getEnquiries(Integer counsellorsId);	
	
	
	@Query(value="select count(*) from enquiry where counsellors_id=:counsellorsId",nativeQuery = true)
	public Long getEnquiries(Integer counsellorsId);
	
	@Query(value="select count(*) from enquiry where counsellors_id=:counsellorsId and status=:status",nativeQuery = true)
	public Long getEnquiries(Integer counsellorsId,String status);

	
}