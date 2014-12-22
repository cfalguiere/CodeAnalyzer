package demo.codeanalyzer;

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
import demo.codeanalyzer.processor.CodeAnalyzerProcessor;

/** This class responsibility is to create the processor, init the compiler task and run it */
public class CodeAnalyzerApp {

	private AppContext context;
	
	public CodeAnalyzerApp(AppContext aContext) {
		context = aContext;
	}
	
	/**
	 * Invokes the annotation processor passing the list of file names
	 * 
	 * @param fileNames
	 *            Names of files to be verified
	 */
	public void invokeProcessor(List<String> fileNames) {
		
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		
		if (fileNames.size() > 0) {
			
			Iterable<? extends JavaFileObject> compilationUnits1 = fileManager.getJavaFileObjectsFromStrings(fileNames);
			
			DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
			
			CompilationTask task = compiler.getTask(null, fileManager, diagnostics,
					null, null, compilationUnits1);
			
			LinkedList<AbstractProcessor> processors = new LinkedList<AbstractProcessor>();
			processors.add(new CodeAnalyzerProcessor(context));
			task.setProcessors(processors);
			
			task.call();

	        context.getViolationCollector().reportOntoConsole(System.out);
	        context.getViolationCollector().reportAsCSVWithAppName("export.csv");

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
