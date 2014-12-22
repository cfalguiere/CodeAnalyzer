package demo.codeanalyzer.rules;

import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.ExpressionStatementTree;
import com.sun.source.tree.VariableTree;

import demo.codeanalyzer.common.NestedContext;
import demo.codeanalyzer.violations.ViolationInfo;

public interface Rule {

	public ViolationInfo execute(String element);
	public ViolationInfo apply(VariableTree variableTree, NestedContext aNestedContext);
	public ViolationInfo apply(ExpressionStatementTree expressionStatementTree, NestedContext aNestedContext);
	public ViolationInfo apply(AssignmentTree assignmentTree, NestedContext aNestedContext);
	public String getName();
}
