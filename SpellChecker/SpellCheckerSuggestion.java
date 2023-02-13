// When running on either terminal, cmd, or any other compiler,
// please do remember to use the argument of the text file; otherwise 
// it wil not run and prompt that you do enter an argument, an example
// will appear 

//java SpellCheckerSuggestion Text1.txt


import java.io.*;
import java.util.*;

public class SpellCheckerSuggestion {
	
	public static ArrayList<String> dictionary = new ArrayList<String>();
	public static ArrayList<String> text = new ArrayList<String>();

	//new arraylist that holds all the suggestions
	public static ArrayList<String> suggestions = new ArrayList<String>();

	public static void main(String[] args) throws IOException, FileNotFoundException {

		dictionary();

		//if no argument is is passed through
		if(args.length == 0) {

			System.out.println("\nYou have not entered a file you would like to spell check; \nPlease type one of the file inputs after the argument so that we can spell check it");
			System.out.println("\neg: java SpellCheckerSuggestion Text1.txt");
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

// Looks at individual text string against dictionary arraylist
	public static void compareFiles() throws IOException {

		for(int i = 0; i < text.size(); i++) {

			String puncc = text.get(i).replaceAll("(?!\")\\p{Punct}", "");
			String punc = puncc.toLowerCase();


			if(dictionary.contains(punc)) {

				update(text.get(i));
			}

			// -- otherwise, present user options
			else {

				for(int x = 0; x < dictionary.size(); x++) {

					int compare = computeLevenshteinDistance(punc, dictionary.get(x));

					if(compare < 2) {

						suggestions.add(dictionary.get(x));
					}
				}
				
				//if the suggestiin array is empty
				if(suggestions.size() == 0){

					System.out.println("\nThere seems to be no words similar to " + puncc);
					System.out.println("Would you like to add this word to the dictionary, or write your own alternative?");
					System.out.println("+) Add to Dictionary");
					System.out.println("-) Write your own Alternative\n");

					BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));      
						// Reading Data using readLine
			        String input2 = reader2.readLine();

	        		switch(input2) {
	        			case "+": 
	        				System.out.println(puncc + " has been added to the dictionary!");
	        				updateDictionary(punc);
	        				updateD(text.get(i));
	        				//suggestions.clear();
	        				break;
	        			case "-": 
	        				updatetxt(text.get(i)); 
	        				//suggestions.clear();
	        				break;			  
	       			}
				}

			else {

				//System.out.println(Arrays.toString(suggestions.toArray()));
		       //System.out.println(Arrays.toString(options.toArray()));	

		       Scanner UserInput = new Scanner(System.in);

		       String input1;

				System.out.println("\n" + puncc + " was not found, we have some suggestions for you");
				System.out.println(suggestionList());

				System.out.println("+) Add to dictionary");
				System.out.println("-) Not here? Write an alternative!\n");

				BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));      
					// Reading Data using readLine

		      	input1 = UserInput.nextLine();

		      	//switch method to identify every possible input
		        switch(input1) {

		        	case "1": 
		        			update(suggestions.get(0));
		        			System.out.println(punc + " has been changed to " + suggestions.get(0));
		        			suggestions.clear();
		        			break;
		        	case "2": 
		        			update(suggestions.get(1));
		        			System.out.println(punc + " has been changed to " + suggestions.get(1));
		        			suggestions.clear();
		        			break;
		        	case "3": 
		        			update(suggestions.get(2));
		        			System.out.println(punc + " has been changed to " + suggestions.get(2));
		        			suggestions.clear();
		        			break;
		        	case "4": 
		        			update(suggestions.get(3));
		        			System.out.println(punc + " has been changed to " + suggestions.get(3));
		        			suggestions.clear();
		        			break;
		        	case "5": 
		        			update(suggestions.get(4));
		        			System.out.println(punc + " has been changed to " + suggestions.get(4));
		        			suggestions.clear();
		        			break;
		        	case "+":
		        			System.out.println(puncc + " has been added to the dictionary!");
	        				updateD(text.get(i));
	        				updateDictionary(punc);
	        				suggestions.clear();
	        				break;
		        	case "-":
		        			updatetxt(text.get(i)); 
	        				suggestions.clear();
	        				break;	
	        		default:
	        				System.out.println("Wrong input! Please enter a different input");
	        				return;
				}
			}
		}
	}	
}

	//updates dictionary.txt file with any new strings
		public static void updateDictionary(String y){

			try(FileWriter fw = new FileWriter("dictionary.txt", true);
		        	BufferedWriter bw = new BufferedWriter(fw);
		        	PrintWriter out = new PrintWriter(bw)) {

						out.println("\n" + y);

					}

		        	catch(IOException e) {

		        		System.out.println("You have an error? Maybe check if there is a Text2 file");
					}


		}

		//updates the second text file with alternative strings
		public static void updatetxt(String t){

			 Reader r = new InputStreamReader(System.in);
	    			BufferedReader br = new BufferedReader(r);
	    			String str = null;

	    			try(FileWriter fw = new FileWriter("Text2.txt", true);
		        		BufferedWriter bw = new BufferedWriter(fw);
		        		PrintWriter out = new PrintWriter(bw))
	    			{
	            		//prompt the user to input data
	            		System.out.println("Please enter your alternative");
	            		str = br.readLine();

	            		out.print(str + " ");
	            		out.close();

	    			} 

	    			catch (IOException e) {

	        			e.printStackTrace();
	    			}
		}

		//add to the dictionary array
		public static void updateD(String d){

				dictionary.add(d);

				try(FileWriter fw = new FileWriter("Text2.txt", true);
		        	BufferedWriter bw = new BufferedWriter(fw);
		        	PrintWriter out = new PrintWriter(bw)) {

						out.print(d + " ");

					}

		        	catch(IOException e) {

		        		System.out.println("You have an error? Maybe check if there is a Text2 file");
					}

		}


		public static void update(String s){

				try(FileWriter fw = new FileWriter("Text2.txt", true);
		        	BufferedWriter bw = new BufferedWriter(fw);
		        	PrintWriter out = new PrintWriter(bw)) {

						out.print(s + " ");

					}

		        	catch(IOException e) {

		        		System.out.println("You have an error? Maybe check if there is a Text2 file");
					}

		}

		//using Levenshtein, for loop is used to create the suggestions
		public static String suggestionList() {
    	int counter = 1;
    	String menu = " ";

    	for(int i = 0; i < suggestions.size(); i++) {

    		if (counter < 8){
    		menu += ("\n" + counter + ") " + suggestions.get(i));
    		counter++;
    		}
    		
    		}

    		return menu;
    }

		private static int minimum(int a, int b, int c) {    

    	return Math.min(Math.min(a, b), c);                                      
    }            

    //Levenshtein algorithm,to compute if any strings in the dictionary are similar to the string that is selected from the text file   	                                                         
    public static int computeLevenshteinDistance(String lhs, String rhs) {

        int[][] distance = new int[lhs.length() + 1][rhs.length() + 1];        
                                                                                 
        for (int i = 0; i <= lhs.length(); i++)                                 
            distance[i][0] = i;        

        for (int j = 1; j <= rhs.length(); j++)                                 
            distance[0][j] = j;                                                  
                                                                                 
        for (int i = 1; i <= lhs.length(); i++)

            for (int j = 1; j <= rhs.length(); j++) 

                distance[i][j] = minimum(                                        
                        distance[i - 1][j] + 1,                                  
                        distance[i][j - 1] + 1,                                  
                        distance[i - 1][j - 1] + ((lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1));
                                                                                 
        return distance[lhs.length()][rhs.length()];                           
    }

}
