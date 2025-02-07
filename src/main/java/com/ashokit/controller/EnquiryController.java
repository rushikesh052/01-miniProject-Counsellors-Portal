package com.ashokit.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ashokit.entity.Enquiry;
import com.ashokit.service.IEnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController {
	
	@Autowired
	private IEnquiryService enqService;
	
	@GetMapping("/enquiry")
	public String addEnquiry(Enquiry enq, Model model) {
		model.addAttribute("enq", new Enquiry());
		return "addEnq";
	}
	
	@PostMapping("/enquiry")
	public String saveEnquiry(Enquiry enq, HttpServletRequest req, Model model) {
		HttpSession session=req.getSession(false);
		Integer cid=(Integer) session.getAttribute("cid");
		boolean status= enqService.addEnquiry(enq, cid);
		if(status) {
			model.addAttribute("smsg","Enquiry Saved");
		}else {
				model.addAttribute("emsg","Enquiry not saved");
		}
		
		model.addAttribute("enq", new Enquiry());
		
		return "addEnq";
	}
	@GetMapping("/enquiries")
	public String getEnquies(HttpServletRequest req, Model model) {
		HttpSession session=req.getSession(false);
		Integer cid=(Integer) session.getAttribute("cid");
		
		List<Enquiry> list=enqService.getEnquiries(new Enquiry(), cid);
		model.addAttribute("enqs",list);
		
		model.addAttribute("enq", new Enquiry());
		
		return "viewEnquries";
	} 
	
	@PostMapping("/filter-enqs")
	public String filterEnqus(@ModelAttribute("enq") Enquiry enq, HttpServletRequest req,Model model)  {
		HttpSession session=req.getSession(false);
		Integer cid=(Integer) session.getAttribute("cid");
		
		List<Enquiry> list=enqService.getEnquiries(enq,cid);
		model.addAttribute("enqs", list);
		
		return "viewEnquries";
	}
	
	@GetMapping("/edit")
	public String editEnquiryPage(@RequestParam("enqId") Integer enqId,@ModelAttribute("enq") Enquiry enq) {
	Enquiry enquiry=enqService.getEnquiry(enqId);
		BeanUtils.copyProperties(enquiry, enq);
		return "EditEnquiry";
	}
	@PostMapping("edit")
	public String editEnquiry(@ModelAttribute("enq") Enquiry enq,Model model) {
		String msg=enqService.editEnquiry(enq);
		model.addAttribute("smsg",enq);
		return "redirect:viewEnquries";
	}
	
	
	
	
}
