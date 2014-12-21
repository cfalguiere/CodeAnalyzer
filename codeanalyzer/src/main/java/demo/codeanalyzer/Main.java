package demo.codeanalyzer;


import java.util.List;

import demo.codeanalyzer.common.AppContext;
import demo.codeanalyzer.util.FileListLoader;


public class Main {

	public static void main(String[] args) {

		String basedir = "/Users/claude.falguiere/Documents/2014-12/CN-ClubMed-SOA/Export-Clubmed";
		String listFilename = "../imports.txt";
		int max = 4;
		
		try {
			AppContext context = new AppContext();
			
			FileListLoader loader = new FileListLoader();
			List<String> filenames = loader.loadFilenames(basedir, listFilename, max);
			System.out.format("Selected files %s%n", filenames);
			
			context.createCodeAnalyzer();
			CodeAnalyzerApp cac = new CodeAnalyzerApp(context);
			cac.invokeProcessor(filenames);

		} catch (Exception e) {
			System.err.format("Could not handle %s. Reason: %s. Exiting%n", listFilename, e.getLocalizedMessage());
			e.printStackTrace();
			System.exit(255);
		}
	}
	
}
