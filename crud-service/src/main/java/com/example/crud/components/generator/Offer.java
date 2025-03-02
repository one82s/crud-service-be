package com.example.crud.components.generator;

public class Offer {
	String code;
	String type;
	String brand;
	String description;
	double discountAmount;
	int discountPercent;
	int seqNumberStart;
	int seqNumberEnd;
	int couponCount;
	
	public int getCouponCount() {
		return couponCount;
	}
	public void setCouponCount(int couponCount) {
		this.couponCount = couponCount;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}
	public int getDiscountPercent() {
		return discountPercent;
	}
	public void setDiscountPercent(int discountPercent) {
		this.discountPercent = discountPercent;
	}
	public int getSeqNumberStart() {
		return seqNumberStart;
	}
	public void setSeqNumberStart(int offerSeqNumberStart) {
		this.seqNumberStart = offerSeqNumberStart;
	}
	public int getSeqNumberEnd() {
		return seqNumberEnd;
	}
	public void setSeqNumberEnd(int offerSeqnumberEnd) {
		this.seqNumberEnd = offerSeqnumberEnd;
	}

}
