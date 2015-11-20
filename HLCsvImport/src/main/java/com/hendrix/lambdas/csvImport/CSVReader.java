/*package com.amido.hendrix.importer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

public class CSVReader extends BufferedReader {

	private String separator;
	public final static String UTF8EncodingFormat = "UTF-8";
	
	public CSVReader(InputStream inputStream, String separator) throws UnsupportedEncodingException {
		super(new InputStreamReader(inputStream, UTF8EncodingFormat));
		this.separator = separator;
	}
	
	public String[] readLineAndSplit() throws IOException{
		String line = readLine();
		
		return line != null ? line.split(separator) : null;
	}
	
	public List<String> readColumns() throws IOException{
		return Arrays.asList(readLineAndSplit());
	}

}*/
