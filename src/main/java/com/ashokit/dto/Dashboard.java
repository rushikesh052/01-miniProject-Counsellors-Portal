package com.ashokit.dto;


public class Dashboard {
	
	private Long totalEnquiry;
	
	private Long openEnqs;
	
	private Long enrolledEnqs;
	
	private long lostEnqs;

	public Long getTotalEnquiry() {
		return totalEnquiry;
	}

	public void setTotalEnquiry(Long totalEnquiry) {
		this.totalEnquiry = totalEnquiry;
	}

	public Long getOpenEnqs() {
		return openEnqs;
	}

	public void setOpenEnqs(Long openEnqs) {
		this.openEnqs = openEnqs;
	}

	public Long getEnrolledEnqs() {
		return enrolledEnqs;
	}

	public void setEnrolledEnqs(Long enrolledEnqs) {
		this.enrolledEnqs = enrolledEnqs;
	}

	public long getLostEnqs() {
		return lostEnqs;
	}

	public void setLostEnqs(long losgEnqs) {
		this.lostEnqs = losgEnqs;
	}
	
	
}
