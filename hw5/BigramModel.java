package il.ac.tau.cs.sw1.ex5;


import java.io.IOException;
import java.util.Arrays;
import java.io.*;

public class BigramModel {
	public static final int MAX_VOCABULARY_SIZE = 14500;
	public static final String VOC_FILE_SUFFIX = ".voc";
	public static final String COUNTS_FILE_SUFFIX = ".counts";
	public static final String SOME_NUM = "some_num";
	public static final int ELEMENT_NOT_FOUND = -1;
	
	String[] mVocabulary;
	int[][] mBigramCounts;
	
	// DO NOT CHANGE THIS !!! 
	public void initModel(String fileName) throws IOException{
		mVocabulary = buildVocabularyIndex(fileName);
		mBigramCounts = buildCountsArray(fileName, mVocabulary);
		
	}
	
	
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public String[] buildVocabularyIndex(String fileName) throws IOException{ // Q 1
		String[] array = new String[MAX_VOCABULARY_SIZE];
		return buildVocabularyIndexRec(fileName, array);
	}
	
	public String[] buildVocabularyIndexRec(String fileName, String[] array) throws IOException{
		File orig_file = new File(fileName);
		BufferedReader buffread = new BufferedReader(new FileReader(orig_file));
		String line = buffread.readLine();
		int count = 0;
		while ((line!=null)&&(findfirstnull(array)!=-1)) {
			String[] Lines_words = line.split(" ");
			for (int i =0; i<Lines_words.length;i++) {
				String word = Lines_words[i];
				if (islegalword(word)) {
					if (isinarray(word.toLowerCase(),array,findfirstnull(array))) {
						continue;
					} else {
						word = word.toLowerCase();
						array[findfirstnull(array)]=word;
					}
				}
					
				if (islegalint(word)) { // than no english letter, maybe int
					//if (isinarray(SOME_NUM,array,findfirstnull(array))) {
					if (count!=0) {
						continue;
					} else {
						String intstring = SOME_NUM;
						array[findfirstnull(array)]=intstring;
						count+=1;
					}		
				}
				
			}
			line = buffread.readLine();
		}
		buffread.close();
		if (findfirstnull(array)==0) {//no legal words
			String[] empty_string = new String[0];
			return empty_string;
		}
		if (findfirstnull(array)!=-1) {//line is null we finished going over the scanner
			String [] vocab = new String[findfirstnull(array)];
			System.arraycopy(array, 0, vocab, 0, findfirstnull(array));
			return vocab; //the array is good to go, no dups and no nulls
		} 
		buffread.close();
		if (findfirstnull(array)==-1) {//array is full
			
				return array;
		}
		return array;
	
	}
	
	
	/*
	 * @post: mVocabulary = prev(mVocabulary)
	 * @post: mBigramCounts = prev(mBigramCounts)
	 */
	public int[][] buildCountsArray(String fileName, String[] vocabulary) throws IOException{ // Q - 2
		int [][] countsarray = new int [vocabulary.length][vocabulary.length];
		File orig_file = new File(fileName);
		BufferedReader buffread = new BufferedReader(new FileReader(orig_file));
		String line = buffread.readLine();
		while (line!=null) {
			String[] Lines_words = line.split(" ");
			for (int i =0;i<Lines_words.length-1;i++) {
				String[] temparray = {Lines_words[i], Lines_words[i+1]};
				int count = 0;
				for (int j =0;j<temparray.length;j++) {
					String toadd = temparray[j].toLowerCase();
					if (isinarray(toadd,vocabulary,vocabulary.length)) {
						temparray[j]=toadd;
						count+=1;
					}
					if (islegalint(temparray[j])&&isinarray(SOME_NUM,vocabulary,vocabulary.length)) {
						temparray[j] = SOME_NUM;
						count+=1;}
				    if (count==2) {
					int x = findindex(temparray[0],vocabulary);
					int y = findindex(temparray[1],vocabulary);
					countsarray[x][y]+=1;
				}
			}
		}
			line = buffread.readLine();
		}
		buffread.close();
		return countsarray;
	}
	
	
	/*
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: fileName is a legal file path
	 */
	public void saveModel(String fileName) throws IOException{ // Q-3
		String [] voc = this.mVocabulary;
		int [][] count = this.mBigramCounts;
		File vocfile = new File("filename"+ VOC_FILE_SUFFIX);
		BufferedWriter vocwriter = new BufferedWriter(new FileWriter(vocfile));
		String line = +voc.length+ " words";
		vocwriter.write(line+"\r\n");
		for (int i =0;i<voc.length;i++) {
			line = +i+ "," + voc[i];
			vocwriter.write(line+"\r\n");
		}
		vocwriter.close();
		File countfile = new File("filename" + COUNTS_FILE_SUFFIX);
		BufferedWriter countwriter = new BufferedWriter(new FileWriter(countfile));
		for (int i=0;i<voc.length;i++) {
			for (int j=0;j<voc.length;j++) {
				if (count[i][j]>0) {
					line = +i+ "," +j+ ":"+ +count[i][j]++;
					countwriter.write(line+"\r\n");
				}
			}
		}
		countwriter.close();
		
	}
	
	
	
	/*
	 * @pre: fileName is a legal file path
	 */
	public void loadModel(String fileName) throws IOException{ // Q - 4
		File voc_file = new File("filename"+ VOC_FILE_SUFFIX);
		BufferedReader buffread1 = new BufferedReader(new FileReader(voc_file));
		String line = buffread1.readLine();
		String[] Lines_words = line.split(" ");
		String [] vocstring = new String [Integer.parseInt(Lines_words[0])];
		line = buffread1.readLine();
		while (line!=null) {
			Lines_words = line.split(",");
			vocstring[Integer.parseInt(Lines_words[0])]=Lines_words[1];
			line = buffread1.readLine();
		}
		this.mVocabulary = vocstring;
		buffread1.close();
		
		File counts_file = new File("filename"+ COUNTS_FILE_SUFFIX);
		BufferedReader buffread2 = new BufferedReader(new FileReader(counts_file));
		int [][] countstring = new int [vocstring.length][vocstring.length];
		line = buffread2.readLine();
		while (line!=null) {
			String [] first_split = line.split(",");
			String[] second_split = first_split[1].split(":");
			int x = Integer.parseInt(first_split[0]);
			int y = Integer.parseInt(second_split[0]);
			int times = Integer.parseInt(second_split[1]);
			countstring[x][y]=times;
			line = buffread2.readLine();
		}
		this.mBigramCounts = countstring;
		buffread2.close();
	}

	
	
	/*
	 * @pre: word is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: word is in lowercase
	 * @post: $ret = -1 if word is not in vocabulary, otherwise $ret = the index of word in vocabulary
	 */
	public int getWordIndex(String word){  // Q - 5
		if (!isinarray(word,mVocabulary,mVocabulary.length)) {
			return ELEMENT_NOT_FOUND;
		}
		return findindex(word,mVocabulary);
	}
	
	
	
	/*
	 * @pre: word1, word2 are in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the count for the bigram <word1, word2>. if one of the words does not
	 * exist in the vocabulary, $ret = 0
	 */
	public int getBigramCount(String word1, String word2){ //  Q - 6
		if (getWordIndex(word1)==ELEMENT_NOT_FOUND||getWordIndex(word2)==ELEMENT_NOT_FOUND) {
			return 0;
		}
		return mBigramCounts[getWordIndex(word1)][getWordIndex(word2)];
	}
	
	
	/*
	 * @pre word in lowercase, and is in mVocabulary
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post $ret = the word with the lowest vocabulary index that appears most fequently after word (if a bigram starting with
	 * word was never seen, $ret will be null
	 */
	public String getMostFrequentProceeding(String word){ //  Q - 7
		int x = getWordIndex(word);
		int appearences=0;
		String matchword ="";
		for (int i=0; i< mVocabulary.length;i++) {
			if (mBigramCounts[x][i]>appearences) {
				appearences = mBigramCounts[x][i];
				matchword = mVocabulary[i];
			}
		}
		if (appearences==0) {
			return null;
		}
		return matchword;
	}
	
	
	/* @pre: sentence is in lowercase
	 * @pre: the method initModel was called (the language model is initialized)
	 * @pre: each two words in the sentence are are separated with a single space
	 * @post: if sentence is is probable, according to the model, $ret = true, else, $ret = false
	 */
	public boolean isLegalSentence(String sentence){  //  Q - 8
		if (sentence.equals("")) {
			return true;
		}
		String[] words = sentence.split(" ");
		if ((words.length==1)&&isinarray(words[0],mVocabulary,mVocabulary.length)) {
			return true;
		}
		if ((words.length==1)&&!isinarray(words[0],mVocabulary,mVocabulary.length)) {
			return false;
		}
		for (int i =0;i<words.length-1;i++) {
			String[] temparray = {words[i], words[i+1]};
			if (getBigramCount(temparray[0],temparray[1])==0) {
				return false;
			}
		}
		return true;
	}
	
	
	
	/*
	 * @pre: arr1.length = arr2.legnth
	 * post if arr1 or arr2 are only filled with zeros, $ret = -1, otherwise calcluates CosineSim
	 */
	public static double calcCosineSim(int[] arr1, int[] arr2){ //  Q - 9
		int[] testarray = new int[arr1.length];
		if (Arrays.equals(arr1, testarray)||Arrays.equals(arr2, testarray)) {
			return -1;
		}
		int sum_1=0;
		int sum_2=0;
		int combined_sum=0;
		for (int i = 0;i<arr1.length;i++) {
			sum_1+=arr1[i]*arr1[i];
			sum_2+=arr2[i]*arr2[i];
			combined_sum+=arr1[i]*arr2[i];
		}
		double cos_im = combined_sum/(Math.sqrt(sum_1)*Math.sqrt(sum_2));
		return cos_im;
	}

	
	/*
	 * @pre: word is in vocabulary
	 * @pre: the method initModel was called (the language model is initialized), 
	 * @post: $ret = w implies that w is the word with the largest cosineSimilarity(vector for word, vector for w) among all the
	 * other words in vocabulary
	 */
	public String getClosestWord(String word){ //  Q - 10
		if (mVocabulary.length==1) {
			return mVocabulary[0];
		}
		int [] word_rep_vector = new int[mVocabulary.length];
		for (int i = 0;i<mVocabulary.length;i++) {
			word_rep_vector[i]=mBigramCounts[getWordIndex(word)][i];
		}
		double maxcosine = 0.0;
		int index = 0;
		int [] other_rep_vector = new int[mVocabulary.length];
		double temp_cos =0.0;
		for (int j=0;j<mVocabulary.length;j++) {
			if (j==getWordIndex(word)) {
				continue;
			}
			for (int i = 0;i<mVocabulary.length;i++) {
				other_rep_vector[i]=mBigramCounts[j][i];
			}
			temp_cos = calcCosineSim(word_rep_vector, other_rep_vector);
			if (temp_cos>maxcosine) {
				maxcosine=temp_cos;
				index = j;
			}
		}
		return mVocabulary[index];
	}

	/*
	 * @pre: word is a String
	 * @pre: the method initModel was called (the language model is initialized)
	 * @post: $ret = the number of word's occurrences in the text.
	 */
	public int getWordCount(String word){ //  Q - 11
		if (!isinarray(word,mVocabulary, mVocabulary.length)) {
			return 0;
		}
		int sum1 = 0;
		int sum2 = 0;
		for (int i =0;i<mVocabulary.length;i++) {
			sum1+=mBigramCounts[getWordIndex(word)][i];
			sum2+=mBigramCounts[i][getWordIndex(word)];
		}
		int appearences_num = Math.max(sum1, sum2);
		return appearences_num;
	}
	
	public static boolean islegalword(String s) {
		for (int i = 0; i<s.length();i++) {
			char current = s.charAt(i);
			int ascii_value = current;
			if ((ascii_value>=65&&ascii_value<=90)||(ascii_value>=97&&ascii_value<=122)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean islegalint(String s) {
		for (int i = 0; i<s.length();i++) {
			char current = s.charAt(i);
			int ascii_value = current;
			if ((ascii_value>=48&&ascii_value<=57)) {
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
	
	public static boolean isinarray(String word,String[] args,int index) {
		if (index==-1) {
			index = args.length;
		}
		for (int i = 0; i<index;i++) {
			if (args[i].equals(word)) {
				return true;
			}
	}
		return false;
	}
	
	public static int findindex(String word, String[] args) {
		for (int i = 0;i<args.length;i++) {
			if (args[i].equals(word)) {
				return i;
			}
		}
		return -1;
	}
	

}
