

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class SlaveB {
	public static void main(String[]args) throws InterruptedException  {
	//connect to a socket
			args = new String[] {"127.0.0.1", "31625"};
			String hostName = args[0];
			int portNumber = Integer.parseInt(args[1]);
			
					try(
						Socket slaveSocket = new Socket(hostName, portNumber);
						//get the output stream
						PrintWriter out = new PrintWriter(slaveSocket.getOutputStream(), true);
						//input stream
						BufferedReader in = new BufferedReader(new InputStreamReader(slaveSocket.getInputStream()));
							){
						
						
						Thread slaveToMaster = new ThreadSlaveToMasterB(out, in);
						slaveToMaster.start();
						slaveToMaster.join();
						
						
						}catch (IOException e) {
							System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection.");
							System.out.println(e.getMessage());
						} 	}
}
