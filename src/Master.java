

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Master {
	public static void main(String[]args) throws InterruptedException {
	//create the socket in a try catch
	try(
			ServerSocket master = new ServerSocket(44236);
			ServerSocket other = new ServerSocket(31625);
			Socket client1 = master.accept();
			Socket client2 = master.accept();
			//output stream for client
			PrintWriter clientResponse1 = new PrintWriter(client1.getOutputStream(), true);
			PrintWriter clientResponse2 = new PrintWriter(client2.getOutputStream(), true);
			//input stream for client
			BufferedReader clientRequest1 = new BufferedReader(new InputStreamReader(client1.getInputStream()));
			BufferedReader clientRequest2 = new BufferedReader(new InputStreamReader(client2.getInputStream()));
			
			
			
			
			
			//input and output streams for slaves 1 and 2
			Socket slave1 = other.accept();
			Socket slave2 = other.accept();
			PrintWriter slaveResponse1 = new PrintWriter(slave1.getOutputStream(), true);
			PrintWriter slaveResponse2 = new PrintWriter(slave2.getOutputStream(), true);
			BufferedReader slaveRequest1 = new BufferedReader(new InputStreamReader(slave1.getInputStream()));
			BufferedReader slaveRequest2 = new BufferedReader(new InputStreamReader(slave2.getInputStream()));
			
			){
		
			Num sendToA = new Num(0);
			Num sendToB = new Num(0);
			Object dummyObject = new Object();

			//create and start and join threads
			
			Thread sendToClient = new ThreadMasterToClient(clientResponse1, slaveRequest1, sendToA, sendToB, dummyObject );
			Thread sendToClientB = new ThreadMasterToClient(clientResponse2, slaveRequest1, sendToA, sendToB, dummyObject );
			Thread sendToClientFromB = new ThreadMasterToClient(clientResponse1, slaveRequest2, sendToA, sendToB, dummyObject);
			Thread sendToClientBFromB = new ThreadMasterToClient(clientResponse2, slaveRequest2, sendToA, sendToB, dummyObject);
			Thread recieveMessage = new ThreadMasterFromClient( clientRequest1,slaveResponse1, slaveResponse2, sendToA, sendToB, dummyObject );
			Thread recieveMessageB = new ThreadMasterFromClient( clientRequest2,slaveResponse1, slaveResponse2, sendToA, sendToB, dummyObject );
			
			sendToClient.start();
			sendToClientB.start();
			sendToClientFromB.start();
			sendToClientBFromB.start();
			recieveMessage.start();
			recieveMessageB.start();
			
			sendToClient.join();
			sendToClientB.join();
			sendToClientFromB.join();
			sendToClientBFromB.join();
			recieveMessage.join();
			recieveMessageB.join();
				}
								
			
	catch (IOException e) {
		System.out.println("Exception caught when trying to listen on port  or listening for a connection.");
		System.out.println(e.getMessage());
	}
	}
	
}
