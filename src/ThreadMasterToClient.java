

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadMasterToClient extends Thread{

   private PrintWriter print;
   private BufferedReader read;
	private Num sendToA;
	private Num sendToB;
	private Object dummyObject;
		
   public ThreadMasterToClient(PrintWriter print, BufferedReader read, Num sendToA, Num sendToB, Object dummyObject) {
	   this.print = print;
	   this.read = read;
	   this.sendToA = sendToA;
	   this.sendToB = sendToB;
	   this.dummyObject = dummyObject;
   }
   
   //send the response from the slave to the client
   public void run() {
	   try {
		   String response;
		   
		while(true) {
			response = read.readLine();
			print.println(response);
			
			
			if(response.charAt(1) == 'a') {
				if(response.charAt(0) == '1') {
					synchronized(dummyObject) {
					sendToA.setNumber((sendToA.getNumber() - 1));
										}
				}
				else {
					synchronized(dummyObject) {
					sendToA.setNumber((sendToA.getNumber() - 5));
									}
				}
				
			}
			else {
				if(response.charAt(0) == '1') {
					synchronized(dummyObject) {
					sendToB.setNumber((sendToB.getNumber() - 1));
					
					}
				}
				else {
					synchronized(dummyObject) {
					sendToB.setNumber((sendToB.getNumber() - 5));
					
					}
				}
			}
			System.out.println("Got message " + response.substring(1) + " from slave sending to client");
		} 
		  
   
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
}
