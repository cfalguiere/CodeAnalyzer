package demo.codeanalyzer.rules;

import demo.codeanalyzer.violations.ViolationInfo;

public interface Rule {

	public ViolationInfo execute(String element);
	public String getName();
}
