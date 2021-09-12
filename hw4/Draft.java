package il.ac.tau.cs.sw1.ex4;

import java.io.File;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Draft {
	public static void main(String[] args) {
		//DONT FORGET TO ERASE AFTER USE!
		if (args.length==0) {
			System.out.println("Insert args");
		}
		String [] hi = {"hi", "you", "are", "1", "}"};
		Arrays.sort(hi);
		//System.out.println(Arrays.toString(hi));
		String a = "Hel";
		a = a.toLowerCase();
		//System.out.println(a);
		String input = "how are you doing?";
		Scanner scanner = new Scanner(input);
		String [] s = WordPuzzle.scanVocabulary(scanner);
		System.out.println(Arrays.toString(s));
		System.out.println(s.length);
		String [] words = input.split("\\s+");
		System.out.println(words.length);
		Scanner vocabularyscanner = new Scanner(input); 
		String [] vocabulary = WordPuzzle.scanVocabulary(vocabularyscanner);
		int vocabularySize = vocabulary.length;
		System.out.println("Read" +vocabularySize+ "words from" +args[0]);
		//Random generator = new MyRandom(WordPuzzle.getRrandomIntArr(vocabularySize), WordPuzzle.getRandomFloatArr());
		//String chosen_word = WordPuzzle.getRandomWord(hi, generator);
		//char [] new_puzzle = WordPuzzle.getRandomPuzzle(chosen_word,0.6,generator);
		//System.out.println(new_puzzle);
	}

}
