package com.restaurant.dinning;

import java.lang.Thread.State;

public class DinningService {

	public void execute() throws Exception {
		Fork[] forks = new Fork[5];
		Thread[] threads = new Thread[5];

		for (int i = 0; i < forks.length; i++) {
			forks[i] = new Fork();
		}

		for (int i = 0; i < forks.length; i++) {
			Fork leftFork = forks[i];
			Fork rightFork = forks[(i + 1) % forks.length];

			Dinner dinner = null;
			if (i == forks.length - 1) {
				dinner = new RightDinner("Dinner " + (i + 1), leftFork, rightFork);
			} else {
				dinner = new Dinner("Dinner " + (i + 1), leftFork, rightFork);
			}
			Thread t = new Thread(dinner);
			t.start();
			threads[i] = t;
		}
		while (true) {
			if (detectDeadlock(threads)) {
				System.out.println("Deadlock Occurs.");
			}
			Thread.sleep(5000);
		}
	}
	
	private boolean detectDeadlock(Thread[] threads) {
		boolean blocked = true;
		for (Thread t : threads) {
			if (t.getState() != State.BLOCKED) {
				blocked = false;
				break;
			}
		}
		return blocked;	
	}
}
