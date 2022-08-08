//Andrew  Yu
//CSE 143 BS TA:Raymond Webster Berry
//Homework 6
//The anagram solver program finds combinations of words within 
//a dictionary that when combined create anagrams of a given word
//and prints out that combination of words, user has abiltiy to limit
//the amount of words in combination

import java.util.*;

public class AnagramSolver {
	//a list used to store the user input
	private List<String> dictionary;
	//maps all words to it's letter inventory
	private Map<String, LetterInventory> inventory;
	
	//Post: this method constructs the anagram solver and stores both
	//the client input, and client input's words being 
	//converted to a count of it's characters (a words letter inventory)
	//Parameters: List<String> list - a list of words to be used as dictionary
	public AnagramSolver(List<String> list) {
		dictionary = list;
		inventory = new HashMap<String, LetterInventory>();
		for (String word : list) {
			// creates new letter inventory per new word
			inventory.put(word, new LetterInventory(word));
		}

	}
	
	//Pre:throws an illegalargumentexception if max is less than 0
	//Post:finds a series of combinations of words, 
	//each combination will be max or less than max
	//in size, that when their letters are combined form an anagram of the given String. 
	// max- the max amount of words in a combination
	// string s - the string given who's characers will
	//be compared to the combinations of word's characters
	public void print(String s, int max) {
		if (max < 0) {
			throw new IllegalArgumentException();
		}
		LetterInventory userInventory = new LetterInventory(s);
		// create a sublist contains only relevant words
		List<String> releventWords = narrowDownList(userInventory);
		List<String> checkedWords = new ArrayList<String>();
		if(releventWords.isEmpty()) {
			System.out.println(checkedWords.toString());
		}
		// start working to solve puzzle by back recursive
		explore(userInventory, checkedWords, releventWords, max);
	}

	//Post: checks different combinations of words to find which combinations
	//are valid (what is considered valid detailed in print method)
	//Parameters:
	//	LetterInventory userInventory - a user's letter inventory 
	//to be compared to the combined words letter inventorys
	//	List<String> checkedWords - a list of words that have already been checked and kept
	//	List<String> releventWords - a series of relevant words from the dictionary
	//	int max - the max amount of words allowed to appear in a combination
	private void explore(LetterInventory userInventory, List<String> checkedWords,
			List<String> releventWords, int max) {

		for(String word : releventWords) {
		
			LetterInventory releventInventory = inventory.get(word);
			LetterInventory subtracted = userInventory.subtract(releventInventory);
			
			if(subtracted != null) {
		
				if(subtracted.size() == 0) {
					checkedWords.add(word);
					System.out.println(checkedWords.toString());
					checkedWords.remove(checkedWords.size()-1);
				}
				else {
					if(checkedWords.size()+1 < max || max == 0) {
						checkedWords.add(word);
						explore(subtracted, checkedWords, releventWords, max);
					}
				}
			}
		}
		//after a full iteration update the checked word back one node so it can run
		//recursevly
		if(checkedWords.size() > 0) {
			checkedWords.remove(checkedWords.size()-1);
		}
	}
	
	//Post: helper method that returns only words who's each letter is contained
	//within the given string and returns a list of those words.
	//Parameters: LetterInventory userInventory - a letter inventory of the user
	//word they pass in.
	private List<String> narrowDownList(LetterInventory userInventory) {
		List<String> narrowedWords = new ArrayList<String>();
		for (String word : dictionary) {
			LetterInventory wordInventory = inventory.get(word);
			if (userInventory.subtract(wordInventory) != null) {
				narrowedWords.add(word);
			}
		}
		return narrowedWords;
	}
}
