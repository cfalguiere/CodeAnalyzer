package demo.codeanalyzer;


import java.util.List;

import demo.codeanalyzer.input.FileListLoader;
import demo.codeanalyzer.processor.CodeAnalyzerController;


public class Main {

	public static void main(String[] args) {

		String basedir = "/Users/claude.falguiere/Documents/2014-12/CN-ClubMed-SOA/Export-Clubmed";
		String listFilename = "imports.txt";
		
		try {
			FileListLoader loader = new FileListLoader();
			int max = 4;
			List<String> filenames = loader.loadFilenames(basedir, listFilename, max);

			System.out.format("Selected files %s%n", filenames);
			
			CodeAnalyzerController cac = new CodeAnalyzerController();
			cac.invokeProcessor(filenames);

		} catch (Exception e) {
			System.err.format("Could not handle %s. Reason: %s. Exiting%n", listFilename, e.getLocalizedMessage());
			e.printStackTrace();
			System.exit(255);
		}
	}
	
}
