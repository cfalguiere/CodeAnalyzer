package demo.codeanalyzer.common;

import demo.codeanalyzer.processor.CodeAnalyzer;
import demo.codeanalyzer.violations.ViolationCollector;

public class AppContext {
	private ViolationCollector vc;
	private CodeAnalyzer ca;

	public AppContext() {
		vc = new ViolationCollector();
	}

	public ViolationCollector getViolationCollector() {
		return vc;
	}

	public CodeAnalyzer createCodeAnalyzer() {
		ca = new CodeAnalyzer(this);
		return ca;
	}

	public CodeAnalyzer getCodeAnalyzer() {
		return ca;
	}
}
