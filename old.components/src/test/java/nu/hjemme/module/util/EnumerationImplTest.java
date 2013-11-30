package nu.hjemme.module.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * A test of the implemented enumeration.
 * @author Tor Egil Jacobsen
 */
public class EnumerationImplTest {

    private EnumerationImpl <String> testIt = null;
    private Set <String> testSet = null;

    /**
     * Test to enumerate over the test cache in the set up...
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullToEnumerate() {
        new EnumerationImpl <String>(null);
    }

    /**
     * Test to enumerate over the test cache in the set up...
     */
    @Test
    public void testEnumeration() {
        testSet = new HashSet <String>(3);
        testSet.add("value1");
        testSet.add("value2");
        testSet.add("value3");
        testIt = new EnumerationImpl <String>(testSet);
        int elems = 0;

        for (; testIt.hasMoreElements(); elems++) {
            String elem = testIt.nextElement();
            assertTrue("Value '" + elem + "' is in the set!", testSet.contains(elem));
        }

        assertEquals("There should be 3 elements in the set!", 3, elems);
    }
}
