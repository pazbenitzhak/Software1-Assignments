package il.ac.tau.cs.software1.bufferedIO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedIOTester {
	public static final String RESOURCES_FOLDER = "resources/hw7/out/";

	public static void main(String[] args) throws IOException{
		String outputFileName = RESOURCES_FOLDER + "rocky1_out2.txt";
		String outString1 = "Now somewhere in the Black mining Hills of Dakota\nThere lived a young boy named Rocky Raccoon,\n"; 
		String outString2 = "And one day his woman";
		String outString3 = " ran off with another guy,\nHit young Rocky in the eye.";
		MyFileWriter fWriter = new MyFileWriter(new File(outputFileName));
		FileWriter fWriter2 = new MyFileWriter(new File(outputFileName));
		int j=10;
		IBufferedWriter bW = new MyBufferedWriter(fWriter, j);
		bW.write(outString1);
		bW.write(outString2);
		bW.write(outString3);
		bW.close();
		int total_length = outString1.length()+outString2.length()+outString3.length();
		if (fWriter.getWritesCount()!= (total_length/j)) {
			System.out.println("error with Writecount");
			System.out.println((total_length/j)+1);
			System.out.println(fWriter.getWritesCount());
		}
		
		/***
		 * The output file this tester creates should be identical to rocky1_correct.txt
		 * 
		 */
	}
}
