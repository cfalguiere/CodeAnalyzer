package demo.codeanalyzer.violations;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.ListIterator;

import org.junit.Test;

import demo.codeanalyzer.model.ErrorDescription;
import demo.codeanalyzer.violations.MethodKey;
import demo.codeanalyzer.violations.ViolationCollector;
import demo.codeanalyzer.violations.ViolationInfo;

public class ViolationCollectorTest {

	@Test
	public void canInsertAViolation() {
		ViolationCollector vc = new ViolationCollector();
		MethodKey methodKey = new MethodKey("com.demo.Test1", "Test1", "init");
		String violation = "violation 1";
		String statement = "CallableStatement statement";
		ViolationInfo violationInfo = new ViolationInfo(methodKey, violation, statement);
		vc.insert(violationInfo);
		
		ListIterator<ViolationInfo> violations = vc.getViolations(methodKey);
		assertEquals(1, vc.getViolationsCount(methodKey));
		assertNotNull(violations);
		assertTrue(violation, violations.hasNext());
		assertEquals(violation, violations.next().getViolation());
	}
	
	@Test
	public void canAddAnotherViolationToTheMethod() {
		ViolationCollector vc = new ViolationCollector();
		MethodKey methodKey = new MethodKey("com.demo.Test1", "Test1",  "init");

		String violation1 = "violation 1";
		String statement1 = "CallableStatement statement";
		ViolationInfo violationInfo1 = new ViolationInfo(methodKey, violation1, statement1);
		vc.insert(violationInfo1);

		String violation2 = "violation 2";
		String statement2 = "Query query";
		ViolationInfo violationInfo2 = new ViolationInfo(methodKey, violation2, statement2);
		vc.insert(violationInfo2);
		
		ListIterator<ViolationInfo> violations = vc.getViolations(methodKey);
		assertEquals(2, vc.getViolationsCount(methodKey));		
		assertNotNull(violations);
		
		assertTrue(violation1, violations.hasNext());
		assertEquals(violation1, violations.next().getViolation());
		
		assertTrue(violation2, violations.hasNext());
		assertEquals(violation2, violations.next().getViolation());
	}

	
	@Test
	public void canAddViolationsWhenTwoMethods() {
		ViolationCollector vc = new ViolationCollector();
		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "Test1", "init");

		String violation1 = "violation 1";
		String statement1 = "CallableStatement statement";
		ViolationInfo violationInfo1 = new ViolationInfo(methodKey1, violation1, statement1);
		vc.insert(violationInfo1);

		MethodKey methodKey2 = new MethodKey("com.demo.Test1", "Test1", "process");

		String violation2 = "violation 2";
		String statement2 = "CallableStatement statement";
		ViolationInfo violationInfo2 = new ViolationInfo(methodKey2, violation2, statement2);
		vc.insert(violationInfo2);
		
		ListIterator<ViolationInfo> violations1 = vc.getViolations(methodKey1);
		assertEquals(1, vc.getViolationsCount(methodKey1));		
		assertNotNull(violations1);
		
		assertTrue(violation1, violations1.hasNext());
		assertEquals(violation1, violations1.next().getViolation());

		ListIterator<ViolationInfo> violations2 = vc.getViolations(methodKey2);
		assertEquals(1, vc.getViolationsCount(methodKey2));		
		assertNotNull(violations2);

		assertTrue(violation2, violations2.hasNext());
		assertEquals(violation2, violations2.next().getViolation());
	}

	@Test
	public void totalNumberOfViolationIsTheSumOfMethodViolationsWhenTwoMethods() {
		ViolationCollector vc = new ViolationCollector();

		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "Test1", "init");
		String violation1 = "violation 1";
		String statement1 = "CallableStatement statement";
		ViolationInfo violationInfo1 = new ViolationInfo(methodKey1, violation1, statement1);
		vc.insert(violationInfo1);
		
		MethodKey methodKey2 = new MethodKey("com.demo.Test1", "Test1", "process");
		String violation2 = "violation 1";
		String statement2 = "CallableStatement statement";
		ViolationInfo violationInfo2 = new ViolationInfo(methodKey2, violation2, statement2);
		vc.insert(violationInfo2);
		
		assertEquals(2, vc.getTotalViolationsCount());
	}
	
	@Test
	public void canReportNoViolationDetectedWhen0Violation() {
		ViolationCollector vc = new ViolationCollector();

		ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
		PrintStream console = new PrintStream(outSpy);
		vc.reportOntoConsole(console);
		assertEquals("No Violation detected\n", outSpy.toString() );
	}
	
	@Test
	public void canReport1ViolationFoundWhen1Violation() {
		ViolationCollector vc = new ViolationCollector();

		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "Test1", "init");
		String violation1 = "violation 1";
		String statement1 = "CallableStatement statement";
		ViolationInfo violationInfo1 = new ViolationInfo(methodKey1, violation1, statement1);
		vc.insert(violationInfo1);

		ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
		PrintStream fakeConsole = new PrintStream(outSpy);
		vc.reportOntoConsole(fakeConsole);
		assertEquals("Violation on com.demo.Test1:Test1:init\n1 Violation(s) found\n", outSpy.toString() );

	}
	
	@Test
	public void canReportCSVWhen1Violation() throws IOException {
		ViolationCollector vc = new ViolationCollector();

		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "Test1", "init");
		String violation1 = "violation 1";
		String statement1 = "CallableStatement statement";
		ViolationInfo violationInfo1 = new ViolationInfo(methodKey1, violation1, statement1);
		vc.insert(violationInfo1);

		ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new PrintWriter(outSpy));
		vc.outputCSVLines(writer);
		assertEquals("com.demo.Test1;Test1;init;violation 1\n", outSpy.toString() );

	}
	
	@Test
	public void canReportCSVWhen1ViolationAndUndefinedMethod() throws IOException {
		ViolationCollector vc = new ViolationCollector();

		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "Test1", null);
		String violation1 = "violation 1";
		String statement1 = "CallableStatement statement";
		ViolationInfo violationInfo1 = new ViolationInfo(methodKey1, violation1, statement1);
		vc.insert(violationInfo1);

		ByteArrayOutputStream outSpy = new ByteArrayOutputStream();
		BufferedWriter writer = new BufferedWriter(new PrintWriter(outSpy));
		vc.outputCSVLines(writer);
		assertEquals("com.demo.Test1;Test1;-;violation 1\n", outSpy.toString() );

	}
}
