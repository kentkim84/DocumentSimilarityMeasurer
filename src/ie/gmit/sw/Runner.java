package ie.gmit.sw;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Runner {

	public static void main(String[] args) {
		String fileName1 = "DeBelloGallico.txt";
		String fileName2 = "WarAndPeace.txt";		
		Scanner sc = new Scanner(System.in);
		FileParser fp = new FileParser();
		
		System.out.println("*** Document Similarity Service ***\n<List of Files>");
		fp.showList();
		System.out.println("<End of List>\nEnter the First File Name: ");
		fileName1 = sc.nextLine();
		System.out.println("Enter the Second File Name: ");
		fileName2 = sc.nextLine();
	
		BlockingQueue<Shingle> blockingQueue = new LinkedBlockingQueue<>();
		new Thread(new FileParser(blockingQueue, fileName1), "TF1").start();
		new Thread(new FileParser(blockingQueue, fileName2), "TF2").start();
		
		new Thread(new Manager(blockingQueue), "TW1").start();
	}
}