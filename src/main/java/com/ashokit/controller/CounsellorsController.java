package com.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashokit.dto.Dashboard;
import com.ashokit.entity.Counsellors;
import com.ashokit.service.CounsellorsService;
import com.ashokit.service.IEnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorsController {
	
	@Autowired
	private CounsellorsService counsService;
	
	@Autowired
	private IEnquiryService enqService;
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req, Model model) {
		HttpSession session=req.getSession();
		session.invalidate();
		return "redirect:/";
	}
	@GetMapping("/register")
	public String register(Model model) {
	    model.addAttribute("counsellors", new Counsellors());
	    return "register";
	}

	@PostMapping("/register")
	public String handleRegister(Counsellors c, Model model) {
		boolean status=counsService.saveCounsellors(c);
		if(status) {
			model.addAttribute("smsg","counsellor saved");
		}else {
			model.addAttribute("emsg","Failed to save");
		}
		return "register";
	}
	
	

	@GetMapping("/")
	public String login(Model model) {
		model.addAttribute("counsellors",new Counsellors());
		return "login";
	}
	
	@PostMapping("/login")
	public String handleLogin(Counsellors counsellors,HttpServletRequest req, Model model) {
		
		Counsellors c=counsService.getCounsellors(counsellors.getEmail(), counsellors.getPassword());
		
		if(c==null) {
			model.addAttribute("emsg","invalid credential");
			return "login";
		}
		else {
			
			HttpSession session=req.getSession(true);
			session.setAttribute("cid", c.getCounsellors_id());
			
			
		Dashboard dbinfo=enqService.getDashboard(c.getCounsellors_id());
			model.addAttribute("dashboard",dbinfo);
			return "dashboard";
		}
	}	
	@GetMapping("/dashboard")
	public String buildDashboard(HttpServletRequest req, Model model)
	{
		HttpSession session=req.getSession(true);
		Integer cid=(Integer) session.getAttribute("cid");
		Dashboard dbinfo=enqService.getDashboard(cid);
		model.addAttribute("dashboard",dbinfo);
		return "dashboard";
		
	}
}
