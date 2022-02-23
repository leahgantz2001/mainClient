

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadMasterFromClient extends Thread{

	private BufferedReader read;
	private PrintWriter printA;
	private PrintWriter printB;
	private Num sendToA;
	private Num sendToB;
	private Object dummyObject;
		
	

	
	public ThreadMasterFromClient(BufferedReader read, PrintWriter printA, PrintWriter printB, Num sendToA, Num sendToB, Object dummyObject) {
		this.printA  = printA;
		this.printB = printB;
		this.read = read;
		this.sendToA = sendToA;
		this.sendToB = sendToB;
		this.dummyObject = dummyObject;	
	
	}
	
	public void run() {			
		
	try {
		String sent;
		while(true) {
			sent = read.readLine();
			System.out.println("Recieved: " + sent);
			char type = sent.charAt(0);
			char sendTo = 0;

			
				if(type == 'a') {
					synchronized(dummyObject) {
					if((sendToA.getNumber() < 7) || (sendToB.getNumber() > 7)) {
						sendTo = 'a';
						sendToA.setNumber((sendToA.getNumber() + 1));
												}
					
					else {
							sendTo = 'b';
							sendToB.setNumber((sendToB.getNumber() + 5));
															}
						}
					}
				
				
				else {
					synchronized(dummyObject) {
					if((sendToB.getNumber() < 7) || (sendToA.getNumber() > 7)) {
						sendTo = 'b';
						sendToB.setNumber((sendToB.getNumber() + 1));
						
						}
					
					else {
							sendTo = 'a';
							sendToA.setNumber((sendToA.getNumber() + 5));
															}
						
					}
				}
				

			
		if(sendTo == 'a') {
				printA.println(sent);
				System.out.println("Sent " + sent + " to a");
			}
			
		else {
				printB.println(sent);
				System.out.println("Sent " + sent + " to b");

			}
			
			
		}
		
	}
		 
		
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	
}
}
