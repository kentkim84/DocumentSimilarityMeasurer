package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class FileParser implements Runnable {
	private final int shingleSize = 5;
	private int documentID;	
	private BlockingQueue<Shingle> blockingQueue;
	private List<String> tempList;
	private Shingle shingle;
	private String fileName;	
	private long startTime;
	private long endTime;
	private long duration;

	public FileParser() {}

	// constructor with parameters then calls run method
	public FileParser(BlockingQueue<Shingle> blockingQueue, String fileName) {		
		this.blockingQueue = blockingQueue;
		this.fileName = fileName;
	}

	public void showList() { // display text file list in the default directory
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				if (name.endsWith(".txt")) {		           
					return true;
				} else {
					return false;
				}
			}
		};		
		File folder = new File(System.getProperty("user.dir"));
		String[] fileList = folder.list(filter);		
		for (String file : fileList) {
			System.out.println(file);
		}
	}

	public void run() {				
		try {			
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			//shingles = new LinkedList<>();
			if (Thread.currentThread().getName().equals("TF1")) {
				documentID = 0;
			}
			else if (Thread.currentThread().getName().equals("TF2")){
				documentID = 1;
			}								
			String line = null;
			tempList = new LinkedList<String>();
			startTime = System.currentTimeMillis();
			System.out.println("Processing...Please wait...\nParsing: " + Thread.currentThread().getName() + " Starts");
			while ((line = br.readLine()) != null) {
				// skip the line without anything
				if(line.length() > 0) {
					String[] words = line.replaceAll("[^a-zA-Z0-9 :]", "").toLowerCase().split("\\s+");					
					shingle = new Shingle(documentID, shingleSize);
					shingle.genShingle(words);									
					// add shingle to the blocking queue
					blockingQueue.put(shingle);										
					tempList.addAll(shingle.getShingleList());					
				}
			}			
			br.close();
			// add a poison shingle to stop
			Shingle poison = new Shingle(-1);		
			blockingQueue.put(poison);
			endTime = System.currentTimeMillis();
			duration = (endTime - startTime); // catch the duration of reading process
			System.out.println("Parsing: " + Thread.currentThread().getName() + " Done: " + duration + " milliseconds");
			System.out.println("shingle list size: "+tempList.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
