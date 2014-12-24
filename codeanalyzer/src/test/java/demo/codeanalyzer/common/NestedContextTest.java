package demo.codeanalyzer.common;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NestedContextTest {

	@Test
	public void canTrackFilename() {
		NestedContext nc = new NestedContext();
		nc.trackFilename("File1");
		assertEquals("File1", nc.getFilename());
	}
	
	@Test
	public void canTrackClass() {
		NestedContext nc = new NestedContext();
		nc.trackClassname("Class1");
		assertEquals("Class1", nc.getClassname());
	}
	
	@Test
	public void canTrackMethod() {
		NestedContext nc = new NestedContext();
		nc.trackMethodname("Method1");
		assertEquals("Method1", nc.getMethodname());
	}
	
	

}
