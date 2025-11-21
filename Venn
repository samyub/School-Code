import java.util.TreeSet;
import java.util.Collection;
import java.util.AbstractSet;
import java.util.AbstractCollection;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Venn {
	// TreeSet that contains the values of the first line 
	public static TreeSet<String> datasetA = new TreeSet<String>();
	// TreeSet that contains the values of the second line 
	public static TreeSet<String> datasetB = new TreeSet<String>();
	// String containing the inputted 
	public static String filename;


	/*
	Calls all the methods

	@param String[] args contains the CLI of the filename
	*/
	public static void main (String[] args) {
		filename = args[0];
		makeSet(filename);
	}

	/*
	The venn diagram of each 2 lines in the file is printed

	@param filename the user inputted file name

	pre: filename isn't null
	post: the TreeSets are cleared.
	exception: if the file doesn't exist in the folder, then user is told so and then prompted to give a 
	valid name
	*/
	public static void makeSet(String filename) {
		try {
		    Scanner messageLine = new Scanner(new FileReader(filename));
		    String numOfSets = messageLine.nextLine();
		    int count = Integer.valueOf(numOfSets); 
		    for (int i = 0; i < count; i++) {
		    	// Assigning the values in the first line to a dataset
		    	String dataA = messageLine.nextLine();
		    	String[] hashmapArrayA = dataA.split(" ");
		    	for (int j = 0; j < hashmapArrayA.length; j++) {
		    		datasetA.add(hashmapArrayA[j]);
		    	}
		    	// Assigning the values in the second line to a dataset
		    	String dataB = messageLine.nextLine();
		    	String[] hashmapArrayB = dataB.split(" ");
		    	for (int k = 0; k < hashmapArrayB.length; k++) {
		    		datasetB.add(hashmapArrayB[k]);
		    	}

		    	System.out.println("A: " + remove(datasetA, datasetB));
		    	System.out.println("B: " + remove(datasetB, datasetA));
		    	System.out.println("INTERSECTION:" + intersect(datasetA, datasetB));
		    	System.out.println("UNION: " + unify(datasetA, datasetB));
		    	System.out.println();

		    	datasetA.clear();
		    	datasetB.clear();
		    }
		messageLine.close();  
		} catch (FileNotFoundException e) {
		    System.out.println("File does not exist. Please give a valid name");
		}
	}

	/*
	Returns set of common elements between the elements of TreeSet a and TreeSet b

	@param TreeSet<String> a words 
	@param TreeSet<String> b words
	*/
	public static TreeSet<String> intersect(TreeSet<String> a, TreeSet<String> b){
		TreeSet<String> intersection = new TreeSet<String>(a);
		intersection.retainAll(b);
		return intersection;
	}

	/*
	Returns a treeset of all the elements in TreeSet a and b. 

	@param TreeSet<String> a words
	@param TreeSet<String> b words
	*/
	public static TreeSet<String> unify(TreeSet<String> a, TreeSet<String> b){
		TreeSet<String> union = new TreeSet<String>(a);
		union.addAll(b);
		return union;
	}

	/*
	Returns a TreeSet that of the elements in TreeSet a that is not in TreeSet b 

	@param TreeSet<String> a words
	@param TreeSet<String> b words
	*/
	public static TreeSet<String> remove(TreeSet<String> a, TreeSet<String> b){
		TreeSet<String> difference = new TreeSet<String>(a);
		difference.removeAll(b);
		return difference;
	}
}
