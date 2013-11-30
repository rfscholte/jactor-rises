package nu.hjemme.module.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * A test of the stack util.
 * @author Tor Egil Jacobsen
 */
public class StackUtilTest {

    @Test
    public void testIsInStack() {
        assertTrue("org.junit should be in the stack!", StackUtil.isInStack("org.junit"));
        assertFalse("The given name should not be in the stack!", StackUtil.isInStack("not.in.stack"));
    }

    @Test
    public void testStackInfo() {
        String partInfo = "nu.hjemme.module.util.StackUtilTest.testStackInfo(StackUtilTest.java";
        assertTrue("Expected part of stack info: " + partInfo, StackUtil.getStackInfo().contains(partInfo));
    }

    @Test
    public void testNullOnValidElement() {
        StackUtil.getValidStackTraceElement((String[]) null);
    }
}
