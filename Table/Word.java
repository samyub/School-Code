// Sam and Suhani


public class Word {
	// A string that represents a word
	private String data;

	/*
	Constructor: assigns data the value of String word

	@param   String word    the user input for the Word object
	*/
	public Word (String word) {
		data = word;
	}

	/*
	return true if the string and the other object are equal. returns false otherwise

	@param    Object other   an object
	*/
	public boolean equals(Object other){
		//NOT AI I PROMISE luna did
		if(other instanceof Word){
			return this.data.equals(((Word)other).data);
		}
		return false;
	}

	/*
	returns the hashcode of the word

	return: number of vowels multiplied by the length of the Word object mod 10 and the remainder
	*/
	public int hashCode(){
		String[] vowels = {"a", "e", "i", "o", "u"};
		int len = data.length();
		int vowelCount = 0;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < vowels.length; j++) {
				if(data.substring(i, i + 1).equals(vowels[j])) { 
					vowelCount++;
				}
			}
		}
		return (vowelCount * len) % 10;
	}

	// Data is returned
	public String toString(){
		return data;
	}
}
