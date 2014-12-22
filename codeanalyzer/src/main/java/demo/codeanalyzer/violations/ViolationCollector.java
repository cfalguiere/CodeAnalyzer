package demo.codeanalyzer.violations;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

public class ViolationCollector {

	private SortedMap<MethodKey, List<ViolationInfo>> violationsMap;

	public ViolationCollector() {
		violationsMap = new TreeMap<MethodKey, List<ViolationInfo>>();
	}

	public void insert(ViolationInfo violationInfo) {
		MethodKey key = violationInfo.getMethodKey();
		List<ViolationInfo> methodViolations = violationsMap.get(key);
		if (methodViolations == null) {
			methodViolations = new ArrayList<ViolationInfo>();
			violationsMap.put(key, methodViolations);
		}
		methodViolations.add(violationInfo);
	}

	/** insert only if violation is not null */
	public void collect(ViolationInfo violationInfo) {
		if (violationInfo != null)
			insert(violationInfo);
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

	public void reportAsCSV(String aCsvFilename) {
		if (getTotalViolationsCount() < 1) {
			System.out.println("No Violation to write out");
			return;
		}
		
		Path path = Paths.get(aCsvFilename);
		try (BufferedWriter writer = Files.newBufferedWriter(path,
				StandardCharsets.UTF_8)) {
			outputCSVLines(writer);
			System.out.format("Violation written in file %s%n", aCsvFilename);
		} catch (IOException e) {
			System.err.format("Could not write csv file %s - Reason: %s %n", aCsvFilename, e.getMessage());
		} 
	}
	
	void outputCSVLines(BufferedWriter writer) throws IOException {
		for (List<ViolationInfo> violations : violationsMap.values()) {
			for (ViolationInfo violation : violations) {
				writer.write(violation.getMethodKey().getClassName());
				writer.write(violation.getMethodKey().getMethodName());
				writer.write(violation.getViolation());
				writer.newLine();
			}
		}
		writer.flush();
		writer.close();
	}
	
}
