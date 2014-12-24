package demo.codeanalyzer.violations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MethodKeyTest {

	@Test
	public void compareWhenEquals() {
		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "Test1", "process");
		MethodKey methodKey2 = new MethodKey("com.demo.Test1", "Test1","process");
		assertEquals(0, methodKey1.compareTo(methodKey2));
	}

	@Test
	public void compareWhenAscendingMethod() {
		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "Test1","init");
		MethodKey methodKey2 = new MethodKey("com.demo.Test1", "Test1", "process");
		assertTrue(methodKey1.compareTo(methodKey2) < 0);
	}

	@Test
	public void compareWhenAscendingClass() {
		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "Test1","process");
		MethodKey methodKey2 = new MethodKey("com.demo.Test1", "Test2","process");
		assertTrue(methodKey1.compareTo(methodKey2) < 0);
	}

	@Test
	public void compareWhenAscendingFile() {
		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "Test1","process");
		MethodKey methodKey2 = new MethodKey("com.demo.Test2", "Test1","process");
		assertTrue(methodKey1.compareTo(methodKey2) < 0);
	}

	@Test
	public void displayKeyAsClassColonMethod() {
		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "Test1", "process");
		assertEquals("com.demo.Test1:Test1:process", methodKey1.toString());
	}

	@Test
	public void compareWhenUndefinedMethod1() {
		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "Test1", null);
		MethodKey methodKey2 = new MethodKey("com.demo.Test1", "Test1", "process");
		assertTrue(methodKey1.compareTo(methodKey2) < 0);
	}

	@Test
	public void compareWhenUndefinedMethod2() {
		MethodKey methodKey1 = new MethodKey("com.demo.Test1", "Test1", "init");
		MethodKey methodKey2 = new MethodKey("com.demo.Test1", "Test1", null);
		assertTrue(methodKey1.compareTo(methodKey2) > 0);
	}


}
