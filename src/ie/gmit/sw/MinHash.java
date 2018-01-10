package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class MinHash implements Runnable {	
	private int setIndex;
	private int numHashes;
	private int hash;
	private int min;	
	private List<String> shingleList;
	// @param Integer: document id
	// @param Set<Integer>: hashes 
	private Map<Integer, List<Integer>> map;
	private int numTerms;
	private int mod;
	private long start_time;
	private long end_time;
	private long duration;	


	
	public MinHash(int setIndex, List<String> shingleList, int numHashes) {				
		this.setIndex = setIndex;
		this.shingleList = shingleList;
		this.numHashes = numHashes;
	}

	
	
	/*public void run() {
		// loop over the number of permutations times
		// Threadpool gives each job to the n-number of worker threads
		// create 200 MinHash set 1 and 2
		List<Integer> hashes = new ArrayList<Integer>();
		List<Integer> hashList = new ArrayList<Integer>();
		Random r = new Random();
		//start_time = System.currentTimeMillis();
		for (int i = 0; i < numHashes; i++) {
			hashes.add(r.nextInt());				
		}
		//end_time = System.currentTimeMillis();
		//duration = (end_time - start_time); // catch the duration of reading process
		//System.out.println("Hashing Done: " + duration + " milliseconds");
		start_time = System.currentTimeMillis();
		for (Integer hash : hashes){
			int min = Integer.MAX_VALUE;
			//start_time = System.currentTimeMillis();
			for (String word : shingleList){
				int minHash = word.hashCode() ^ hash; //Bitwise XOR the string hashCode with the hash
				if (minHash < min) {
					min = minHash;
				}
			}
			//end_time = System.currentTimeMillis();
			//duration = (end_time - start_time); // catch the duration of reading process
			//System.out.println("Word hashing Done: " + duration + " milliseconds");
			hashList.add(min); //Only store the shingle with the minimum hash for each hash function
			//executorService.execute(new MinHash(0, buffer1, buffer1.size()+buffer2.size()));
			//executorService.execute(new MinHash(1, buffer2, buffer1.size()+buffer2.size()));
		}
		end_time = System.currentTimeMillis();
		duration = (end_time - start_time); // catch the duration of reading process
		System.out.println("Document ID: "+setIndex);
		System.out.println("Hashes size: "+hashes.size()+"\nHashList size: "+hashList.size());
		System.out.println("MinHashing Done: " + duration + " milliseconds");
	}*/
	
	public void run() {
		try {
			for (String word : shingleList){
				int minHash = word.hashCode() ^ hash; //Bitwise XOR the string hashCode with the hash
				if (minHash < min) {
					min = minHash;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
