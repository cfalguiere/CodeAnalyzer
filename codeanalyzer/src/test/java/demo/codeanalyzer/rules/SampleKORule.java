package demo.codeanalyzer.rules;

import demo.codeanalyzer.violations.ViolationInfo;

public class SampleKORule extends AbstractRule {
	public SampleKORule() {
		super("sample KO rule");
	}

	@Override
	protected ViolationInfo apply(String element) {
		return createViolationInfo("violation1", "assessment1");
	}

}
