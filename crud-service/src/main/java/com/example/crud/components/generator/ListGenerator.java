package com.example.crud.components.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListGenerator {

	List<Offer> offers = new ArrayList<>();
	Offer offer = new Offer();

	public static void main(String[] args) {
		ListGenerator generator = new ListGenerator();
		generator.getCouponList();

	}

	private List<Offer> getCouponList() {
		File file = new File("./src/main/resources/MOCK_DATA.txt");
		getOffer();
		Set<String> generatedCodes = new HashSet<>();
		Set<String> newGeneratedCodes = new HashSet<>();
		Set<String> newCodes = new HashSet<>();
		List<MatchResult> offerMatches = getOfferMatcher(file);
		//utilized AtomicInteger to allow for the mutable integer operations within the stream.
		AtomicInteger randomNum = new AtomicInteger();
		generatedCodes = offerMatches.stream()
		    .map(matchResult -> matchResult.group() + randomNum.addAndGet((int) (Math.random() * offerMatches.size())))
		    .collect(Collectors.toSet());
		
		generatedCodes.parallelStream().filter(code -> code.contains(offer.getCode())).forEach(filteredCode ->{
			newGeneratedCodes.add(filteredCode+offer.getCode()); 
			newCodes.add(filteredCode+offer.getCode());
			buildCouponFromGeneratedCode(offer);
		});
		System.out.println("Generated set size: "+newGeneratedCodes.size());
//		for (String string : newGeneratedCodes) {
//			System.out.println("New generated codes: "+string);
//		}
		System.out.println("New Codes set size: "+newCodes.size());
//		for (String string : newCodes) {
//			System.out.println("New codes: "+string);
//		}
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

	
//	private void addCouponsFromFileToList(File textFile) {
//		try {
//			Scanner scanner = new Scanner(textFile);
//			scanner.useDelimiter(Pattern.compile("[|\n]"));
//			while (scanner.hasNext()) {
//				Offer offer = new Offer();
//				offer.setCode(scanner.next());
//				offer.setType(scanner.next());
//				offer.setBrand(scanner.next());
//				offer.setDescription(scanner.next());
//				offer.setDiscountAmount(Double.parseDouble(scanner.next()));
//				offer.setDiscountPercent(Integer.parseInt(scanner.next()));
//				offers.add(offer);
//			}
//			scanner.close();
//		} catch (FileNotFoundException ex) {
//			Logger.getLogger(Offer.class.getName()).log(Level.SEVERE, null, ex);
//		}
//	}

	public void getOffer() {
		offer.setCode("BDCR1010102");
		offer.setType("Voolith");
		offer.setBrand("Milan");
		offer.setDescription("KM8NU4CC2BU084787");
		offer.setDiscountAmount(31.66);
		offer.setDiscountPercent(54);
	}

	// To simulate data retrieval from text file and add to set
//	private Set<String> getGeneratedCodes(File textFile) {
//		Set<String> generatedCodes = new HashSet<>();
//		Pattern pattern = Pattern.compile(offer.getCode());
//		try {
//			Scanner scanner = new Scanner(textFile);
//			Stream<MatchResult> matches = scanner.findAll(pattern);
////			int randomNum = 0;
//			List<MatchResult> matchList = matches.toList();
//			int matchListSize = matchList.size();
//			System.out.println("Match code size: "+matchListSize);
//			
////			for (MatchResult matchResult : matchList) {
////				randomNum += (int)(Math.random()*matchList.size());
////				generatedCodes.add(matchResult.group()+randomNum);
////			}
//			//utilized AtomicInteger to allow for the mutable integer operations within the stream.
//////			AtomicInteger randomNum = new AtomicInteger();
//////			generatedCodes = matchList.stream()
//////			    .map(matchResult -> matchResult.group() + randomNum.addAndGet((int) (Math.random() * matchList.size())))
//////			    .collect(Collectors.toSet());
////            System.out.println("Generated codes size: "+generatedCodes.size());
//			scanner.close();
//		} catch (FileNotFoundException ex) {
//			Logger.getLogger(Offer.class.getName()).log(Level.SEVERE, null, ex);
//		}
//		return generatedCodes;
//	}
	
	private List<MatchResult> getOfferMatcher(File textFile){
		Pattern pattern = Pattern.compile(offer.getCode());
		List<MatchResult> matchList = new ArrayList<MatchResult>();
		try {
			Scanner scanner = new Scanner(textFile);
			Stream<MatchResult> matches = scanner.findAll(pattern);
			matchList = matches.toList();
			System.out.println("Match code size: "+matchList.size());
			scanner.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Offer.class.getName()).log(Level.SEVERE, null, ex);
		}
		return matchList;
		
		
	}

}
