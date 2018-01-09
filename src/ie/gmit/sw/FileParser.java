package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;

public class FileParser implements Runnable {
    private final int shingleSize = 5;
    private int documentID;	
	private BlockingQueue<Shingle> blockingQueue;
	private String fileName;	
	private long start_time;
	private long end_time;
	private long duration;
	
	// constructor with parameters then calls run method
	public FileParser(BlockingQueue<Shingle> blockingQueue, String fileName) {		
		this.blockingQueue = blockingQueue;
		this.fileName = fileName;
	}
	
	public void parse() throws IOException, InterruptedException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		//shingles = new LinkedList<>();
		String line = null;
		if (Thread.currentThread().getName().equals("TF1")) {
			documentID = 0;
		}
		else {
			documentID = 1;
		}
		
		start_time = System.currentTimeMillis();		
		while ((line = br.readLine()) != null) {
			// skip the line without anything
			if(line.length() > 0) {
				String[] words = line.replaceAll("[^a-zA-Z0-9 :]", "").toLowerCase().split("\\s+");
				System.out.println("line: "+line);
				Shingle shingle = new Shingle(documentID, shingleSize);
				shingle.genShingle(words);
				System.out.println("T ID "+Thread.currentThread().getName()+" shingle: "+shingle.toString());
				// add shingle to the blocking queue
				blockingQueue.put(shingle);					
			}			
		}
		br.close();
		
		// add a poison shingle to stop
		Shingle poison = new Shingle(-1);
		blockingQueue.put(poison);
		end_time = System.currentTimeMillis();
		duration = (end_time - start_time); // catch the duration of reading process
		System.out.println("Parsing: " + Thread.currentThread().getName() + " Done: " + duration + " milliseconds");
	}

	public void run() {		
		try {
			parse();
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}


}
