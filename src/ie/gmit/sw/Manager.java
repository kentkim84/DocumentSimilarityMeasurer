package ie.gmit.sw;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Manager implements Runnable {	
	private BlockingQueue<Shingle> blockingQueue;
	private ExecutorService executorService;
	private Set<Integer> set1;
	private Set<Integer> set2;	
	private int count = 2; // number of sets
	private long start_time;
	private long end_time;
	private long duration;

	public Manager(BlockingQueue<Shingle> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			List<String> buffer1 = new LinkedList<String>();
			List<String> buffer2 = new LinkedList<String>();
			List<Integer> hashIntList = new ArrayList<Integer>();
			List<Future<Integer>> minHashList1 = new LinkedList<Future<Integer>>();
			List<Future<Integer>> minHashList2 = new LinkedList<Future<Integer>>();
			set1 = new HashSet<Integer>();
			set2 = new HashSet<Integer>();
			
			// create a fixed size of thread pool with n-number of workers
			executorService = Executors.newFixedThreadPool(100);

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
			}
			// create the set of hash integers as random numbers
			// create k random integers
			Random r = new Random();
			int k = buffer1.size()+buffer2.size();
			//int k = 2000; // test k-size
			for (int i = 0; i < k; i++) {
				hashIntList.add(r.nextInt());				
			}
			start_time = System.currentTimeMillis();		
			for (Integer hash : hashIntList) {				
				minHashList1.add(executorService.submit(new MinHash(buffer1, hash)));
				minHashList2.add(executorService.submit(new MinHash(buffer2, hash)));
			}
			
			// retrieve future integer value to store into each set
			for (Future<Integer> future1 : minHashList1) {
				set1.add(future1.get());
			}
			for (Future<Integer> future2 : minHashList2) {
				set2.add(future2.get());
			}

			executorService.shutdown();
			while(executorService.isTerminated() != true) {
				// await till threadPool is terminated
			}
			if (executorService.isTerminated()) {
				end_time = System.currentTimeMillis();
				duration = (end_time - start_time); // catch the duration of reading process
				// get similarity result from two sets
				final double result = JaccardSimilarity(set1, set2);
				System.out.println("\n<Results>\nMinHash Processing Done: " + duration + " milliseconds");
				System.out.println("Size of HashFunctions: " + k);
				System.out.println("Similarity: " + result*100 + " %");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public double JaccardSimilarity(Set<Integer> set1, Set<Integer> set2) {
		double result = 0;		
		Set<Integer> intersection = new HashSet<Integer>(set1);		
		intersection.retainAll(set2);
		result = intersection.size() / (double)((set1.size() + set2.size()) - intersection.size()); // 
		return result;
	}

}
