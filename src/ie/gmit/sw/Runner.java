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

		BlockingQueue<Shingle> blockingQueue = new LinkedBlockingQueue<>();
		new Thread(new FileParser(blockingQueue, fileName1), "TF1").start();
		new Thread(new FileParser(blockingQueue, fileName2), "TF2").start();
		
		new Thread(new Manager(blockingQueue), "TW1").start();		

	}
}