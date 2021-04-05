package com.personal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ParcelService {
	@Value("${priority_2_cost}")	
	private double priority2Cost;
	@Value("${priority_3_cost}")	
	private double priority3Cost;
	@Value("${priority_4_cost}")	
	private double priority4Cost;
	@Value("${priority_5_cost}")	
	private double priority5Cost;
	
	public double compute(int priority, double costing) {
		double cost = 0.0;
		switch (priority) {
			case 2:
				cost = priority2Cost * costing;
				break;
			case 3:
				cost = priority3Cost * costing;
				break;
			case 4:
				cost = priority4Cost * costing;
				break;
			case 5:
				cost = priority5Cost * costing;
				break;
		}
		return cost;
	}
}
