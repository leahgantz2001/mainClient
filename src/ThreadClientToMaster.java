

import java.util.Random;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadClientToMaster extends Thread{
	private PrintWriter out;

	
	
	public ThreadClientToMaster(PrintWriter out) {
		this.out = out;
		
	}

	
	public void run() {
		//send requests with probability of being either job type
		System.out.println("Sending.....");
		String type;
			int id;
			Random rand = new Random();
			int number;
			String message;
			
			for(id = 1; id < 20; id++) {
				
				number = rand.nextInt(40);
				if(number > 25) {
					type = "a";
				}
				else {
					type = "b";
				}
				
				message = type + id;
				System.out.println("Sending job " + message);
				out.println(message);
		}
	
	
	}
}
