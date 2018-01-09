package com.codespot.util;
import java.io.File;
import java.io.IOException;

public class MyFileUtil2 {
	public static void main(String[] args) throws IOException {
		
		File directory = new File("F:/subtitles");

	    // get all the files from a directory
	    File[] fList = directory.listFiles();
	    boolean bool = false;
	    for (File file : fList) {
	        if (!file.isDirectory()) {
	        	String n1=file.getAbsolutePath();
	        	// Lovelace English Subtitle Free Download 7
	        	String n2 = n1.replaceAll("  Subtitle Free Download", "");
	        	File file2 = new File(n2);
	            if(file.renameTo(file2)){
	            	System.out.println(file2);
	            	//System.out.println(file+" renamed to : "+file2);
	            }else{
					System.out.println("can not rename to : "+file2);
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
