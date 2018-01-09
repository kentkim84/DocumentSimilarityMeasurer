package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Runner {

	public static void main(String[] args) {
		String fileName1 = "DeBelloGallico.txt";
		String fileName2 = "WarAndPeace.txt";
		//int BOUND = 10;
		
		Random r = new Random(11);
		for (int i = 0; i < 10; i++){
			int a = (int)r.nextInt();
			int b = (int)r.nextInt();
			int c = (int)r.nextInt();
			System.out.println(a+" "+b+" "+c);
		}
		

		BlockingQueue<Shingle> blockingQueue = new LinkedBlockingQueue<>();
		Set<Integer> set1 = new TreeSet<Integer>();
		Set<Integer> set2 = new TreeSet<Integer>();

		new Thread(new FileParser(blockingQueue, fileName1), "TF1").start();
		new Thread(new FileParser(blockingQueue, fileName2), "TF2").start();
		
		new Thread(new Manager(blockingQueue, set1, set2), "TW1").start();		

	}
}