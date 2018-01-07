package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Runner {
	public static void main(String[] args) {
		String fileName1 = "test.txt";
		String fileName2 = "test.txt";
		int BOUND = 10;
		int N_PRODUCERS = 4;
		int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
		int poisonPill = Integer.MAX_VALUE;
		int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS;

		BlockingQueue<Shingle> blockingQueue = new LinkedBlockingQueue<>(BOUND);

		new Thread(new FileParser(blockingQueue, fileName1, poisonPill, poisonPillPerProducer), "T1").start();
		//new Thread(new FileParser(blockingQueue, fileName2, poisonPill, poisonPillPerProducer), "T2").start();
		try {
			Shingle sh = blockingQueue.take();
			System.out.println("id: " + sh.getDocumentID());
			System.out.println("sh: "+sh.getShingleList());
			int i = 0;
			for (String shingle : sh.getShingleList()) {
				System.out.println(++i + " : " + shingle);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*for (int j = 0; j < N_CONSUMERS; j++) {
			new Thread(new NumbersConsumer(queue, poisonPill)).start();
		}*/
	}
}
