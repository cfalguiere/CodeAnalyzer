package demo.codeanalyzer.rules;

import com.sun.source.tree.VariableTree;

import demo.codeanalyzer.common.NestedContext;
import demo.codeanalyzer.violations.MethodKey;
import demo.codeanalyzer.violations.ViolationInfo;

public class TypeShouldNotContainsRule extends AbstractRule {

	private String typePart;
	
	public TypeShouldNotContainsRule(String aTypePart) {
		super("type shumd not contain " + aTypePart);
		typePart = aTypePart;
	}

	
	@Override
	public ViolationInfo apply(VariableTree variableTree, NestedContext aNestedContext) {
		ViolationInfo violation = null;
        if (variableTree.getType().toString().contains(typePart)) {
            String filename = aNestedContext.getFilename();
            String classname = aNestedContext.getClassname();
            String method = aNestedContext.getMethodname();
            MethodKey key = new MethodKey(filename, classname, method);
            System.out.println("VARIABLE " + variableTree.getName() + " " + variableTree.getType());
            violation = new ViolationInfo(key, "use " + variableTree.getType(), variableTree.getName().toString() );
        }
		return violation;
	}


	@Override
	protected ViolationInfo apply(String element) {
		// TODO Auto-generated method stub
		return null;
	}

}
