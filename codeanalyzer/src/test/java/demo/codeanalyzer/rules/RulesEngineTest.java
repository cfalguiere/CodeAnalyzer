package demo.codeanalyzer.rules;

import static org.junit.Assert.*;

import org.junit.Test;

import demo.codeanalyzer.common.AppContext;

public class RulesEngineTest {

	@Test
	public void canAddARule() {
		RulesEngine re = new RulesEngine(null);
		re.addRule(new SampleOKRule());
		assertEquals(1, re.getRulesCount());
	}

	@Test
	public void canFireRules() {
		AppContext ctx = new AppContext();
		
		RulesEngine re = new RulesEngine(ctx);
		re.addRule(new SampleOKRule());
		re.fireRules("class1");
		
		assertEquals(0, ctx.getViolationCollector().getTotalViolationsCount());
	}

	@Test
	public void canFireRulesAndDetectViolations() {
		AppContext ctx = new AppContext();
		
		RulesEngine re = new RulesEngine(ctx);
		re.addRule(new SampleKORule());
		re.fireRules("class1");
		
		assertEquals(1, ctx.getViolationCollector().getTotalViolationsCount());
	}

}
