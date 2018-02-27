package euiccsim;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import org.smpp.smscsim.Simulator;

public class Service {

    public static void main(String[] args) throws IOException {
    	
    	
    	
    	//Starting the SMS Server
    	 Simulator sim=new Simulator();
		sim.start();
    	
    	System.out.println("Started the SmsTriggerServer ");
    	
    	
    	try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
        //Creating BlockingQueue of size 10
        BlockingQueue<Message> queue = new ArrayBlockingQueue<>(10);
        
        
        Producer2 producer2 = new Producer2();
        producer2.addqueue(queue);
        
        
       // Producer producer = new Producer(queue);
       // Consumer consumer = new Consumer(queue);
       // System.out.println("Going to start Producer");
        //starting producer to produce messages in queue
      //  new Thread(producer).start();
        //System.out.println("Going to start Consumer");
        //starting consumer to consume messages from queue
      //  new Thread(consumer).start();
       // System.out.println("Producer and Consumer has been started");
     
        
    }

}