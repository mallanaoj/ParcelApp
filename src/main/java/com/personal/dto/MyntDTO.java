package com.personal.dto;

public class MyntDTO {
	
	private String code;
	private double discount;
	private String expiry;

	public MyntDTO() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	@Override
	public String toString() {
		return "MyntDTO [code=" + code + ", discount=" + discount + ", expiry=" + expiry + "]";
	}
	
	
}
