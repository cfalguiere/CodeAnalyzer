package demo.codeanalyzer.rules;

import demo.codeanalyzer.violations.MethodKey;
import demo.codeanalyzer.violations.ViolationInfo;

public abstract class AbstractRule implements Rule {
	private String name;

	public AbstractRule(String aName) {
		name = aName;
	}
	
	/* concrete class must implement these */
	protected abstract ViolationInfo apply(String element);

	@Override
	public final ViolationInfo execute(String element) {
		return apply(element);
	}

	public final String getName() {
		return name;
	}

	protected ViolationInfo createViolationInfo(String aViolation, String anAssessment) {
		MethodKey key = new MethodKey("class1", "method1");
		return new ViolationInfo(key, aViolation, anAssessment);
	}

}
