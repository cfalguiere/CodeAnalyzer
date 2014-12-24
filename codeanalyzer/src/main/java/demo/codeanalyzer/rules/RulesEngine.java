package demo.codeanalyzer.rules;

import java.util.HashSet;
import java.util.Set;

import demo.codeanalyzer.common.AppContext;
import demo.codeanalyzer.model.ClassFile;
import demo.codeanalyzer.model.ClassModelMap;

/**
 * This class responsibility is to list the rules, apply the rules to the submitted classes, and collect violations 
 */
public class RulesEngine {
	
	private AppContext context;
	private Set<Rule> ruleSet;
	
	public RulesEngine(AppContext ctx) {
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
    public void fireRules(String className) {

        ClassFile clazzInfo = ClassModelMap.getInstance().getClassInfo(className);
        
        for (Rule rule : getRules()) {
                // apply class-level rules
        	// TODO get the classfile from the class name
        	context.getViolationCollector().collect(rule.execute(className));
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
