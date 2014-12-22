package demo.codeanalyzer.common;

import java.util.Stack;

public class NestedContext {

	private String filename;
	private String classname;
	private String methodname;
	
	public void trackFilename(String aFilename) {
		filename = aFilename;
		classname = null;
		methodname = null;
	}

	public String getFilename() {
		return filename;
	}

	public void trackClassname(String aClassname) {
		classname = aClassname;
		methodname = null;
	}

	public String getClassname() {
		return classname;
	}

	public void trackMethodname(String aMethodname) {
		methodname = aMethodname;
	}

	public String getMethodname() {
		return methodname;
	}
	
}
