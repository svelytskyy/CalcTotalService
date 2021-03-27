package com.finity.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SolutionTest {
	
	int discount;
	int[] prices = {};
	
	@Test
	public void testProcessBusket(){
		
		//Test case # 1 : sum 4000
		discount = 25;
		prices = new int[]{1000, 2000,400, 1000, 100};
		assertTrue("First test case",Solution.calculateTotalPrice(prices, discount)==4000);
		
		//Test negative case #2 empty busket price
		discount = 25;
		prices = new int[]{};
		assertTrue("First test case",Solution.calculateTotalPrice(prices, discount)==-1);
		
		//Test negative case #3 negative prices
		discount = 25;
		prices = new int[]{-1,100,11,12};
		assertTrue("First test case",Solution.calculateTotalPrice(prices, discount)==-1);
		
		//Test negative case #4 busket size > 100
		discount = 25;
		prices = new int[100];
		for(int i = 0; i < prices.length; i++)prices[i] = i;
		assertTrue("First test case",Solution.calculateTotalPrice(prices, discount)==-1);	
		
		//Test case #5 only 1 item in busket price
		discount = 25;
		prices = new int[]{100};
		assertTrue("First test case",Solution.calculateTotalPrice(prices, discount)==75);	
		
		//Test case #6 round down
		discount = 10;
		prices = new int[]{1000, 2012,400, 1000, 100};
		assertTrue("First test case",Solution.calculateTotalPrice(prices, discount)==4310);		
	}

}
