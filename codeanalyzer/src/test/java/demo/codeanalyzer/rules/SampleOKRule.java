package demo.codeanalyzer.rules;

import demo.codeanalyzer.violations.ViolationInfo;

public class SampleOKRule extends AbstractRule {
	public SampleOKRule() {
		super("sample OK rule");
	}

	@Override
	protected ViolationInfo apply(String element) {
		return null;
	}

}
