package ie.gmit.sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
	private Map<Integer, List<String>> map;
	private int numPermutations = 200;
	private int count = 2; // number of sets
	private long start_time;
	private long end_time;
	private long duration;

	public Manager(BlockingQueue<Shingle> blockingQueue) {
		this.blockingQueue = blockingQueue;
		this.set1 = set1;
		this.set2 = set2;
	}

	@Override
	public void run() {
		try {			 
			//Create the set of hash integers as random numbers
			List<Integer> hashList = new ArrayList<Integer>();
			List<String> buffer1 = new LinkedList<String>();
			List<String> buffer2 = new LinkedList<String>();
			// create a hash map to store document id and hash data
			map = new HashMap<Integer, List<String>>();
			// create a fixed size of thread pool with n-number of workers
			executorService = Executors.newFixedThreadPool(100);
			Future<List<Integer>> future1 = null;
			Future<List<Integer>> future2= null;
			//start_time = System.currentTimeMillis();
			//System.out.println("M: "+Thread.currentThread().getName()+" Q size start: "+blockingQueue.size());
			// pre-sort process
			// -1 is the poison number to stop the process			
			while(count > 0){
				Shingle shingle = blockingQueue.take();
				// decrease the count if poison shingle found
				if (shingle.getDocumentID() == -1) { 
					count--;
				}
				// find shingles from the first and second document
				// then add into buffer 1 and 2
				else if (shingle.getDocumentID() == 0) {
					buffer1.addAll(shingle.getShingleList());
				}
				else if (shingle.getDocumentID() == 1) {
					buffer2.addAll(shingle.getShingleList());
				}
				//future = executorService.invokeAny(new MinHash(shingle, map, numPermutations));
			}
			//end_time = System.currentTimeMillis();
			//duration = (end_time - start_time); // catch the duration of reading process
			//System.out.println("Pre-sorting Done: " + duration + " milliseconds");
			//System.out.println("buffer 1 size: " + buffer1.size());
			//System.out.println("buffer 2 size: " + buffer2.size());
			start_time = System.currentTimeMillis();			
			for (int i = 0; i < buffer1.size()+buffer2.size(); i++) {
				/*int min = Integer.MAX_VALUE;
				//start_time = System.currentTimeMillis();
				for (String word : buffer1){
					int minHash = word.hashCode(); //Bitwise XOR the string hashCode with the hash
					if (minHash < min) {
						min = minHash;
					}
				}
				for (String word : buffer2){
					int minHash = word.hashCode(); //Bitwise XOR the string hashCode with the hash
					if (minHash < min) {
						min = minHash;
					}
				}*/
				
				executorService.execute(new MinHash(0, buffer1, 2000));
				executorService.execute(new MinHash(1, buffer2, 2000));
			}
			executorService.shutdown();

			
			/*future1 = executorService.submit(new MinHash(0, buffer1));
			future2 = executorService.submit(new MinHash(1, buffer2));*/
			/*for (Integer hash : hashList) {
				for (String sh : buff) {
					//  submit the tasks for execution and also control their execution using the returned Future instance.
					executorService.execute(new MinHash(sh, min, hash));
				}
			}*/

			//System.out.println("map size: "+map.size());
			//end_time = System.currentTimeMillis();
			//duration = (end_time - start_time); // catch the duration of reading process
			//System.out.println("MinHashing Done: " + duration + " milliseconds");

			//similarity(Set<T> set1, Set<T> set2);
			/*end_time = System.currentTimeMillis();
			duration = (end_time - start_time); // catch the duration of reading process
			//System.out.println("Document ID: "+setIndex);
			//System.out.println("Hashes size: "+hashes.size()+"\nHashList size: "+hashList.size());
			System.out.println("MinHashing Done: " + duration + " milliseconds");*/
			
			while(executorService.isTerminated() != true) {
				// await till threadPool is terminated
			}			
			if (executorService.isTerminated()) {
				end_time = System.currentTimeMillis();
				duration = (end_time - start_time); // catch the duration of reading process
				//System.out.println("Document ID: "+setIndex);
				//System.out.println("Hashes size: "+hashes.size()+"\nHashList size: "+hashList.size());
				System.out.println("MinHashing Done: " + duration + " milliseconds");
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
