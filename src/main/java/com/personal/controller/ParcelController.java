package com.personal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.personal.ParcelService;
import com.personal.dto.MyntDTO;

@RestController
@RequestMapping("/parcel")
public class ParcelController {
	
	@Autowired
	private ParcelService parcelService;
	private String DISCOUNT_SITE = "https://mynt-exam.mocklab.io/voucher/MYNT?key=apikey";
	
	private double computeTotalCost(double weight, int height, int width, int length) {
		
		double result = 0.0;
		Integer volume = (height * width * length);
		if (weight >= 10 && weight <= 50) {
			result = parcelService.compute(2, weight);
		} else {
			double wt = volume / 1000.00; // 1000 - 1kg
			if (wt < 1.5) {
				result = parcelService.compute(3, volume);
			} else if (wt >= 1.5 && wt <= 2.5) {
				result = parcelService.compute(4, volume);
			} else {
				result = parcelService.compute(5, volume);
			}
		}
		return result;
	}
	
	@GetMapping("/compute/{weight}/{height}/{width}/{length}")
	public ResponseEntity<String> computeCost(@PathVariable Double weight,  
																   @PathVariable Integer height, 
																   @PathVariable Integer width, 
																   @PathVariable Integer length) {
		String result = "";
		if (weight > 50) {
			result = "N/A";
		} else {
			result = "" + computeTotalCost(weight, height, width, length);
		}
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/compute/{weight}/{height}/{width}/{length}/{voucherCode}")
	public ResponseEntity<String> computeCostWithVoucher(@PathVariable Double weight,  
																   @PathVariable Integer height, 
																   @PathVariable Integer width, 
																   @PathVariable Integer length) {
		
		MyntDTO dto = getDiscount();
		double total = computeTotalCost(weight, height, width, length) - dto.getDiscount();
		
		return new ResponseEntity<>(total + "", HttpStatus.OK);
	}
	
	private MyntDTO getDiscount() {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MyntDTO> response = restTemplate.getForEntity(DISCOUNT_SITE, MyntDTO.class);
		MyntDTO dto = response.getBody();
		return dto;
		
	}
}
