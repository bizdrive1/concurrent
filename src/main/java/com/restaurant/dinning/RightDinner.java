package com.restaurant.dinning;

public class RightDinner extends Dinner {
	
	public RightDinner(String name, Fork leftFork, Fork rightFork) {
		super(name, leftFork, rightFork);
	}
	
	/* (non-Javadoc)
	 * @see com.restaurant.dinning.Dinner#run()
	 */
	@Override
	public void run() {
		try {
			while (true) {
				synchronized (rightFork) {
					perform("picked up right fork");
					synchronized (leftFork) {
						perform("picked up left fork");
						int ms = (int) (Math.random() * 100);
						perform("is eating ");
						perform("ate for " + ms + "ms. Pausing to digest…");
						Thread.sleep((int) (Math.random() * 100));
						perform("Put down left fork");
					}
					perform("Put down right fork.");
				}
				// let other dinner get a chance to get the fork.
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
