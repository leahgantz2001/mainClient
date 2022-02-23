

import java.io.BufferedReader;
import java.io.IOException;

public class ThreadClientFromMaster extends Thread{
	private BufferedReader in;
	
	public ThreadClientFromMaster(BufferedReader in) {
		this.in = in;
	}
	
	public void run() {
		try {
		String response;
		while(true) {
			 response = in.readLine();
			 System.out.println("Recieved job done: " + response.substring(1));
		} 
		
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
