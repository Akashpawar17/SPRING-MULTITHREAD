package com.thread;

public class JoinDemo  extends Thread{


	public void run() {
		for(int i=1;i<=5;i++){  
			   try{  
			    Thread.sleep(300);  
			   }catch(Exception e){System.out.println(e);}  
			  System.out.println(i);  
			  }  
	}
	public static void main(String[] args) {
		JoinDemo j1=new JoinDemo();
		JoinDemo j2=new JoinDemo();

		JoinDemo j3=new JoinDemo();
		
		System.out.println("thread 1"+j1.getName()+":"+j1.getId());
		System.out.println("thread 2"+j2.getName()+":"+j2.getId());
		j3.setName("tom");
		System.out.println("thread 3"+j3.getName()+":"+j3.getId());
		j1.start();
		try {
			j1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
j2.start();
j3.start();
	}
	

}
