package com.finity.service;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Sergiy Velytskyy
 *
 *	discount of "discount%" the most expensive item purchased by a given customer during the sales period.
 *  calculation of the total purchase price as an integer
 */

public class Solution {
	
	private static final int MAX_PRICE = 100000;
	private static final int MIN_PRICE = 0;
	
	private static final int MIN_DISCOUNT = 0;
	private static final int MAX_DISCOUNT = 100;
	
	private static final int MIN_BUSKET_SIZE = 0;
	private static final int MAX_BUSKET_SIZE = 100;
	
	private static final Logger LOGGER = Logger.getLogger( Solution.class.getName() );
	
	static {
		LOGGER.setLevel(Level.INFO);
	}
	
	/**
	 * 
	 * @param prices - price busket
	 * @param discount - discount for max priced item
	 * @return - sum of price busket
	 */
	public static int calculateTotalPrice(int[] prices, int discount) {
		
		try {
			validateParameters(prices, discount);
			return processBusket(prices, discount);
			
		}catch (CalcTotalServiceException ce){
			
			LOGGER.severe(ce.getMessage());
			
		}catch(Exception e) {
			LOGGER.severe(e.getMessage());
		}
		
		return -1;
	}
	
	public static void validateParameters(int[] prices, int discount) throws  CalcTotalServiceException{
		if(discount < MIN_DISCOUNT || discount > MAX_DISCOUNT) throw new CalcTotalServiceException("discount parameter must be between " + MIN_DISCOUNT + " and " + MAX_DISCOUNT);
		if(prices == null || prices.length == 0) throw new CalcTotalServiceException("price busket can't be empty");
		if(prices.length<= MIN_BUSKET_SIZE || prices.length >= MAX_BUSKET_SIZE) throw new CalcTotalServiceException("price busket size must be between " + MIN_BUSKET_SIZE + " and " + MAX_BUSKET_SIZE);
		
		for(int price : prices) {
			if(price <= 0 || price >= 100000) throw new CalcTotalServiceException("price must be between " + MIN_PRICE + " and " + MAX_PRICE);
		}
	}
	
	public static int processBusket(int[] prices, int discount) {
		
		LOGGER.info("discount : " + discount  + " prices : " + Arrays.toString(prices));
		Arrays.sort(prices);
		float sum = 0;
		for(int i = 0; i < prices.length; i++) {
			if(i == prices.length-1) {
				//discounting max price
				sum += prices[i]* (1- ((float)(discount)/100));
			}
			else {
				sum += prices[i];
			}
		}
		int result = (int)Math.ceil(sum);
		LOGGER.info("Total sum " + result);
		return result;
	}
	
	public static void main(String args[]) {
		int[]prices = {1000, 2000,400, 1000, 100};
		int discount  = 25;
		calculateTotalPrice(prices,discount);
	}

}
