package tests;

import org.junit.Assert;
import org.junit.Test;

import parser.util.Ops;
import parser.util.Value;

public class OpsTest {

	@Test
	public void testBinary() {
		Value v1 = Value.of(20);
		Value v2 = Value.of(22);
		Value sum = Ops.plus.apply(v1, v2);
		Assert.assertEquals(42, sum.asInt());
	}
	
	@Test
	public void testUnary() {
		Value v1 = Value.of(42);
		Value v2 = Ops.negative.apply(v1);
		Assert.assertEquals(-42, v2.asInt());
	}
}
