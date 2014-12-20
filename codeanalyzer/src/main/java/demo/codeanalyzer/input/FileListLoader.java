package demo.codeanalyzer.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class FileListLoader {

    public List<String> loadFilenames(String basedir, String listFilename, int max) throws Exception {
    	
    	List<String> filenames = new ArrayList<>();
    	int count = 0;
    	
        Charset charset = Charset.forName("US-ASCII");
        Path path = Paths.get(listFilename);
        try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
            String line = null;
            while ((line = reader.readLine()) != null && count<max)  {
            	if (! line.contains("/test/")) {
                	filenames.add(basedir + File.separator + line);
                	count++;            		
            	}
           }
            System.out.format("Found %d filenames%n", filenames.size());
       } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
            throw x;
        }
        
        return filenames;
    }
}
