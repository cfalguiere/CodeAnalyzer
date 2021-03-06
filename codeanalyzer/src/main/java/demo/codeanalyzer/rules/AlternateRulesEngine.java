package demo.codeanalyzer.rules;

import java.util.HashSet;
import java.util.Set;

import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.ExpressionStatementTree;
import com.sun.source.tree.VariableTree;

import demo.codeanalyzer.common.AppContext;

/**
 * This class responsibility is to list the rules, apply the rules to the
 * submitted classes, and collect violations
 */

public class AlternateRulesEngine {

	private AppContext context;
	private Set<Rule> ruleSet;

	public AlternateRulesEngine(AppContext ctx) {
		context = ctx;
		ruleSet = new HashSet<Rule>();
	}

	/**
	 * Method to get the details of rules used for verification and to invoke
	 * the appropriate rule class to execute these rules.
	 * 
	 * @param javaClass
	 *            The element to process
	 */
	public void fireRules(VariableTree variableTree) {
		for (Rule rule : getRules()) {
			context.getViolationCollector().collect(rule.apply(variableTree, context.getNestedContext()));
		}
	}

	public void fireRules(ExpressionStatementTree expressionStatementTree) {
		for (Rule rule : getRules()) {
			context.getViolationCollector().collect(rule.apply(expressionStatementTree, context.getNestedContext()));
		}
	}

	public void fireRules(AssignmentTree assignmentTree) {
		for (Rule rule : getRules()) {
			context.getViolationCollector().collect(rule.apply(assignmentTree, context.getNestedContext()));
		}
	}

	private Set<Rule> getRules() {
		return ruleSet;
	}

	public void addRule(Rule rule) {
		ruleSet.add(rule);
	}

	public Object getRulesCount() {
		return ruleSet.size();
	}

}
