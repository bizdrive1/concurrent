package com.restaurant.dinning;

public class LeftDinner extends Dinner {

	
	public LeftDinner(String name, Fork leftFork, Fork rightFork) {
		super(name, leftFork, rightFork);
	}

	@Override
	public void eatMeal() throws InterruptedException {

		synchronized (leftFork) {
			perform("picked up left fork");
			synchronized (rightFork) {
				perform("picked up right fork");
				int ms = (int) (Math.random() * 100);
				perform("is eating ");
				eatingTime += ms;
				Thread.sleep(ms);
				perform("ate for " + ms + "ms. Pausing to digest…");
				perform("Put down right fork");
			}
			perform("Put down left fork.");
		}
	}

	public long getEatingTime() {
		return eatingTime;
	}
}
