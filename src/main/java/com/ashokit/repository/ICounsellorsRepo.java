package com.ashokit.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.ashokit.entity.Counsellors;

@Repository
public interface ICounsellorsRepo extends JpaRepository<Counsellors,Integer> {
	
    public Counsellors findByEmailAndPassword(String email, String password);
    
    public Counsellors findByEmail(String email);
    
}
