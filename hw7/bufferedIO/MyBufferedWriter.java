package il.ac.tau.cs.software1.bufferedIO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class MyBufferedWriter implements IBufferedWriter{
	
	private FileWriter file;
	private int buffsize;
	private char[] buffer;

	public MyBufferedWriter(FileWriter fWriter, int bufferSize){
		this.file = fWriter;
		this.buffsize = bufferSize;
		this.buffer = new char[bufferSize];
	}

	
	@Override
	public void write(String str) throws IOException {
		char[] split_str = str.toCharArray();
		int first_index = checkindex(this.buffer);  //for previous line remains case
		int begin = 0;
		while (split_str.length-begin>this.buffsize) {
			System.arraycopy(split_str, begin, this.buffer, first_index, Math.min(this.buffsize, this.buffsize-first_index));
			this.file.write(this.buffer,0,this.buffer.length);
			begin+=Math.min(this.buffsize, this.buffsize-first_index); //might be remains of previous line
			first_index = 0;
		}
		
		
		for (int i =0;i<this.buffer.length;i++) {
			this.buffer[i]=0;
		}
		for (int i=begin;i<split_str.length;i++) {//for making sure items left are less than buffer size
			this.buffer[i-begin]=split_str[i];
		}
			
		
		

	}
	
	@Override
	public void close() throws IOException {
		this.file.write(this.buffer,0,this.buffsize);
		this.file.close();
	}
	
	public static int checkindex(char[] args) {
		for (int i=0;i<args.length;i++) {
			if (args[i]==0) {
				return i;
			}
		}
		return -1;
	}

}