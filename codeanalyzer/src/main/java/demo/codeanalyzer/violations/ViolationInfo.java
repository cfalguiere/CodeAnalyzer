package demo.codeanalyzer.violations;

import java.util.List;

import demo.codeanalyzer.model.ClassFile;
import demo.codeanalyzer.model.MethodInfo;

/** 
 * This class responsibility is to hold violation informations
 */
public class ViolationInfo {

	private MethodKey methodKey;
	private String violation;
	private String statement;
	
	public ViolationInfo(MethodKey aMethodKey, String aViolation,
			String aStatement) {
		methodKey = aMethodKey;
		violation = aViolation;
		statement = aStatement;
	}

	public MethodKey getMethodKey() {
		return methodKey;
	}

	
	public String getViolation() {
		return violation;
	}

	@Override
	public String toString() {
		return "ViolationInfo [methodKey=" + methodKey + ", violation="
				+ violation + ", statement=" + statement + "]";
	}
	
}
