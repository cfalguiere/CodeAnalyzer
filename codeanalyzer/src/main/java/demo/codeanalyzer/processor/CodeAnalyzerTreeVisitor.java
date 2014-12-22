package demo.codeanalyzer.processor;

import javax.lang.model.element.Element;

import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.ExpressionStatementTree;
import com.sun.source.tree.ImportTree;
import com.sun.source.tree.LabeledStatementTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.VariableTree;
import com.sun.source.util.TreePath;
import com.sun.source.util.TreePathScanner;
import com.sun.source.util.Trees;

import demo.codeanalyzer.common.AppContext;
import demo.codeanalyzer.helper.ClassInfoDataSetter;
import demo.codeanalyzer.helper.FieldInfoDataSetter;
import demo.codeanalyzer.helper.MethodInfoDataSetter;
import demo.codeanalyzer.model.JavaClassInfo;
import demo.codeanalyzer.violations.MethodKey;
import demo.codeanalyzer.violations.ViolationInfo;

/**
 * Visitor class which visits different nodes of the input source file, 
 * extracts the required atribute of the visiting class, its mehods, 
 * fields, annotations etc and set it in the java class model.
 * 
 * @author Seema Richard (Seema.Richard@ust-global.com)
 * @author Deepa Sobhana (Deepa.Sobhana@ust-global.com)
 */
public class CodeAnalyzerTreeVisitor extends TreePathScanner<Object, Trees> {

	private AppContext context;
	
	public CodeAnalyzerTreeVisitor(AppContext aContext) {
		context = aContext;
	}
	
    //Model class stores the details of the visiting class
    JavaClassInfo clazzInfo = new JavaClassInfo();

    /**
     * Visits the class
     * @param classTree
     * @param trees
     * @return
     */
    @Override
    public Object visitClass(ClassTree classTree, Trees trees) {

    	try {
            TreePath path = getCurrentPath();
            //populate required class information to model
            ClassInfoDataSetter.populateClassInfo(clazzInfo, classTree, 
                        path, trees);
            context.getNestedContext().trackClassname(classTree.getSimpleName().toString());
            System.out.println("CLASS " + classTree.getSimpleName() + " " + classTree.getKind());
            
    	} catch (Exception e) {
            System.err.println("Could not visit class");    		
    	}
       return super.visitClass(classTree, trees);
    }

    /**
     * Visits all methods of the input java source file
     * @param methodTree
     * @param trees
     * @return
     */
    @Override
    public Object visitMethod(MethodTree methodTree, Trees trees) {
       	try {
       	         TreePath path = getCurrentPath();

	        //populate required method information to model
	        MethodInfoDataSetter.populateMethodInfo(clazzInfo, methodTree, 
	                                                path, trees);
	        
            context.getNestedContext().trackMethodname(methodTree.getName().toString());
	        System.out.println("METHOD " + methodTree.getName() );
	    } catch (Exception e) {
            System.err.println("Could not visit method");    		
    	}
        return super.visitMethod(methodTree, trees);
    }

    /**
     * Visits all variables of the java source file
     * @param variableTree
     * @param trees
     * @return
     */
    @Override
    public Object visitVariable(VariableTree variableTree, Trees trees) {
        TreePath path = getCurrentPath();
        Element e = trees.getElement(path);

        //populate required method information to model
        FieldInfoDataSetter.populateFieldInfo(clazzInfo, variableTree, e, 
                                              path, trees);

        context.getAlternateRulesEngine().fireRules(variableTree);
        
        return super.visitVariable(variableTree, trees);
    }

    

	@Override
	public Object visitImport(ImportTree importTree, Trees trees) {        
        //System.out.println("IMPORT " + importTree);
 		return super.visitImport(importTree, trees);
	}

	
	@Override
	public Object visitAssignment(AssignmentTree assignmentTree, Trees trees) {
	    //System.out.println("ASSIGNMENT " + assignmentTree);
		return super.visitAssignment(assignmentTree, trees);
	}

	@Override
	public Object visitCompilationUnit(CompilationUnitTree compilationUnitTree, Trees trees) {
	    System.out.println("UNIT " + compilationUnitTree);
		return super.visitCompilationUnit(compilationUnitTree, trees);
	}

	@Override
	public Object visitExpressionStatement(ExpressionStatementTree expressionStatementTree,
			Trees trees) {
	    //System.out.println("EXPRESSION STATEMENT " + expressionStatementTree);
		return super.visitExpressionStatement(expressionStatementTree, trees);
	}

	@Override
	public Object visitLabeledStatement(LabeledStatementTree labeledStatementTree, Trees trees) {
	    //System.out.println("LABELED STATEMENT " + labeledStatementTree);
		return super.visitLabeledStatement(labeledStatementTree, trees);
	}

	@Override
	public Object visitOther(Tree tree, Trees trees) {
	    //System.out.println("OTHER " + tree);
		return super.visitOther(tree, trees);
	}

	/**
     * Returns the Java class model which holds the details of java source
     * @return clazzInfo Java class model 
     */
    public JavaClassInfo getClassInfo() {
        return clazzInfo;
    }
}

