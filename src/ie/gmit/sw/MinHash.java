package ie.gmit.sw;

import java.util.List;
import java.util.concurrent.Callable;

public class MinHash implements Callable<Integer>{		
	private int hash;
	private List<String> shingleList;

	public MinHash(List<String> shingleList, int hash) {						
		this.shingleList = shingleList;
		this.hash = hash;
	}

	public Integer call() {
		Integer minHashValue = Integer.MAX_VALUE;
		try {									
			for (String word : shingleList){
				int minHash = word.hashCode() ^ hash; //Bitwise XOR the string hashCode with the hash
				if (minHash < minHashValue) {
					minHashValue = minHash;
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return minHashValue;
	}

	/*public void run() { // Runnable implementation
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

}
