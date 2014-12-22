package demo.codeanalyzer.rules;

import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.VariableTree;

import demo.codeanalyzer.common.NestedContext;
import demo.codeanalyzer.violations.MethodKey;
import demo.codeanalyzer.violations.ViolationInfo;

public class ShouldNotAssignVariableRule extends AbstractRule {

	private String typePart;
	
	public ShouldNotAssignVariableRule(String aTypePart) {
		super("should not assign variable " + aTypePart);
		typePart = aTypePart;
	}

	
	@Override
	public ViolationInfo apply(AssignmentTree assignmentTree, NestedContext aNestedContext) {
		ViolationInfo violation = null;
        if (assignmentTree.getVariable().toString().equals(typePart)) {
            String filename = aNestedContext.getFilename();
            String classname = aNestedContext.getClassname();
            String method = aNestedContext.getMethodname();
            MethodKey key = new MethodKey(filename, classname, method);
            System.out.println("ASSIGNMENT " + assignmentTree.toString());
            violation = new ViolationInfo(key, "set " + typePart, assignmentTree.toString());
        }
		return violation;
	}


	@Override
	protected ViolationInfo apply(String element) {
		// TODO Auto-generated method stub
		return null;
	}

}
