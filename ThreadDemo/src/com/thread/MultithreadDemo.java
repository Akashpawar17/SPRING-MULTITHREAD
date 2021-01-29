package com.thread;

public class MultithreadDemo {

	public static void main(String[] args) {
		hi obj1=new hi();
		obj1.start();
		hello obj2=new hello();
		obj2.start();

	}

}
class hello extends Thread{
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("hello");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class hi extends Thread{
	public void run() {
		for (int i = 0; i < 5; i++) {
			System.out.println("hi");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}