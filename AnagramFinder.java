import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramFinder {
	
	public static void main(String[] agrs) throws Exception {
		System.out.println("Welcome to the Anagram Finder");
		System.out.println("-----------------------------");

		// load
		long startLoad = System.currentTimeMillis();
		Map<String, List<String>> map = load();
		long endLoad = System.currentTimeMillis();
		System.out.println("Dictionary loaded in " + (endLoad - startLoad) + " ms");
		System.out.println();
		
		// accept input, and print out result
		System.out.print("AnagramFinder >");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String word = reader.readLine().trim();
		while (word != null && !word.equals("exit")) {
			long findStart = System.currentTimeMillis();
			List<String> anagrams = findAnagram(map, word);

			if(anagrams == null || anagrams.isEmpty()) {
				long findEnd = System.currentTimeMillis();
				System.out.println("No anagrams found for accept in " + (findEnd - findStart) + " ms");
			} else {
				long findEnd = System.currentTimeMillis();
				System.out.println("" + anagrams.size() + " Anagrams found for " + word + " in " + (findEnd - findStart) + " ms");
				System.out.println(anagrams.toString());
			}
			
			System.out.println();
			System.out.print("AnagramFinder >");
			word = reader.readLine().trim();
		}
	}
	
	public static List<String> findAnagram(Map<String, List<String>> map, String word) {
		char[] chars = word.toCharArray();
		Arrays.sort(chars);
		String identifier = new String(chars);
		return map.get(identifier);
	}
	
	// load words from dictionary.txt
	// and, map every word to its unique identifier
	// use the word's sorted-order as its unique identifier
	public static Map<String, List<String>> load() throws Exception {
		FileReader file = new FileReader("dictionary.txt");
		BufferedReader reader = new BufferedReader(file);
		
		Map<String, List<String>> map = new HashMap<>();
		String line = reader.readLine();
		while (line != null) {
			
			String word = line.trim();
			char[] chars = word.toCharArray();
			Arrays.sort(chars);
			String identifier = new String(chars);
			if(!map.containsKey(identifier)) {
				map.put(identifier, new ArrayList<String>());
			}
			map.get(identifier).add(word);
			
			line = reader.readLine();
		}
		return map;
	}
	
	

}
