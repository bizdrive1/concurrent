package com.restaurant.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DinningServiceTest {

	private DinningService dinningService;
	
	@Before
	public void setUp() throws Exception {
		dinningService = new DinningService(10);
	}

	@After
	public void tearDown() throws Exception {

	}
	
	@Test
	public void testDinningService() throws Exception {
		dinningService.execute();
		Thread.sleep(20000);
	}
}
