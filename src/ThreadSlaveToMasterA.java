

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadSlaveToMasterA extends Thread{

	private PrintWriter out;
	private BufferedReader in;

	
	public ThreadSlaveToMasterA( PrintWriter out, BufferedReader in) {
		this.in = in;
		this.out = out;

	}
	
		
	
	public void run() {
		
			char jobType;
			
			try {
				String job ;

			while(true) {
				//get the job
				job = in.readLine();
				jobType = job.charAt(0);
					
				if(jobType == 'a') {
					try {
						Thread.sleep(2000);
						out.println("1" + job);
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					
					}
				}
				else {
						try {
							Thread.sleep(10000);
							out.println("5" + job);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
					
					System.out.println("Job on slave a " + job);
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

}
			}
	
