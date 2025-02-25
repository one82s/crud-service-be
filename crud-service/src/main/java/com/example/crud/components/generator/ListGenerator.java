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
		Set<String> generatedCodes = getGeneratedCodes(file);
		Set<String> newGeneratedCodes = new HashSet<>();
		Set<String> newCodes = new HashSet<>();
		generatedCodes.parallelStream().filter(code -> code.contains(offer.getCode())).forEach(filteredCode ->{
			newGeneratedCodes.add(filteredCode+offer.getCode()); 
			newCodes.add(filteredCode+offer.getCode());
		});
		System.out.println("Generated set size: "+newGeneratedCodes.size());
		for (String string : newGeneratedCodes) {
			System.out.println("New generated codes: "+string);
		}
		System.out.println("New Codes set size: "+newCodes.size());
		for (String string : newCodes) {
			System.out.println("New codes: "+string);
		}
		return offers;
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
	private Set<String> getGeneratedCodes(File textFile) {
		Set<String> generatedCodes = new HashSet<>();
		Pattern pattern = Pattern.compile(offer.getCode());
		try {
			Scanner scanner = new Scanner(textFile);
			Stream<MatchResult> matches = scanner.findAll(pattern);
			int randomNum = 0;
			List<MatchResult> matchList = matches.toList();
			int matchListSize = matchList.size();
			System.out.println("Match code size: "+matchListSize);
			for (MatchResult matchResult : matchList) {
				randomNum += (int)(Math.random()*matchList.size());
				generatedCodes.add(matchResult.group()+randomNum);
			}
            System.out.println("Generated codes size: "+generatedCodes.size());
			scanner.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(Offer.class.getName()).log(Level.SEVERE, null, ex);
		}
		return generatedCodes;
	}

}
