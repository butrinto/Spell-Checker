# SpellChecker Project
**SpellChecker** is a Java-based command-line spell-checking tool that allows users to check the spelling of words in a given text file against a predefined dictionary. It provides suggestions for misspelled words and allows users to update the dictionary or choose alternative words.

## Features
* **Spell Checking:** The program compares words in a text file against a dictionary to identify misspelled words.
* **Suggestions:** For each misspelled word, the program suggests alternative words based on the Levenshtein distance.
* **Dictionary Management:** Users can choose to add new words to the dictionary or provide alternative spellings.
* **File Handling:** The tool reads input from a text file and outputs results to another file.

## How to Run
### Prerequisites:
* Java Development Kit (JDK) installed on your machine.
* Text file with content for spell checking.

### Steps
1. Compile Java Files: <br>
Open a terminal or command prompt and navigate to the project directory. Compile both Java files:

``` 
  javac Spellchecker.java 
  javac SpellCheckerSuggestion.java
``` 
  <br>
2. Run SpellChecker: <br>
Execute the SpellChecker program by providing the text file as an argument:

```
java Spellchecker Text1.txt
``` 
If no file is provided, the program will prompt you to enter a valid file. <br>

3. Review Suggestions: <br>
For each misspelled word, the program will prompt you with suggestions. Choose from the presented options:
* +) Add the word to the dictionary. <br>
* -) Enter an alternative spelling. <br>
* Any other input terminates the program. <br> <br>

4. Run SpellCheckerSuggestion: <br>
Execute the SpellCheckerSuggestion program similarly:

``` 
java SpellCheckerSuggestion Text1.txt
```
Follow the prompts to review suggestions and update the dictionary or provide alternatives. <br> <br>

5. Updating the Dictionary <br>
The program maintains a dictionary in the dictionary.txt file. Newly added words or alternative spellings are appended to this file. <br>

### Contributions and Issues
Feel free to contribute to the project by submitting pull requests or reporting issues. Your feedback is valuable in enhancing the functionality of the SpellChecker tool.

#### License
This project is licensed under the MIT License.

Note: Ensure you have appropriate permissions to read and write files in the project directory. If you encounter any issues, check file permissions and provide the necessary access.
