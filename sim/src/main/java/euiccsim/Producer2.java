package euiccsim;

import java.util.concurrent.BlockingQueue;

public class Producer2  {

	private BlockingQueue<Message> queue;

	public static enum HttpRequestType {
		GET, POST, PUT, DELETE;
	}
	
//	public Producer2(BlockingQueue<Message> q) {
//		this.queue = q;
//	}

	
	public void  addqueue(BlockingQueue<Message> q) {
		this.queue = q;
	}

	public void addRequest(String systemId, String msgs){
		// produce messages
		try {
					String eid=msgs.trim();
					Message msg = new Message();
					msg.setEid(eid);
					msg.setUrl("http://httpbin.org/get");
					msg.setMethod(HttpRequestType.GET.name());
					// Check in Queue if present or Not
					if (!queue.contains(msg)) {
						System.out.println("true");
						System.out.println("Eid:" + msg.getEid());
						// Check in Database
						boolean state = MongoConnection2.checkInDb(msg.getEid());
						System.out.println("State : " + state);
						// If Present then add to Queue
						if (state == true) {
							queue.add(msg);
							System.out.println("Produced " + msg.getEid());
							Thread.sleep(1000);
							
						}
					}
					else{
						System.out.println("false");
					}
				//}
				System.out.println("Queue: " + queue.toString());
				//System.out.println("clientMesgQueue: " + clientQueue.toString());
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}