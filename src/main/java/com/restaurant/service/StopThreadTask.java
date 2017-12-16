package com.restaurant.service;

import java.util.TimerTask;

public class StopThreadTask extends TimerTask {

	private Thread[] threads;
	
	public StopThreadTask(Thread[] threads) {
		this.threads = threads;
	}
	
	public void run() {
		for(Thread t : threads) {
			t.interrupt();
		}
	}
}
