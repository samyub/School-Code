import java.io.FileReader;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import java.util.HashMap; // Import HashMap class to make hashmap

public class Translator {
	// Hashmap that contains the spanish word and it's english defenition
	public static HashMap<String,String> translation = new HashMap<String, String>();

	/*
	Assigns values to the Hashmap, translation, by reading the file with all of the words and creates 
	the HashMap
	*/
	public static void main(String[] args) {
		try{
        	FileReader spanishToEnglish = new FileReader("espanol-english.dat");
        	Scanner translatedWords = new Scanner(spanishToEnglish);
        	while (translatedWords.hasNextLine()){
        		String data = translatedWords.nextLine();
        		String[] hasmapArray = data.split(" ");
        		translation.put(hasmapArray[0], hasmapArray[1]);
        	}	
        	translatedWords.close();
        } catch(FileNotFoundException e){
        	System.out.println("Your file was not found! Please enter a valid file next time!");
        }
        translate();
    }

    /*
	Using the HashMap translation, this method reads the words in the hardcoded spanish file. Once it get's the 
	values of the translateMe.in file, the method finds the value in HashMap created earlier and prints out the
	english version.

	pre: the translateMe.in file is located in the same folder
	post: the english translation of the spanish sentence.
    */
    public static void translate() {
    	try {
    		FileReader toTranslate = new FileReader("translateMe.in");
    		Scanner spanishLines = new Scanner(toTranslate);
    		while (spanishLines.hasNextLine()) {
    			String data = spanishLines.nextLine();
    			String[] spanishWords = data.split(" ");
    			for (int i = 0; i < spanishWords.length; i++) {
    				System.out.print(translation.get(spanishWords[i]) + " ");
    			}
    			System.out.println("");
    		} 
    		spanishLines.close();
    	} catch(FileNotFoundException e) {
    			System.out.println("wtv");
    	}
    }
}
