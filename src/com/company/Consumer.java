package com.company;

class Consumer implements Runnable {

	Store store;

	Consumer(Store store) {
		this.store = store;
	}

	public void run()
	{
		try {
			long delay = (long) (Math.random() * 1000);
			Thread.sleep(delay);

			for (int i = 1; i < 6; i++) {
				store.get();
				Thread.sleep((long) (Math.random() * 500));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
