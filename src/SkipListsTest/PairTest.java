package SkipListsTest;

import SkipLists.Pair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test class is responsible for testing each scenario of each method in
 * the SkipLists.Pair class
 *
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class PairTest {

    private Pair<String, String> pairA1; //Represents a pair of object "A" and
    // "1"
    private Pair<String, String> pairA2; //Represents a pair of object "A" and
    // "2"
    private Pair<String, String> pairB1; //Represents a pair of object "B" and
    // "1"
    private Pair<String, String> pairB2; //Represents a pair of object "B" and
    // "2"

    /**
     * Set up the pair objects with values to test before
     * every test case execution.
     */
    @Before
    public void setUp() {
        pairA1 = new Pair<>("A", "1");
        pairA2 = new Pair<>("A", "2");
        pairB1 = new Pair<>("B", "1");
        pairB2 = new Pair<>("B", "2");
    }

    /**
     * Test if class constructor throws exception when null inputs are passed
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNull() {
        new Pair<String, String>(null, null);
        fail();
    }


    /**
     * Test if pair returns correct ToString output
     */
    @Test
    public void testA1ToString() {
        assertEquals("A | 1", pairA1.toString());
    }

    /**
     * Test if Equals method works with a different object
     */
    @Test
    public void testA1EqualsDifferentObject() {
        assertNotEquals(pairA1, pairA1.toString());
    }


    /**
     * Test if Equals method works with the object itself
     */
    @Test
    public void testA1EqualsSelf() {
        assertEquals(pairA1, pairA1);
    }


    /**
     * Test if Equals method works with a different object with same values
     */
    @Test
    public void testA1EqualsDifferentA1() {
        assertEquals(pairA1, new Pair<>("A", "1"));
    }


    /**
     * Test if Equals method works with a different object with same values
     * and the order flipped
     */
    @Test
    public void testA1EqualsDifferent1A() {
        assertEquals(pairA1, new Pair<>("1", "A"));
    }


    /**
     * Test if Equals method works with a different object with different values
     */
    @Test
    public void testA1Equals1B() {
        assertNotEquals(pairA1, new Pair<>("1", "B"));
    }


    /**
     * Test if Equals method works with a different object with different values
     */
    @Test
    public void testA1EqualsB1() {
        assertNotEquals(pairA1, pairB1);
    }


    /**
     * Test if Equals method works with a different object with different values
     */
    @Test
    public void testA1Equals2A() {
        assertNotEquals(pairA1, new Pair<>("2", "A"));
    }


    /**
     * Test if Equals method works with a different object with different values
     */
    @Test
    public void testA1EqualsA2() {
        assertNotEquals(pairA1, pairA2);
    }


    /**
     * Test if Equals method works with a different object with different values
     */
    @Test
    public void testA1EqualsB2() {
        assertNotEquals(pairA1, pairB2);
    }
}