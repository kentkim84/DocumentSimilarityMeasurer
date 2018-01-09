package ie.gmit.sw;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Manager implements Runnable {
	private BlockingQueue<Shingle> blockingQueue;
	private ExecutorService executorService;
	private Set<Integer> set1;
	private Set<Integer> set2;
	private Map<Integer, List<Integer>> map;
	private int numPermutations = 200;
	private long start_time;
	private long end_time;
	private long duration;
		
	public Manager(BlockingQueue<Shingle> blockingQueue, Set<Integer> set1, Set<Integer> set2) {
		this.blockingQueue = blockingQueue;
		this.set1 = set1;
		this.set2 = set2;
	}

	public void run() {		
		Shingle shingle;
		// create a hash map to store document id and hash data
		map = new HashMap<Integer, List<Integer>>();
		// create a fixed size of thread pool with n-number of workers
		executorService = Executors.newFixedThreadPool(5);
		Future<Map<Integer, List<Integer>>> future = null;
		try {
			start_time = System.currentTimeMillis();
			// -1 is the poison number to stop the process
			while((shingle = blockingQueue.take()).getDocumentID() != -1 && blockingQueue.isEmpty() != true){
				//  submit the tasks for execution and also control their execution using the returned Future instance.
				future = executorService.submit(new MinHash(shingle, map, numPermutations));
				//future = executorService.invokeAny(new MinHash(shingle, map, numPermutations));
			}
			
			map = future.get();
			//System.out.println("map size: "+map.size());
			end_time = System.currentTimeMillis();
			duration = (end_time - start_time); // catch the duration of reading process
			//System.out.println("MinHashing Done: " + duration + " milliseconds");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}

}
