package demo.codeanalyzer.processor;

import java.util.ArrayList;
import java.util.Collection;

import demo.codeanalyzer.model.ClassFile;
import demo.codeanalyzer.model.ClassModelMap;
import demo.codeanalyzer.model.ErrorDescription;

/**
 * RulesEngine encapsulates the details of Rules used for verification. This
 * class load the rules defined in <i>rules.xml</i> file and then invokes the
 * appropriate rule class for executing these rules.
 * 
 * @author Seema Richard (Seema.Richard@ust-global.com)
 * @author Deepa Sobhana (Deepa.Sobhana@ust-global.com)
 */
public class RulesEngine {

    /**
     * List of problems found in the source file
     */
    private Collection<ErrorDescription> problemsFound = new ArrayList<ErrorDescription>();

    /**
     * Method to get the details of rules used for verification and to invoke
     * the appropriate rule class to execute these rules.
     * 
     * @param javaClass
     *            The element to process
     */
    public void fireRules(String className) {

        ClassFile clazzInfo = ClassModelMap.getInstance().getClassInfo(className);
        /*
        for (JavaCodeRule rule : getRules()) {
                // apply class-level rules
                Collection<ErrorDescription> problems = rule.execute(clazzInfo);
                if (problems != null) {
                    problemsFound.addAll(problems);
                }
            }*/
    }

	public Collection<ErrorDescription> getProblemsFound() {
		return problemsFound;
	}

	public void setProblemsFound(Collection<ErrorDescription> problemsFound) {
		this.problemsFound = problemsFound;
	}

 
}
