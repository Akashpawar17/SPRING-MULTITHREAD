package com.thread;

public class RunnableDemo implements Runnable {
	@Override
	public void run() {
		System.out.println("thread"+Thread.currentThread().getPriority());
		System.out.println("thread"+Thread.currentThread().getName());
		System.out.println("thread"+Thread.currentThread().getId());

	}
	public static void main(String[] args) {
		RunnableDemo d=new RunnableDemo();
		Thread t=new Thread(d);
		t.start();
		

	}

	

}
