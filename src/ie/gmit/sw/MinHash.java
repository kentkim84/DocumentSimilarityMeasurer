package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class MinHash implements Callable<Map<Integer, List<Integer>>> {
	private Shingle shingle;
	private List<Integer> hasheList;
	private List<String> shingleList;
	private Map<Integer, List<Integer>> map;
	private int numPermutations;
	private int numTerms;
	private int mod;
	private long start_time;
	private long end_time;
	private long duration;	

	public MinHash(Shingle shingle, Map<Integer, List<Integer>> map, int numPermutations) {		
		this.shingle = shingle;
		this.map = map;
		this.numPermutations = numPermutations;
	}

	public void init() {
		// the total number of unique terms in the collection of documents
		shingleList = shingle.getShingleList();
		numTerms = shingleList.size();
		mod = PrimeFunction.nextPrime(numTerms);

		/*// generates k-random hash functions.  k is equal to the number of permutations.
		Random r = new Random();
		hasheList = new ArrayList<Integer>();

		this.numHash = numHash;

		Random r = new Random(11);
		for (int i = 0; i < numHash; i++){
			int a = (int)r.nextInt();
			int b = (int)r.nextInt();
			int c = (int)r.nextInt();
			int x = hash(a*b*c, a, b, c);
			hash[i] = x;
		}*/
	}

	public Map<Integer, List<Integer>> call() throws Exception {

		//System.out.println("docID: "+shingle.getDocumentID()+" "+shingleList);

		start_time = System.currentTimeMillis();		
		for (int i = 0; i < numPermutations; i++){
			//hasheList.add(r.nextInt());

			/*hashVal = word2int(words[j], AB.get(i).a, AB.get(i).b, mod);
			if(hashVal < minHashVals[i]) minHashVals[i] = hashVal;*/
		}
		//XOR the integer word values with the hashes
		/*for (Integer hash : hashes){
					int min = Integer.MAX_VALUE;
					for (String word : shingle.getShingleList()) {
						int minHash = word.hashCode() ^ hash; //Bitwise XOR the string hashCode with the hash
						if (minHash < min) min = minHash;
					}
					hashes.add(min); //Only store the shingle with the minimum hash for each hash function
				}*/





		//System.out.println("id: " + shingle.getDocumentID());
		//System.out.println("sh: "+shingle.getShingleList());
		int i = 0;
		for (String sh : shingle.getShingleList()) {
			//System.out.println(++i + " : " + sh);
		}	
		end_time = System.currentTimeMillis();
		duration = (end_time - start_time); // catch the duration of reading process
		//System.out.println("MinHashing Done: " + duration + " milliseconds");
		return map;
	}
}
