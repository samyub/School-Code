///Suhani and Sam

import java.io.FileReader;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.TreeMap; // Import HashMap class to make hashmap

public class Frequency {
    //Treemap containing all the values of the file given by the user 
	public static TreeMap<String, Integer> message = new TreeMap<>();
    //String contianing the name of the file
    public static String filename;
    // An integer that decides what the user wants to do
    public static int prompt;

    /*
    This method calls makeTreeMap with creates the tree map and it gives the user options to choose from
    on what they want to 

    pre: args.length > 0
    exception: if args.length is not greater than 0, the user is prompted to give a file name
    */
	public static void main(String[] args) {
		if (args.length == -1) {
            System.out.println("Please enter a file name");
        }
        makeTreeMap(args[0]);
        if (message.size() > 0) {
            getPrompt();
        }
        else {
            System.out.println("Your file is empty, please add something to it!");
        }
    }

    /*
    All the values in the file are put into the treemap
    
    pre: A filename has been inputted
    post: message (the treemap) is updated
    expection: If the files doesn't exist, the user is told so.
    */
    public static void makeTreeMap(String filename) {
        String sentence = "";
        try {
            Scanner messageLine = new Scanner(new FileReader(filename));
            while (messageLine.hasNext()) {
                sentence = messageLine.next();
                if(message.get(sentence) == null){
                    message.put(sentence, 1);
                }
                else{
                    message.put(sentence, (message.get(sentence) + 1));
                }
            }
            messageLine.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /*
    The user is given 4 different options of what they want the program to do. Based on what they choose,
    a switch is called and the appropriate methods are called.
    
    pre: message (the treemap) has a length greater than 0/it isn't empty
    post: appropirate method is called
    expection: if a number 1-4 is not put in, the user is told to change their answer.
    */
    public static void getPrompt() {
    	Scanner input = new Scanner(System.in);
    	System.out.println("Please enter the option you would like: ");
    	System.out.println("1. Search for a word and get its frequency");
    	System.out.println("2. Display all words sorted lexicographically with their frequency: "); 
    	System.out.println("3. Display all the words sorted by frequency with their frequency");
    	System.out.println("4. Quit");
        System.out.print("Enter a number here: ");
    	prompt = input.nextInt();
    	input.nextLine();
    	switch (prompt) {
    		case 1: System.out.println("What word would you like to search for?");
    				String word = input.nextLine();
    				searchWord(word);
    				break;
    		case 2: lexiographicOrder();
    				break;
    		case 3: numericalOrder();
    				break;
    		case 4: System.out.println("Program has been ended!");
    				break;
    		default: System.out.println("Please enter a number 1-4!");
    	}
    }

    /*
    The tree map is searched for wordToSearchFor (the word inputted by the user) and it's frequency is 
    printed. If the word does not exist, then the user is informed. 

    pre: the user has given a word they want to search for and prompt is equals to 1
    post: the word and it's frequency are printed
    */
    public static void searchWord(String wordToSearchFor) {
        Integer count = message.get(wordToSearchFor);
        if(count != null){
            System.out.println(wordToSearchFor + " appears " + count + " times.");
        }
        else{
            System.out.println(wordToSearchFor + " was not found in the message.");
        }   
    }

    /*
    Because treemaps are already sorted lexiographically, no additional work has to be done. This method
    simply prints out the tree map

    pre: prompt is equal to 2
    */
    public static void lexiographicOrder() {
    	for(Map.Entry<String, Integer> entry : message.entrySet()){
    		System.out.print(entry.getKey() + " ");
    		System.out.println(entry.getValue());
    	}
    }

    /*
    The method itereated through temp (a temporary tree map containing the same values as message) and finds
    the greatest frequency and saves it in maxVal. It then prints out the maxVal and then sets the index to
    -1 so it gets removed for temp. This makes it easier to make sure that none of the values are repated.
    
    pre: prompt is equal to 3
    post: all the values of temp/message are printed out
    */
    public static void numericalOrder() {
        TreeMap<String, Integer> temp = new TreeMap<>();
        for(Map.Entry<String, Integer> entry : message.entrySet()){
            temp.put(entry.getKey(), entry.getValue());
        }
        // resets the method each time so that all the values in the treemaps are checked
        for(int i = 0; i < temp.size(); i++){
            String maxWord = null;
            int maxVal = -1;
            // compares all of the values to the previous value
            for(Map.Entry<String, Integer> entry : temp.entrySet()){
                int val = entry.getValue();
                if(val >= 0){
                    if(val > maxVal){
                        maxVal = val;
                        maxWord = entry.getKey();
                    }
                }

            }
            if(maxWord != null){
                System.out.print(maxWord + " ");
                System.out.println(maxVal);
                temp.put(maxWord, -1);
            }
        }
    }
}
