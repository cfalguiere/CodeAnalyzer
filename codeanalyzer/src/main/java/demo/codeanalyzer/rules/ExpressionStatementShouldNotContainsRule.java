package demo.codeanalyzer.rules;

import com.sun.source.tree.ExpressionStatementTree;
import com.sun.source.tree.VariableTree;

import demo.codeanalyzer.common.NestedContext;
import demo.codeanalyzer.violations.MethodKey;
import demo.codeanalyzer.violations.ViolationInfo;

public class ExpressionStatementShouldNotContainsRule extends AbstractRule {

	private String typePart;
	
	public ExpressionStatementShouldNotContainsRule(String aTypePart) {
		super("type should not contain " + aTypePart);
		typePart = aTypePart;
	}

	
	@Override
	public ViolationInfo apply(ExpressionStatementTree expressionStatementTree, NestedContext aNestedContext) {
		ViolationInfo violation = null;
        if (expressionStatementTree.toString().contains(typePart)) {
            String filename = aNestedContext.getFilename();
            String classname = aNestedContext.getClassname();
            String method = aNestedContext.getMethodname();
            MethodKey key = new MethodKey(filename, classname, method);
            System.out.println("EXPRESSION STATEMENT " + expressionStatementTree.toString());
            violation = new ViolationInfo(key, "expression contains " + typePart, expressionStatementTree.toString() );
        }
		return violation;
	}


	@Override
	protected ViolationInfo apply(String element) {
		// TODO Auto-generated method stub
		return null;
	}

}
