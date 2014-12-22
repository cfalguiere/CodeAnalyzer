package demo.codeanalyzer.common;

import demo.codeanalyzer.processor.CodeAnalyzer;
import demo.codeanalyzer.violations.ViolationCollector;

/** This class acts as a simple CDI */
public class AppContext {
	private ViolationCollector vc;
	private CodeAnalyzer ca;
	private NestedContext nc;

	public AppContext() {
		vc = new ViolationCollector();
		nc = new NestedContext();
	}

	public ViolationCollector getViolationCollector() {
		return vc;
	}

	public NestedContext getNestedContext() {
		return nc;
	}
	
	public CodeAnalyzer createCodeAnalyzer() {
		ca = new CodeAnalyzer(this);
		return ca;
	}

	public CodeAnalyzer getCodeAnalyzer() {
		return ca;
	}
}
