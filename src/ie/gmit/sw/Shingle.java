package ie.gmit.sw;

import java.util.LinkedList;
import java.util.List;

public class Shingle {
	private static final int DEFAULT_SHINGLE_SIZE = 5;
	private int documentID;
	private int shingleSize;
	private List<String> shingleList;
	
	public Shingle(int documentID, String[] words) {
		this.documentID = documentID;
		this.shingleSize = DEFAULT_SHINGLE_SIZE;
		getShingle(words);
	}

	// Shingles: the basic unit (element) is a fixed-size group of words
	public Shingle(int documentID, int shingleSize, String[] words) {	
		this.documentID = documentID;
		this.shingleSize = shingleSize;
		getShingle(words);
	}
			
	public List<String> getShingle(String[] words) {
		shingleList = new LinkedList<>();
		int shinglesNumber = words.length - shingleSize;          
        //Create all shingles 
        for (int i = 0; i <= shinglesNumber; i++) { 
            String shingle = ""; 
            //Create one shingle 
            for (int j = 0; j < shingleSize; j++) { 
                shingle = shingle + words[i+j] + " "; 
            } 
            shingleList.add(shingle); 
        }			
		return shingleList;
	}

	@Override
	public String toString() {
		return "Shingle [documentID=" + documentID + ", shingleSize=" + shingleSize + ", shingleList=" + shingleList
				+ "]";
	}
}
