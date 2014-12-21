package demo.codeanalyzer.violations;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

public class ViolationCollector  {

	private SortedMap<MethodKey, List<ViolationInfo>> violationsMap;
	
	public ViolationCollector() {
		violationsMap = new TreeMap<MethodKey, List<ViolationInfo>> ();
	}
	
	public void insert(ViolationInfo violationInfo) {
		MethodKey key = violationInfo.getMethodKey();
		List<ViolationInfo> methodViolations =  violationsMap.get(key);
		if (methodViolations == null) {
			methodViolations = new ArrayList<ViolationInfo>();
			violationsMap.put(key, methodViolations);
		}
		methodViolations.add(violationInfo);
	}

	/** insert only if violation is not null */
	public void collect(ViolationInfo violationInfo) {
		if (violationInfo != null) insert(violationInfo);
	}

	public ListIterator<ViolationInfo> getViolations(MethodKey methodKey) {
		return violationsMap.get(methodKey).listIterator();
	}

	public long getViolationsCount(MethodKey methodKey) {
		List<ViolationInfo> methodViolations = violationsMap.get(methodKey);
		return (methodViolations == null ? 0 : methodViolations.size());
	}

	public int getTotalViolationsCount() {
		int total = 0;
		for (List<ViolationInfo> methodViolations : violationsMap.values()) {
			total += methodViolations.size();
		}
		return total;
	}

	public void reportOntoConsole(PrintStream out) {
		PrintWriter writer = new PrintWriter(new PrintStream(out));
		if (getTotalViolationsCount() < 1) {
			writer.println("No Violation detected");			
		} else {
			for (MethodKey key : violationsMap.keySet()) {
				writer.format("Violation on %s%n", key);			
			}
			writer.format("%d Violation(s) found%n", getTotalViolationsCount());			
		}
		writer.flush();
        writer.close();		
	}
}
