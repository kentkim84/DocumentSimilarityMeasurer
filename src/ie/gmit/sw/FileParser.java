package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class FileParser implements Runnable {
	//private static final String REGEX = "\\s+";
    private final int shingleSize = 5;
    private int documentID;
	private List<String> shingles;
	private BlockingQueue<Shingle> blockingQueue;
	private String fileName;
	private int poisonPill;
	private int poisonPillPerProducer;

	// constructor with parameters then calls run method
	public FileParser(BlockingQueue<Shingle> blockingQueue, String fileName, int poisonPill, int poisonPillPerProducer) {		
		this.blockingQueue = blockingQueue;
		this.fileName = fileName;
		this.poisonPill = poisonPill;
		this.poisonPillPerProducer = poisonPillPerProducer;
	}
	
	public void parse() throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		//shingles = new LinkedList<>();
		String line = null;
		documentID = 0;
		while ((line = br.readLine()) != null) {
			// skip the line without anything
			if(line.length() > 0) {
				String[] words = line.split("\\W*\\s+");
				//System.out.println("line: "+line);			
				Shingle shingle = new Shingle(documentID, shingleSize, words);
				//System.out.println("T ID "+Thread.currentThread().getName()+" shingle: "+shingle.toString());
				blockingQueue.put(shingle);
				documentID++;			
			}			
		}
		br.close();
	}

	public void run() {		
		try {
			parse();			
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}


}
