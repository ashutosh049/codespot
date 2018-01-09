package com.codespot.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class MyFileUtil {
	public static void main(String[] args) throws IOException {
		
		File directory = new File("F:/Multimedia/watch series/subtitles - Copy");

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    boolean bool = false;
	    for (File file : fList) {
	        if (!file.isDirectory()) {
	        	String n1=file.getAbsolutePath();
	        	//[hdpopcorns]-Brief-Interviews-with-Hideous-Men-English-Subtitle-Free-Download-2
	        	String n2 = n1.replaceAll("[hdpopcorns]", "");
	        	File file2 = new File(n2);
	            if(file.renameTo(file2)){
	            	System.out.println(file2);
	            	//System.out.println(file+" renamed to : "+file2);
	            }
	            
	        	/*** replace the contents within file.****/
	        	/*	        	
				Path path = Paths.get(file.getAbsolutePath());
	        	Charset charset = StandardCharsets.UTF_8;
	        	String content = new String(Files.readAllBytes(path), charset);
	        	content = content.replaceAll("\\$\\{contextPath\\}\\/resources\\/css", "\\$\\{contextPath\\}\\/resources\\/css\\/");
	        	Files.write(path, content.getBytes(charset));
	        	*/
	        }
	    }
		
	}
	
	
}
