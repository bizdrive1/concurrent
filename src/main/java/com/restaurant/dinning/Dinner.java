package com.restaurant.dinning;

public class Dinner implements Runnable {

	protected String name;
	protected Fork leftFork;
	protected Fork rightFork;

	public Dinner(String name, Fork leftFork, Fork rightFork) {
		this.name = name;
		this.leftFork = leftFork;
		this.rightFork = rightFork;
	}

	@Override
	public void run() {
		try {
			while (true) {
				synchronized (leftFork) {
					perform("picked up left fork");
					synchronized (rightFork) {
						perform("picked up right fork");
						int ms = (int) (Math.random() * 100);
						perform("is eating ");
						Thread.sleep(ms);
						perform("ate for " + ms + "ms. Pausing to digest…");
						perform("Put down right fork");
					}
					perform("Put down left fork.");
				}
				// let other dinner get a chance to get the fork.
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected void perform(String action) {
		System.out.println(name + " " + action);
	}
}
