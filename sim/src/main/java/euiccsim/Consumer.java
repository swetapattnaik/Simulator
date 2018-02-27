package euiccsim;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public class Consumer implements Runnable {

	private BlockingQueue<Message> queue;
	private static ExecutorService service = Executors.newFixedThreadPool(2);
	static RequestConfig requestConfig;
	static CloseableHttpAsyncClient httpclient;

	public Consumer(BlockingQueue<Message> q) {
		this.queue = q;
		requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();
		httpclient = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig).build();
		httpclient.start();
	}

	@Override
	public void run() {
		try {
			while (true) {
				sendNextRequest(queue.take());
				Thread.sleep(10);
				System.out.println("Consumed ");
			}
		} catch (Exception e) {
			// log error
		}
	}

	private void sendNextRequest(final Message reqObject) {
		System.out.println("Sending Http Request...");
		service.execute(new Runnable() {
			@Override
			public void run() {
				// final CountDownLatch latch = new CountDownLatch(1);
				final HttpGet request = new HttpGet(reqObject.getUrl());
				// need to check the request.getMethod and form the appropriate
				// object HttpGet,
				// HttpPost etc
				System.out.println("Request Statusline " + request.getRequestLine());

				httpclient.execute(request, new FutureCallback<HttpResponse>() {

					@Override
					public void completed(final HttpResponse response) {
						// latch.countDown();
						try {
							System.out.println("URI:" + request.getURI());
							if (request.getURI().equals(new URI("http://httpbin.org/get123"))) {
								System.out.println("name : " + Thread.currentThread().getName());
								System.out.println("true");
								Thread.sleep(5000);
							}
							// if(Thread.currentThread().getName().equals("I/O
							// dispatcher 1"))
							Thread.sleep(2000);
							// if(Thread.currentThread().getName().equals("I/O
							// dispatcher 2"))
							// Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						System.out.println("name : " + Thread.currentThread().getName());
						System.out.println(request.getRequestLine() + "->" + response.getStatusLine());
					}

					@Override
					public void failed(final Exception ex) {
						// latch.countDown();
						System.out.println("Failed :" + request.getRequestLine() + "->" + ex);
					}

					@Override
					public void cancelled() {
						// latch.countDown();
						System.out.println("cancelled :" + request.getRequestLine() + " cancelled");
					}

				});
				// try {
				// latch.await();
				// } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
				// }

			}
		});

	}

	public void finalize() {
		try {
			System.out.println("Shutting down");
			httpclient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * @Override public void run() { try{ // Message msg; String msg;
	 * //consuming messages until exit message is received while((msg =
	 * queue.take()) !="exit"){ Thread.sleep(10);
	 * System.out.println("Consumed "+msg); }
	 * System.out.println(queue.toString()); }catch(InterruptedException e) {
	 * e.printStackTrace(); } }
	 */

}