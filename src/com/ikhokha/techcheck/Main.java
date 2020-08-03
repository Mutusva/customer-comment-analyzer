package com.ikhokha.techcheck;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
	// This controls the number of threads spawn
	private static final int NTHREDS = 10;

	public static void main(String[] args) {

		List<Future<Map<String, Integer>>> list = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);

		ConcurrentHashMap<String, Integer> totalResults = new ConcurrentHashMap<>();
				
		File docPath = new File("docs");
		File[] commentFiles = docPath.listFiles((d, n) -> n.endsWith(".txt"));

		for (File commentFile : commentFiles) {

			Callable<Map<String, Integer>> worker = new Callable<>() {
				CommentAnalyzer commentAnalyzer = new CommentAnalyzer(commentFile);
				@Override
				public Map<String, Integer> call() throws Exception {
					return commentAnalyzer.analyze();
				}
			};
			Future<Map<String, Integer>> submit= executor.submit(worker);
			list.add(submit);
		}
		// This will make the executor accept no new threads and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {
		}
		for (Future<Map<String, Integer>> future : list) {
			try {
				Map<String, Integer> fileResults = future.get();
				addReportResults(fileResults, totalResults);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		System.out.println("RESULTS\n=======");
		totalResults.forEach((k,v) -> System.out.println(k + " : " + v));
	}
	
	/**
	 * This method adds the result counts from a source map to the target map 
	 * @param source the source map
	 * @param target the target map
	 */
	private static void addReportResults(Map<String, Integer> source, ConcurrentHashMap<String, Integer> target) {

		for (Map.Entry<String, Integer> entry : source.entrySet()) {
			if (target.containsKey(entry.getKey()))	{
				target.put(entry.getKey(), entry.getValue() + target.get(entry.getKey()));
			}
			else {
				target.put(entry.getKey(), entry.getValue());
			}
		}
		
	}

}
