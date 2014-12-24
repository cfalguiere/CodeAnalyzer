package demo.codeanalyzer.processor;

import demo.codeanalyzer.common.AppContext;
import demo.codeanalyzer.rules.RulesEngine;

/**
 * This class starts the code analyzing process
 * @author Deepa Sobhana, Seema Richard
 */
public class CodeAnalyzer {

	private AppContext context;
 
    public CodeAnalyzer(AppContext aContext) {
    	context = aContext;
    }

    public void process(String className) {
        RulesEngine ruleEngine = new RulesEngine(context);
        ruleEngine.fireRules(className);
    }  

}
