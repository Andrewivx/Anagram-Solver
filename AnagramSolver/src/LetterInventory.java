//Andrew  Yu
//CSE 143 BS TA:Raymond Webster Berry
//Homework 1
//The Letter inventory program is a class
//that when given a string will keep an inventory
//of the letters and can display/manipulate
//them in various ways

public class LetterInventory {

	// int array used to store the number of occurances of each character
	private int[] inventory;
	// int arraySize to store the total occurances of each character
	private int arraySize;

	// ALPHABET for amount of different letters in the alphabet
	private static final int ALPHABET = 26;

	// Pre: Must be given a String
	// Post: Constructs a LetterInventory object which keeps
	// the amount of each letter in an array, as well as the total
	// amount of characters
	// Parameters: String data, to create the array of ints
	public LetterInventory(String data) {
		inventory = new int[ALPHABET];
		String lowerData = data.toLowerCase();
		for (int i = 0; i < lowerData.length(); i++) {
			char currentChar = lowerData.charAt(i);
			// uses int representation of chars to determine
			// if it's a character or symbol
			if (Character.isAlphabetic(currentChar)) {
				int index = currentChar - 'a';
				inventory[index]++;
				arraySize++;
			}
		}
	}

	// post: returns the count of total characters stored in LetterInventory
	public int size() {
		return arraySize;
	}

	// post: returns true if count of total characters in LetterInventory
	public boolean isEmpty() {
		return arraySize == 0;
	}

	// pre: throws IllegalArgumentException if a non-alphabet character is
	// used, will throw IllegalArgumentException
	// post: returns the amount of a specific character
	// Parameters: char letter - used to figure out 
	// what character's count is being looked for
	public int get(char letter) {
		char lowerCaseChar = Character.toLowerCase(letter);
		if (!Character.isAlphabetic(lowerCaseChar)) {
			throw new IllegalArgumentException();
		}
		int placement = lowerCaseChar - 'a';
		return inventory[placement];
	}

	// post: returns a string representation of all characters
	// stored in the array in alphabetical order
	public String toString() {
		String characters = "";
		for (int i = 0; i < ALPHABET; i++) {
			if (inventory[i] > 0) {
				char currentChar = (char) ('a' + i);
				for (int j = 0; j < inventory[i]; j++) {
					characters += currentChar;
				}
			}
		}
		String finalString = ("[" + characters + "]");
		return finalString;
	}

	// post: adds the counts of two LetterInventory's
	// arrays together creating a new
	// array
	// Parameters: LetterInventory other
	// - the other object's array being added to the original object's array
	public LetterInventory add(LetterInventory other) {
		String newString = toString() + other.toString();
		LetterInventory newInventory = new LetterInventory(newString);
		return newInventory;
	}

	// pre: value must be a positive number, or else throws an
	// IllegalArgumentException
	// if a non-character is input for letter will also result in
	// IllegalArgumentException
	// post: sets the amount of a certain 
	// letter's count (stored in the array) to value
	// Parameters: char letter - 
	// the letter's count's within the array being changed
	// (which element)
	// int value - what the element of the array is being changed to
	public void set(char letter, int value) {
		char lowerCaseChar = Character.toLowerCase(letter);
		if (!Character.isAlphabetic(lowerCaseChar) || value < 0) {
			throw new IllegalArgumentException();
		}

		int index = lowerCaseChar - 'a';
		int oldCount = inventory[index];
		inventory[index] = value;
		arraySize += (value - oldCount);
	}

	// Post: subtracts the count of characters inbetween the two arrays
	// and puts the new counts in a new array
	// Parameters: LetterInventory other - 
	// the array's counts being subtracted from
	// the original LetterInventory
	public LetterInventory subtract(LetterInventory other) {
		String newString = "";
		char currentChar;
		for (int i = 0; i < ALPHABET; i++) {
			currentChar = (char) ('a' + i);
			int occurance = get(currentChar) - other.get(currentChar);
			if (occurance < 0) {
				return null;
			}
			for (int j = 0; j < occurance; j++) {
				newString += currentChar;
			}
		}
		LetterInventory newInventory = new LetterInventory(newString);
		return newInventory;
	}
}