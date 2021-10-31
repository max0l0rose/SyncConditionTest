package com.company;

class Producer implements Runnable {

	Store store;

	Producer(Store store) {
		this.store = store;
	}

	public void run() {
		try {
			Thread.sleep((long)(Math.random() * 1000));

			for (int i = 1; i < 6; i++) {
				store.put();
				Thread.sleep((long) (Math.random() * 500));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
