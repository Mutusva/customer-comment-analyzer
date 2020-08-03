package com.ikhokha.techcheck;

import com.ikhokha.techcheck.Operations.Operation;
import com.ikhokha.techcheck.Operations.OperatorFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CommentAnalyzer {
	
	private File file;
	
	public CommentAnalyzer(File file) {
		this.file = file;
	}
	
	public Map<String, Integer> analyze() {
		
	   	Map<String, Integer> resultsMap = new HashMap<>();
		Map<String, Operation> operationMap = OperatorFactory.operationMap;

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			
			String line = null;
			while ((line = reader.readLine()) != null) {

				String comment = line;

				operationMap.forEach((key, operator) -> {
                  if(operator.apply(comment)) {
                  	incOccurrence(resultsMap, key);
				  }
				});
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + file.getAbsolutePath());
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO Error processing file: " + file.getAbsolutePath());
			e.printStackTrace();
		}
		return resultsMap;
	}
	
	/**
	 * This method increments a counter by 1 for a match type on the countMap. Uninitialized keys will be set to 1
	 * @param countMap the map that keeps track of counts
	 * @param key the key for the value to increment
	 */
	private void incOccurrence(Map<String, Integer> countMap, String key) {
		countMap.putIfAbsent(key, 0);
		countMap.put(key, countMap.get(key) + 1);
	}

}
