package tests;

import org.junit.Assert;
import org.junit.Test;
import parser.util.Value;

public class ValueTest {

    @Test
    public void testInt() {
        Value v1 = Value.of(42);
        Assert.assertTrue(v1.isInt());
        Assert.assertEquals(42, v1.asInt());
        Assert.assertEquals(Integer.valueOf(42), v1.value());
        Value v2 = Value.of(77);
        Value sum = v1.plus(v2);
        Assert.assertEquals(42 + 77, sum.asInt());
        Value isLess = v1.lt(v2);
        Assert.assertTrue(isLess.asBoolean());
        Value isEqual = v2.eq(v1);
        Assert.assertFalse(isEqual.asBoolean());
    }

    @Test
    public void testString() {
        Value v1 = Value.of("hello");
        Assert.assertTrue(v1.isString());
        Assert.assertEquals("hello", v1.asString());
        Assert.assertEquals("hello", v1.value());
        Value v2 = Value.of("world");
        Value sum = v1.plus(v2);
        Assert.assertEquals("helloworld", sum.asString());
        Value isLess = v1.lt(v2);
        Assert.assertTrue(isLess.asBoolean());
        Value isEqual = v2.eq(v1);
        Assert.assertFalse(isEqual.asBoolean());
    }

    @Test
    public void testBoolean() {
        Value v1 = Value.of(true);
        Assert.assertTrue(v1.isBoolean());
        Assert.assertEquals(true, v1.asBoolean());
        Assert.assertEquals(Boolean.TRUE, v1.value());
        Value v2 = Value.of(false);
        Value isLess = v2.lt(v1);
        Assert.assertTrue(isLess.asBoolean());
        Value isEqual = v2.eq(v1);
        Assert.assertFalse(isEqual.asBoolean());
    }
}
