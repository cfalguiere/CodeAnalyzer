package demo.codeanalyzer.processor;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.processing.AbstractProcessor;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import demo.codeanalyzer.common.AppContext;

/**
 * The controller class to initiate verification of java files using custom
 * annotation processor. The files to be verified can be supplied to this class
 * as comma-separated argument.
 * 
 * @author Seema Richard (Seema.Richard@ust-global.com)
 * @author Deepa Sobhana (Deepa.Sobhana@ust-global.com)
 * 
 */
public class CodeAnalyzerController {

	private AppContext context;
	
	public CodeAnalyzerController(AppContext aContext) {
		context = aContext;
	}
	
	/**
	 * Invokes the annotation processor passing the list of file names
	 * 
	 * @param fileNames
	 *            Names of files to be verified
	 */
	public void invokeProcessor(List<String> fileNames) {
		// Gets the Java programming language compiler
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
		// Get a new instance of the standard file manager implementation
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		
		// Get the valid source files as a list
		if (fileNames.size() > 0) {
			// Get the list of java file objects
			Iterable<? extends JavaFileObject> compilationUnits1 = fileManager
					.getJavaFileObjectsFromStrings(fileNames);
			
			DiagnosticCollector<JavaFileObject> diagnostics =
			        new DiagnosticCollector<JavaFileObject>();
			
			// Create the compilation task
			CompilationTask task = compiler.getTask(null, fileManager, diagnostics,
					null, null, compilationUnits1);
			
			// Get the list of annotation processors
			LinkedList<AbstractProcessor> processors = new LinkedList<AbstractProcessor>();
			processors.add(new CodeAnalyzerProcessor(context));
			task.setProcessors(processors);
			
			// Perform the compilation task.
			task.call();
			
			try {
				fileManager.close();
			} catch (IOException e) {
				System.out.println(e.getLocalizedMessage());
			}
		} else {
			System.err.println("No file to process. ");
		}
	}

}
