package com.company;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Store {
	private int product = 0;
	ReentrantLock locker;
	Condition condition;


	Store() {
		locker = new ReentrantLock(); // создаем блокировку
		condition = locker.newCondition(); // получаем условие, связанное с блокировкой
	}


	public void get() {

		locker.lock();
		try {
			// пока нет доступных товаров на складе, ожидаем
			System.out.println("< Покупатель покупает товар:");
			while (product < 1) {
				System.out.println("Store::get await");
				condition.await();
			}

			product--;
			System.out.println("< Покупатель купил товар. Товаров на складе: " + product);

			condition.signalAll();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("< Покупатель покинул склад.");
			locker.unlock();
		}
	}


	public void put() {

		locker.lock();
		System.out.println(">>> Производитель добавляет товар:");
		try {
			// пока на складе 3 товара, ждем освобождения места
			while (product >= 3) {
				System.out.println("Store::put await");
				condition.await();
			}

			product++;
			System.out.println(">>> Производитель добавил товар. Товаров на складе: " + product);
			// сигнализируем
			condition.signalAll();
			Thread.sleep(500);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println(">>> Производитель покинул склад.");
			locker.unlock();
		}
	}

}
