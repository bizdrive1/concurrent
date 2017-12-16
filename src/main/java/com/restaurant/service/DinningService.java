package com.restaurant.service;

import java.lang.Thread.State;
import java.util.Timer;

import com.restaurant.dinning.Dinner;
import com.restaurant.dinning.Fork;
import com.restaurant.dinning.LeftDinner;
import com.restaurant.dinning.RightDinner;

public class DinningService {

	private long timeInSeconds;
	
	public DinningService(long timeInSeconds) {
		this.timeInSeconds = timeInSeconds;
	}
	public void execute() throws Exception {
		Fork[] forks = new Fork[5];
		Thread[] threads = new Thread[5];
		Thread[] stopThreads = new Thread[6];
		
		
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
				dinner = new LeftDinner("Dinner " + (i + 1), leftFork, rightFork);
			}
			Thread t = new Thread(dinner);
			t.start();
			threads[i] = t;
			stopThreads[i] = t;
		}
		
		Thread checkDeadLockThread = new Thread(() -> {
			try {
				while (true) {
					if (detectDeadlock(threads)) {
						System.out.println("Deadlock Occurs.");
					}
					Thread.sleep(5000);
				}
			} catch (InterruptedException e) {
				System.out.println("Check deadLock thread ends");
			}
		});
		stopThreads[5] = checkDeadLockThread;
		checkDeadLockThread.start();
		StopThreadTask stopThreadTask = new StopThreadTask(stopThreads);

		Timer timer = new Timer(true);
        timer.schedule(stopThreadTask, timeInSeconds*1000);
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
