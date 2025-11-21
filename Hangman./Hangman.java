//Sam and Aarav
///We used the do while loop and ternary operator
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Hangman{
	// a constant int that determines the max length incorrectLetters can be
	static int MAX_LENGTH = 9;

	
	public static void main(String[] args){
		// the secret word
		String word;
		//arraylist of underscores that is the same length as secret word
		ArrayList<String> underscores = new ArrayList<>();
		//arraylist of failed attempts
		ArrayList<String> incorrectLetters = new ArrayList<>();
		//list of all secret words
		ArrayList<String> wordList = new ArrayList<>();
		//checks to see if the dashes string equals the secret word
        boolean isWordMatched = false;
		
        createWordList(wordList, args);

		int selection = (int)(Math.random() * wordList.size());
		word = wordList.get(selection);
       	createUnderscores(underscores, word);

		do{
			Scanner input = new Scanner(System.in);
			System.out.println("Enter a letter: ");
			String letter = input.nextLine();
			if (letter.length() == 1) {
				isWordMatched = guess(letter, underscores, incorrectLetters, word);
			}
			else {
				System.out.println("This is not an acceptable answer!");
			}
		}while (!isWordMatched && incorrectLetters.size() < MAX_LENGTH);
		System.out.println("Secret word is: "+word);
		gameResult(incorrectLetters);
	}

	/*takes an arraylist of all the incorrect guesses, incorrectLetters to check if it is below the 
	maximum length. 

	pre: incorrectLetters >= 0
	post: returns if the user has won or lost the game*/
	public static boolean gameResult(ArrayList<String> incorrectLetters) {
		boolean finalResult = incorrectLetters.size() < MAX_LENGTH;
        String result = finalResult ? "win" : "lose";
		System.out.println(result);
		return finalResult;
	}

	/*Takes the word modifies the arraylist underscores to have the same amount of characters as underscores.

	pre: word has been defined
	post: underscores had been modified
	*/
	public static ArrayList<String> createUnderscores(ArrayList<String> underscores, String word){
		for(int i = 0; i<word.length(); i++){
			underscores.add("_");
		}
		return underscores;
	}

	/* Adds words to wordList if the file entered does not exist or is empty

	pre: file does not exist or is empty
	post: 5 words are manually added to wordList
	*/
	public static void addWords (ArrayList<String> wordList) {
		wordList.add("apple");
		wordList.add("headphones");
		wordList.add("jack");
		wordList.add("waterbottle");
		wordList.add("people");
	}

	/* Takes an uploaded file and adds all the word to an arraylist called wordlist which contains all
	the secret words.

	pre: args exists
	post: wordList is updated
	exceptions: if user doesn't input anything, tell them to enter a file name. If the file does not exist,
	tell the user that we are adding 5 words ourselves. If the file uploaded is empty, 5 words will 
	be hardcoded.
	*/
	public static ArrayList<String> createWordList(ArrayList<String> wordList, String[] args) {
		if (args.length == 0) {
        	System.out.println("Please enter a file name!");
        }
        else {
        	try{
        	File myObj = new File(args[0]);
        	Scanner input = new Scanner(myObj);
        	while (input.hasNextLine()){
        		String data = input.nextLine();
        		if (data.length() != 0) {
        			wordList.add(input.nextLine());
        		}
        		else {
        			System.out.println("There are empty lines");
        		}
        	}
        	input.close();
        	if (wordList.size() == 0) {
        		addWords(wordList);
        	}
        	} catch(FileNotFoundException e){
        		System.out.println("Your file was not found! Please enter a valid file next time!");
        		addWords(wordList);
        	}
        }
        return wordList;
	}
	/*Takes a user input, letter, and compares it to a secret word. If the letter is in the String word, 
	then an arraylist of underscores, underscores, will be altered. If the letter is not in the secret letter, 
	it will beadded to a list of incorrect guesses, incorrectLetters.

	pre: user has given an input that is part of the alphabeth
	post: prints the status of the game */
	public static boolean guess(String letter, ArrayList<String> underscores, 
								ArrayList<String> incorrectLetters, String word) {
		int index = -1;
		boolean result = false;
		for (int i = 0; i < word.length(); i++) {
			if (word.substring(i, i + 1).equals(letter)) {
				index = i;
				underscores.set(i, letter);
				String newWord = convertDashesToString(underscores);
				if (newWord.equals(word)) {
			    	result = gameResult(incorrectLetters);
				}
			}
		}
		if (index < 0) {
		    	incorrectLetters.add(letter);
				if(incorrectLetters.size() >= MAX_LENGTH) {
			   		result = gameResult(incorrectLetters);

				}
			}
		System.out.println("Correct guess: "+ underscores);
		System.out.println("Incorrect guess: "+ incorrectLetters);
	    return result;
	}


	/*takes an arraylist of underscores, underscores, and converts it into a string

	pre: word has been chosen, underscores has undersores that matches the length
	post: returns a string of underscores*/
	public static String convertDashesToString(ArrayList<String> underscores) {
        String result = "";
        for (int i = 0; i < underscores.size(); i++) {
            result += underscores.get(i);
        }
        return result;
    }

}



