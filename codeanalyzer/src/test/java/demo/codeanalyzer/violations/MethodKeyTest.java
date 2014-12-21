package demo.codeanalyzer.violations;

import static org.junit.Assert.*;

import org.junit.Test;

import demo.codeanalyzer.violations.MethodKey;

public class MethodKeyTest {

	@Test
	public void compareWhenEquals() {
		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "process");
		MethodKey methodKey2 = new MethodKey("com.demo.Test1", "process");
		assertEquals(0, methodKey1.compareTo(methodKey2));
	}

	@Test
	public void compareWhenAscendingMethod() {
		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "init");
		MethodKey methodKey2 = new MethodKey("com.demo.Test1", "process");
		assertTrue(methodKey1.compareTo(methodKey2) < 0);
	}

	@Test
	public void compareWhenAscendingClass() {
		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "process");
		MethodKey methodKey2 = new MethodKey("com.demo.Test2", "process");
		assertTrue(methodKey1.compareTo(methodKey2) < 0);
	}

	@Test
	public void displayKeyAsClassColonMethod() {
		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "process");
		assertEquals("com.demo.Test1:process", methodKey1.toString());
	}

}