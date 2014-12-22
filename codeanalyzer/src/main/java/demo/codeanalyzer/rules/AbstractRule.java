package demo.codeanalyzer.rules;

import com.sun.source.tree.VariableTree;

import demo.codeanalyzer.common.NestedContext;
import demo.codeanalyzer.violations.MethodKey;
import demo.codeanalyzer.violations.ViolationInfo;

public abstract class AbstractRule implements Rule {
	private String name;

	public AbstractRule(String aName) {
		name = aName;
	}
	
	/* concrete class must implement these */
	protected abstract ViolationInfo apply(String element);

	public ViolationInfo apply(VariableTree variableTree, NestedContext aNestedContext) {return null;}
	
	@Override
	public final ViolationInfo execute(String element) {
		return apply(element);
	}

	public final String getName() {
		return name;
	}

	protected ViolationInfo createViolationInfo(String aViolation, String anAssessment) {
		MethodKey key = new MethodKey("file1", "class1", "method1");
		return new ViolationInfo(key, aViolation, anAssessment);
	}

}
