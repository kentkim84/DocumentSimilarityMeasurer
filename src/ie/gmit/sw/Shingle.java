package ie.gmit.sw;

import java.util.LinkedList;
import java.util.List;

public class Shingle {
	private static final int DEFAULT_SHINGLE_SIZE = 5;
	private int documentID;
	private int shingleSize;
	private List<String> shingleList;

	// constructor without a given shingle size
	public Shingle(int documentID) {
		this.documentID = documentID;
		this.shingleSize = DEFAULT_SHINGLE_SIZE;		
	}

	// Shingles: the basic unit (element) is a fixed-size group of words
	public Shingle(int documentID, int shingleSize) {	
		this.documentID = documentID;
		this.shingleSize = shingleSize;		
	}

	public List<String> genShingle(String[] words) {
		shingleList = new LinkedList<>();		
		int shinglesNumber = words.length - shingleSize;          
		//Create all shingles
		String shingle;
		if (words.length < shingleSize) {
			shingle = "";
			for (int j = 0; j < words.length; j++) {
				shingle = shingle + words[j];
			}
			shingleList.add(shingle);
		}
		else {
			for (int i = 0; i <= shinglesNumber; i++) { 
				shingle = ""; 
				//Create one shingle 
				for (int j = 0; j < shingleSize; j++) {
					if (shingleSize == 1 || j == shingleSize -1) {
						shingle = shingle + words[i+j];
					}
					else {
						shingle = shingle + words[i+j] + " ";
					}          
				} 
				shingleList.add(shingle); 
			}
		}					
		return shingleList;
	}

	public List<String> getShingleList() {
		return shingleList;
	}



	public void setShingleList(List<String> shingleList) {
		this.shingleList = shingleList;
	}

	public int getDocumentID() {
		return documentID;
	}

	public void setDocumentID(int documentID) {
		this.documentID = documentID;
	}

	@Override
	public String toString() {
		return "Shingle [documentID=" + documentID + ", shingleSize=" + shingleSize + ", shingleList=" + shingleList
				+ "]";
	}
}
