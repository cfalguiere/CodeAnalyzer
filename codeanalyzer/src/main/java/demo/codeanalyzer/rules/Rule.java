package demo.codeanalyzer.rules;

import com.sun.source.tree.VariableTree;

import demo.codeanalyzer.common.NestedContext;
import demo.codeanalyzer.violations.ViolationInfo;

public interface Rule {

	public ViolationInfo execute(String element);
	public ViolationInfo apply(VariableTree variableTree, NestedContext aNestedContext);
	public String getName();
}
