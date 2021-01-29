package com.thread;

public class DeamonDemo implements Runnable{

	public static void main(String[] args) {
		DeamonDemo d1=new DeamonDemo();
		Thread t=new Thread(d1);
		Thread t1=new Thread(d1);
		
		t.setDaemon(true);
t.start();
t1.start();

	}

	@Override
	public void run() {
		  if(Thread.currentThread().isDaemon()){//checking for daemon thread  
			   System.out.println("daemon thread work");  
			  }  
			  else{  
			  System.out.println("user thread work");  
			 } }

}
