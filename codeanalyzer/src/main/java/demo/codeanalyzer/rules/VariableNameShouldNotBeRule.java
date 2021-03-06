package demo.codeanalyzer.rules;

import com.sun.source.tree.VariableTree;

import demo.codeanalyzer.common.NestedContext;
import demo.codeanalyzer.violations.MethodKey;
import demo.codeanalyzer.violations.ViolationInfo;

public class VariableNameShouldNotBeRule extends AbstractRule {

	private String typePart;
	
	public VariableNameShouldNotBeRule(String aTypePart) {
		super("type should not be " + aTypePart);
		typePart = aTypePart;
	}

	
	@Override
	public ViolationInfo apply(VariableTree variableTree, NestedContext aNestedContext) {
		ViolationInfo violation = null;
        if (variableTree.getName().toString().equals(typePart)) {
            String filename = aNestedContext.getFilename();
            String classname = aNestedContext.getClassname();
            String method = aNestedContext.getMethodname();
            MethodKey key = new MethodKey(filename, classname, method);
            System.out.println("VARIABLE " + variableTree.toString());
            violation = new ViolationInfo(key, "declare " + variableTree.getName(), variableTree.getName().toString() );
        }
		return violation;
	}


	@Override
	protected ViolationInfo apply(String element) {
		// TODO Auto-generated method stub
		return null;
	}

}
