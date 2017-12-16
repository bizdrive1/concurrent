package com.restaurant.dinning;

public class RightDinner extends LeftDinner {

	public RightDinner(String name, Fork leftFork, Fork rightFork) {
		super(name, leftFork, rightFork);
	}

	@Override
	public void eatMeal() throws InterruptedException {

		synchronized (rightFork) {
			perform("picked up right fork");
			synchronized (leftFork) {
				perform("picked up left fork");
				int ms = (int) (Math.random() * 100);
				perform("is eating ");
				perform("ate for " + ms + "ms. Pausing to digest…");
				eatingTime += ms;
				Thread.sleep(ms);
				perform("Put down left fork");
			}
			perform("Put down right fork.");
		}
	}
}
