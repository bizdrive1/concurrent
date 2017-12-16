package com.restaurant.dinning;

public abstract class Dinner implements Runnable {

	protected long eatingTime;
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
				eatMeal();
				// let other dinner get a chance to get the fork.
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			System.out.println(name + " Thread ends. Total eating time " + eatingTime);
		}
	}
	
	protected abstract void eatMeal() throws InterruptedException;
	
	protected void perform(String action) {
		System.out.println(name + " " + action);
	}
}
