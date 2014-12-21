package demo.codeanalyzer.violations;

/** 
 * This class responsibility is to identify a méthod
 */
public class MethodKey implements Comparable {
	
	private String className;
	private String methodName;
	
	public MethodKey(String className, String methodName) {
		super();
		this.className = className;
		this.methodName = methodName;
	}
	
	public String getClassName() {
		return className;
	}
	public String getMethodName() {
		return methodName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((className == null) ? 0 : className.hashCode());
		result = prime * result
				+ ((methodName == null) ? 0 : methodName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MethodKey other = (MethodKey) obj;
		if (className == null) {
			if (other.className != null)
				return false;
		} else if (!className.equals(other.className))
			return false;
		if (methodName == null) {
			if (other.methodName != null)
				return false;
		} else if (!methodName.equals(other.methodName))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object obj) {
		MethodKey other = (MethodKey) obj;
		int diff = className.compareTo(other.className);
		if (diff == 0) diff = methodName.compareTo(other.methodName);
		return diff;
	}

	@Override
	public String toString() {
		return className + ":" + methodName;
	}

	
}
