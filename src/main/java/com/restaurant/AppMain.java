package com.restaurant;

import com.restaurant.service.DinningService;

public class AppMain {
	public static void main(String[] args) throws Exception {
		DinningService service = new DinningService(10);
		service.execute();
	}
}
