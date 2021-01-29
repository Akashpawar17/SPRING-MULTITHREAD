package com.thread;

public class Demo extends Thread {

	public void run() {
		for (int i = 1; i < 5; i++) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			System.out.println(i);
		}
	}
	
	

	public static void main(String[] args) {
		Demo d=new Demo();
		Demo d1=new Demo();
		d.start();
	d1.start();

	}

}
