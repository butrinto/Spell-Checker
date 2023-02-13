// When running on either terminal, cmd, or any other compiler,
// please do remember to use the argument of the text file; otherwise 
// it wil not run and prompt that you do enter an argument, an example
// will appear 

// java SpellChecker Text1.txt

import java.io.*;
import java.util.*;

public class Spellchecker {
	
	public static ArrayList<String> dictionary = new ArrayList<String>();
	public static ArrayList<String> text = new ArrayList<String>();

	public static void main(String[] args) throws IOException, FileNotFoundException {

		if(args.length == 0){

			System.out.println("\nYou have not entered a file you would like to spell check; \nPlease type one of the file inputs after the argument so that we can spell check it");
			System.out.println("\njava Spellchecker Text1.txt");
			return;
		}

		try {

		textFile(args[0]);
		dictionary();
		compareFiles();
		}

		catch(FileNotFoundException e){

			System.out.println("\nYou have entered an invalid text file you would like to run; please check what the input of the tezt file that you're using is\n");
		}
	}

// Method to add strings into the dictionary arraylist
	// Adding to arraylist so that if two words are similar it will parse through
	public static void dictionary() throws FileNotFoundException {
		File diction = new File("dictionary.txt");
		Scanner sc1 = new Scanner(diction);
		
		while (sc1.hasNext()) {
			dictionary.add(sc1.next());
		}
	}

// Method that adds the next word into the text arraylist
	public static void textFile(String s) throws FileNotFoundException {
		File spelling = new File(s);
		Scanner sc2 = new Scanner(spelling);

		while(sc2.hasNext()) {
			text.add(sc2.next());
		}
	}

//Method to update the dictionary file; is executed when the code is complete
	public static void updateDictionary(String y){

		try(FileWriter fw = new FileWriter("dictionary.txt", true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw)) {

			out.println(y);

			}

		catch(IOException e) {

		    System.out.println("You have an error? Maybe check if there is a Text2 file");
		}
	}

// Looks at individual text string against dictionary arraylist
	public static void compareFiles() throws IOException {
		for(int i = 0; i < text.size(); i++){

			// Removes punctuation and any capital letters from text
			String punc = text.get(i).replaceAll("(?!\")\\p{Punct}", "").toLowerCase();

			// Prints string into text file if it is in the dictionary -- 
			if(dictionary.contains(punc)){

				try(FileWriter fw = new FileWriter("Text2.txt", true);
		        	BufferedWriter bw = new BufferedWriter(fw);
		        	PrintWriter out = new PrintWriter(bw)) {

						//Using original "text.get(i)" to maintain any original capital letters or punctuation
						out.print(text.get(i) + " ");
					}

					// Catches error, printing error reason
		        	catch(IOException e) {

		        		System.out.println("You have an error!! Maybe check if there is a Text2.txt file");
					}
			}

			// -- otherwise, present user options
			else {

				System.out.println("\n" + text.get(i).replaceAll("(?!\")\\p{Punct}", "") + " was not found in the dictionary");
				System.out.println("\nWould you like to add it?");

				System.out.println("\n+) Add to Dictionary");
				System.out.println("-) Enter an Alternative");

		        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));         
				
				// Reading Data using readLine
		        String input = reader.readLine();
		        //System.out.println(input);

		        if(input.equals("+")){

		        	System.out.println("\nSuccesfully added to Dictionary\n\n\n");
		        	updateDictionary(punc);
					dictionary.add(punc);

		        	try(FileWriter fw = new FileWriter("Text2.txt", true);
		        		BufferedWriter bw = new BufferedWriter(fw);
		        		PrintWriter out = new PrintWriter(bw)) {

						out.print(text.get(i) + " ");

						//System.out.println(Arrays.toString(dictionary.toArray()));
					}

		        	catch(IOException e) {

		        		System.out.println("You have an error? Maybe check if there is a Text2 file");
					}
		        }

		       	else if(input.equals("-")){
		        
			        Reader r = new InputStreamReader(System.in);
	    			BufferedReader br = new BufferedReader(r);
	    			String str = null;

	    			try(FileWriter fw = new FileWriter("Text2.txt", true);
		        		BufferedWriter bw = new BufferedWriter(fw);
		        		PrintWriter out = new PrintWriter(bw))
	    			{

	            		//Prompt the user to input data
	            		System.out.println("Please enter your alternative");
	            		str = br.readLine();

	            		//prints into text file
	            		out.println(str);
	            		out.close();
	    			} 

	    			catch (IOException e) {

	        			e.printStackTrace();
	    			}
				}
		        
		        else{

		        	//If neither + or - were inputted, the program will terminate
		        	System.out.println("\nIncorrect input, please enter either 1 or 2\n\n");
		        	return;
				}
			}
		}
	}
}
