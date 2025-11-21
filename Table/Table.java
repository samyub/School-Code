// Sam and Suhani
import java.io.FileReader;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import java.util.ArrayList;


public class Table {
	//Array of ArrayLists that h the values from the file
	private static ArrayList[] table = new ArrayList[10];

	/*
	Constructor: Initializes the Array of Arraylists so it is no longer null
	*/
	public Table () {
		for(int i = 0; i < 10; i++){
			table[i] = new ArrayList();
		}
	}

	/*
	Reads in the file given in CLI and makes a table based the words in the file. 
	
	@param  String[] args   filename

	pre: args.length > 0
	post: prints out the final table  
	exception: if filename is not found, the user is told and prompted to give a different name next
	time
	*/
	public static void main (String[] args) {
		String filename = args[0];
		Table myTable = new Table();
		try{
        	FileReader file = new FileReader(filename);
        	Scanner message = new Scanner(file);
        	while (message.hasNextLine()){
        		String data = message.nextLine();
        		Word newWord = new Word(data);
        		myTable.add(newWord);
        	}
        	message.close();
        } catch(FileNotFoundException e){
        	System.out.println("Your file was not found! Please enter a valid file next time!");
        }
        myTable.removeDoubles();
		System.out.println(myTable);
	}

	/*
	@param Word listWord  Object word 
	
	post: table is updated with all the values in the file put in the corresponding cell of table
	*/
	public void add(Word listWord) {
		int ind = listWord.hashCode();
		if(!table[ind].contains(listWord)){
			table[ind].add((Word)listWord);
		}
	}

	/*
	removes all doubles in the table
	post: table is changed, all the doubles are removed form it 
	*/
	public void removeDoubles() {
		for (int i = 0; i < 10; i++) {
			if (table[i].size() > 1) {
				for(int j = 0; j < table[i].size() - 1; j++){
					if(table[i].get(j).equals(table[i].get(j + 1))){
						table[i].remove(j);
						j--;
					}
				}
			}
		}
	}

	//Returns string representation of table
	public String toString() {
		String finalMessage = "";
		for (int i = 0; i < 10; i++) {
			finalMessage += (i + ": ");
			for (int j = 0; j < table[i].size(); j++) {
				finalMessage += (table[i].get(j) + " ");
			}
			finalMessage += "\n";
		}
		return finalMessage;
	}
}
