package com.example.crud.components.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class ListGenerator {

	List<Offer> offers = new ArrayList<>();
	Offer offer = new Offer();

	public static void main(String[] args) {
		ListGenerator generator = new ListGenerator();
		generator.getCouponList();

	}

	private List<Offer> getCouponList() {
		getOffer();
		Set<String> newCodes = new HashSet<>();

		int totalCoupons = offer.getCouponCount();
		Set<String> generatedCouponCodes = Collections.synchronizedSet(getGeneratedCodes(offer.getCode()));
		List<Coupon> coupons = new ArrayList<>();
		IntStream.range(0, totalCoupons).parallel().forEach(i -> {
			int couponCode;
			boolean isAdded;
			String generatedCode;
			do {
				couponCode = ThreadLocalRandom.current().nextInt(offer.getSeqNumberStart(),
						offer.getSeqNumberEnd() + 1);
				generatedCode = offer.getCode() + couponCode;
				isAdded = generatedCouponCodes.add(generatedCode);
			} while (!isAdded); // Ensure the coupon code is unique

			synchronized (newCodes) {
				newCodes.add(generatedCode);
			}
			coupons.add(buildCouponFromGeneratedCode(offer));
		});

		System.out.println("Generated coupon set size: " + generatedCouponCodes.size());
		System.out.println("New Coupon Codes set size: " + newCodes.size());
		System.out.println("Coupon list size: " + coupons.size());
		return offers;
	}

	private Coupon buildCouponFromGeneratedCode(Offer offer) {
		Coupon coupon = new Coupon();
		coupon.setOffer(offer);
		coupon.setBrand(offer.getBrand());
		coupon.setCode(offer.getCode());
		coupon.setDescription(offer.getDescription());
		coupon.setDiscountAmount(offer.getDiscountAmount());
		coupon.setDiscountPercent(offer.getDiscountPercent());
		coupon.setType(offer.getType());
		return coupon;
	}

	public void getOffer() {
		offer.setCode("BDCR1010102");
		offer.setType("Voolith");
		offer.setBrand("Milan");
		offer.setDescription("KM8NU4CC2BU084787");
		offer.setDiscountAmount(31.66);
		offer.setDiscountPercent(54);
		offer.setSeqNumberStart(1000);
		offer.setSeqNumberEnd(9000);
		offer.setCouponCount(10000);
	}

	
	private Set<String> getGeneratedCodes(String offerCode) {
		int totalCoupons = offer.getCouponCount();
		Set<String> generatedCodes = new HashSet<>();
		for (int i = 0; i < totalCoupons; i++) {
			int couponCode = (int) (Math.random() * (offer.getSeqNumberEnd() - offer.getSeqNumberStart() + 1)
					+ offer.getSeqNumberStart());
//			System.out.println("Generated code per line: " + offerCode + couponCode);
			generatedCodes.add(offerCode + couponCode);

		}
		System.out.println("Generated set code size: " + generatedCodes.size());
		return generatedCodes;
	}

}
