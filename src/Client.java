

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class Client {
	public static void main(String[]args) throws IOException, InterruptedException {  
		args = new String[] {"127.0.0.1", "44236"};
		
		
		//connect to a socket
		try(
			Socket clientSocket = new Socket("127.0.0.1", 44236);
			//get the output stream
				
			
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			//input stream
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				){
			
					
				Thread read = new ThreadClientFromMaster(in);
				Thread write = new ThreadClientToMaster(out);
				read.start();
				write.start();
				read.join();
				write.join();
			
				
			}
			
		}
}
