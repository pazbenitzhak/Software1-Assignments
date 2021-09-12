package il.ac.tau.cs.sw1.ex4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.File;

public class WordPuzzle {
	public static final char HIDDEN_CHAR = '_';
	public static final int MAX_VOCABULARY_SIZE = 3000;
	

	public static String[] scanVocabulary(Scanner scanner) { // Q - 1
		String[] array = new String[MAX_VOCABULARY_SIZE];
		return scanVocabularyRec(scanner, array);
		}
	
	public static String[] scanVocabularyRec(Scanner scanner, String[] array) {
		while (scanner.hasNext()&&findfirstnull(array)!=-1) {
			String word = scanner.next();
			if (word.length()>1&&isonlyletters(word)) {
				word = word.toLowerCase();
				array[findfirstnull(array)]=word;
			}
		}
		if (findfirstnull(array)==0) {//no legal words
			return array;
		}
		if (findfirstnull(array)!=-1) {//we finished going over the scanner
			String [] vocab = new String[findfirstnull(array)];
			System.arraycopy(array, 0, vocab, 0, findfirstnull(array));
			Arrays.sort(vocab);
			vocab = erasedups(vocab);
			return vocab; //the array is good to go, no dups and no nulls
		} 
		if (findfirstnull(array)==-1) {
			Arrays.sort(array);
			array = erasedups(array);
			if (array.length!=MAX_VOCABULARY_SIZE) { //we haven't fully completed going over the entire input
				return scanVocabularyRec(scanner, array); //do the same process with the new array
			} else { //we have completed going over the input
				return array;
			}
		}
		return array;
	}

	public static int countHiddenInPuzzle(char[] puzzle) { // Q - 2
		int count = 0;
		for (int i =0;i<puzzle.length;i++) {
			if (puzzle[i] == HIDDEN_CHAR) {
				count+=1; //than '_' appears one time and we count
			}
		}
		return count; //if no '_' than count=0
	}

	public static String getRandomWord(String[] vocabulary, Random generator) { // Q - 3
		int randomInt = generator.nextInt(vocabulary.length);
		return vocabulary[randomInt];
	}

	public static boolean checkLegal(String word, char[] puzzle) { // Q - 4
		if ((countHiddenInPuzzle(puzzle)==0)||(countHiddenInPuzzle(puzzle)==puzzle.length)) {
			return false;
		}
		for (int i = 0; i<word.length();i++) {
			for (int j = 0; j<word.length();j++) {
				if (word.charAt(i)==word.charAt(j)) {
					boolean check_not_hidden = (puzzle[i]!=HIDDEN_CHAR&&puzzle[j]!=HIDDEN_CHAR);
					boolean check_hidden = (puzzle[i]==HIDDEN_CHAR&&puzzle[j]==HIDDEN_CHAR);
					if (!(check_not_hidden||check_hidden)) {
						return false;
					}
				}
			}

		}
		return true;
	}

	public static char[] getRandomPuzzleCandidate(String word, double prob, Random generator) { // Q - 5
		char[] new_word = new char[word.length()]; //"building block"
		for (int i = 0; i<word.length();i++) {
			float randomdouble = generator.nextFloat();
			if (randomdouble<=prob) {
				new_word[i]=HIDDEN_CHAR;
			} else {
				new_word[i]=word.charAt(i);
			}
		} //now we have a new hidden word in a char array
		return new_word;
	}

	public static char[] getRandomPuzzle(String word, double prob, Random generator) { // Q - 6
		for (int i =0;i<1000;i++) {
			char [] puzzquiz = getRandomPuzzleCandidate(word,prob,generator);
			if (checkLegal(word,puzzquiz)) {
				return puzzquiz;
			}
		}
		throwPuzzleGenerationException();
		return null;
	}

	public static int applyGuess(char guess, String solution, char[] puzzle) { // Q - 7
		int count = 0;
		for (int i =0;i<solution.length();i++) {
			if (guess==solution.charAt(i)&&puzzle[i]==HIDDEN_CHAR) {
				puzzle[i] = guess;
				count +=1;
			}
		}
		return count;
	}

	public static char[] getHelp(String solution, char[] puzzle) { // Q - 8
		int letter_index = 0;
		for (int i =0;i<puzzle.length;i++) {
			if (puzzle[i]==HIDDEN_CHAR) { //first occurrence of '_'
				letter_index = i; //we found the first and relevant occurrence
				break;  //no need for more iterations
			}
		}
		char letter_to_reveal = solution.charAt(letter_index); //the char to reveal
		for (int j=0;j<solution.length();j++) {
			if (solution.charAt(j)==letter_to_reveal) { //than we might change it in the puzzle
				puzzle[j] = letter_to_reveal; //change the puzzle accordingly
			}
		}
		return puzzle;
	}

	public static void main(String[] args) throws Exception { // Q - 9
		if (args.length==0) {
		System.out.println("No arguments given");
			return;
		}
		Scanner vocabularyscanner = new Scanner(new File(args[0])); 
		String [] vocabulary = scanVocabulary(vocabularyscanner);
		int vocabularySize = vocabulary.length;
		Random generator = new MyRandom(getRrandomIntArr(vocabularySize), getRandomFloatArr());
		System.out.println("Read " +vocabularySize+ " words from " +args[0]);
		Scanner s = new Scanner(System.in);
		System.out.println("--- Setting stage ---");
		System.out.println("Enter your hiding probability: ");
		float prob = s.nextFloat();
		int count = 0;
		String chosen_word = getRandomWord(vocabulary, generator);
		char[] new_puzzle = getRandomPuzzle(chosen_word,prob,generator);
		while (count==0) { //loop for asking to replace the puzzle
			System.out.println(new_puzzle);
			System.out.println("Replace puzzle?");
			String option = s.next();
			if (option.equals("yes")) {
				chosen_word = getRandomWord(vocabulary, generator);
				new_puzzle = getRandomPuzzle(chosen_word,prob,generator);
				continue;
			}
			if (option.equals("no")) {
				count = 1;
			}
			else {
				int second_count = 0;
				while (second_count==0) { // loop for when answer not clear
					System.out.println("Replace puzzle?");
					String option2 = s.next();
					if (option2.equals("yes")) {
						second_count = 1;
						chosen_word = getRandomWord(vocabulary, generator);
						new_puzzle = getRandomPuzzle(chosen_word,prob,generator);
						continue;
					}
					if (option2.equals("no")) {
						second_count = 1;
						count = 1;
				}
				}
				
			}
				
		}
		
		System.out.println("--- Game stage ---"); //off we go
		int tries = countHiddenInPuzzle(new_puzzle)+3;
		while (tries >0) {
			System.out.println(new_puzzle);
			System.out.println("Enter your guess: ");
			String first_guess = s.next();
			char letter_guess = first_guess.charAt(0);
			int hidden_before = countHiddenInPuzzle(new_puzzle);
			applyGuess(letter_guess,chosen_word,new_puzzle);
			int hidden_after = countHiddenInPuzzle(new_puzzle);
			if (hidden_after==0&&hidden_before!=hidden_after) {
				System.out.println("Congratulations! You solved the puzzle");
				return;
			}
			if (hidden_after!=0&&hidden_before!=hidden_after) {
				tries-=1;
				System.out.println("Correct Guess, " +tries+ " guesses left");
			}
			if (hidden_before==hidden_after) {
				if (letter_guess!='H') {
					tries-=1;
					System.out.println("Wrong guess, " +tries+ " guesses left");
				}
				else {
					getHelp(chosen_word, new_puzzle);
					tries-=1;
					if (countHiddenInPuzzle(new_puzzle)==0) {
						System.out.println("Congratulations! You solved the puzzle");
						return;
					}
				}
			}
		}
		System.out.println("Game over!");
		
		
		
		// Uncomment only one of the generators:
		// Random generator = new MyRandom(new int[]{0,1,2,3,4,5},new float[]{0.0f,0.1f,0.2f,0.3f,0.4f,0.5f,0.6f,0.7f,0.8f,0.9f,1.0f});
		
		// Random generator = new Random ();

		// replace with your code
	}

	/*************************************************************/
	/********************* Don't change this ********************/
	/*************************************************************/
	private static float[] getRandomFloatArr() {
		Double[] doubleArr = new Double[] { 0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9, 1.0 };
		List<Double> doubleList = Arrays.asList(doubleArr);
		Collections.shuffle(doubleList);
		double[] unboxed = doubleList.stream().mapToDouble(Double::doubleValue).toArray();

		// cast double array to float array
		float[] floatArr = new float[unboxed.length];
		for (int i = 0; i < unboxed.length; i++) {
			floatArr[i] = (float) unboxed[i];
		}
		return floatArr;
	}

	private static int[] getRrandomIntArr(int vocabularySize) {
		
		if(vocabularySize<0) {
			throw new RuntimeException("Wrong use of getRandomIntArr(int vocabularySize)");
		}
		
		int i = 0;
		Integer[] intArr = new Integer[vocabularySize];
		while (i < vocabularySize) {
			intArr[i] = i;
			i++;
		}
		List<Integer> doubleList = Arrays.asList(intArr);
		Collections.shuffle(doubleList);
		int[] unboxed = doubleList.stream().mapToInt(Integer::intValue).toArray();
		return unboxed;
	}

	public static void throwPuzzleGenerationException() {
		throw new RuntimeException("Failed creating a legal puzzle after 1000 attempts!");
	}

	public static void printReadVocabulary(String vocabularyFileName, int numOfWords) {
		System.out.println("Read " + numOfWords + " words from " + vocabularyFileName);
	}

	public static void printSettingsMessage() {
		System.out.println("--- Settings stage ---");
	}

	public static void printEnterHidingProbability() {
		System.out.println("Enter your hiding probability:");
	}

	public static void printPuzzle(char[] puzzle) {
		System.out.println(puzzle);
	}

	public static void printReplacePuzzleMessage() {
		System.out.println("Replace puzzle?");
	}

	public static void printGameStageMessage() {
		System.out.println("--- Game stage ---");
	}

	public static void printEnterYourGuessMessage() {
		System.out.println("Enter your guess:");
	}

	public static void printCorrectGuess(int attemptsNum) {
		System.out.println("Correct Guess, " + attemptsNum + " guesses left");
	}

	public static void printWrongGuess(int attemptsNum) {
		System.out.println("Wrong Guess, " + attemptsNum + " guesses left");
	}

	public static void printWinMessage() {
		System.out.println("Congratulations! You solved the puzzle");
	}

	public static void printGameOver() {
		System.out.println("Game over!");
	}
	
	public static boolean isonlyletters(String s) {
		for (int i = 0; i<s.length();i++) {
			char current = s.charAt(i);
			int ascii_value = current;
			if ((ascii_value>=65&&ascii_value<=90)||(ascii_value>=97&&ascii_value<=122)) {
				continue;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public static int findfirstnull(String [] args) {
		for (int i = 0;i<args.length;i++) {
			if (args[i]==null) {
				return i;
			}
		}
		return -1;
	}
	
	public static String[] erasedups(String[] args) {
		for (int i = 0; i<args.length-1;i++) {
			if (args[i].equals(args[i+1])) {
				args[i]="0";
			}
		}
		Arrays.sort(args);
		int start = 0;
		for (int i=0;i<args.length;i++) {
			if (isonlyletters(args[i])) {
				start = i;
				break;
			}
		}
		String [] new_array = new String[args.length-(start)];
		System.arraycopy(args, start, new_array, 0, args.length-(start));
		return new_array;
	}

}
