import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test class is responsible for testing each scenario of each method in
 * the Pair class
 *
 * @author Muhammad Ali Qadri
 */
public class PairTest {

    Pair<String, String> pair_A1; //Represents a pair of object "A" and "1"
    Pair<String, String> pair_A2; //Represents a pair of object "A" and "2"
    Pair<String, String> pair_B1; //Represents a pair of object "B" and "1"
    Pair<String, String> pair_B2; //Represents a pair of object "B" and "2"

    /**
     * Set up the pair objects with values to test before
     * every test case execution.
     */
    @Before
    public void setUp() {
        pair_A1 = new Pair<>("A", "1");
        pair_A2 = new Pair<>("A", "2");
        pair_B1 = new Pair<>("B", "1");
        pair_B2 = new Pair<>("B", "2");
    }

    /**
     * Test if class constructor throws exception when null inputs are passed
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateNull(){
        new Pair<String, String>(null, null);
        fail();
    }


    /**
     * Test if pair returns correct ToString output
     */
    @Test
    public void testA1ToString(){
        assertEquals("A | 1", pair_A1.toString());
    }

    /**
     * Test if Equals method works with a different object
     */
    @Test
    public void testA1EqualsDifferentObject(){
        assertNotEquals(pair_A1, pair_A1.toString());
    }


    /**
     * Test if Equals method works with the object itself
     */
    @Test
    public void testA1EqualsSelf(){
        assertEquals(pair_A1, pair_A1);
    }


    /**
     * Test if Equals method works with a different object with same values
     */
    @Test
    public void testA1EqualsDifferentA1(){
        assertEquals(pair_A1, new Pair<>("A", "1"));
    }


    /**
     * Test if Equals method works with a different object with same values
     * and the order flipped
     */
    @Test
    public void testA1EqualsDifferent1A(){
        assertEquals(pair_A1, new Pair<>("1", "A"));
    }


    /**
     * Test if Equals method works with a different object with different values
     */
    @Test
    public void testA1Equals1B(){
        assertNotEquals(pair_A1, new Pair<>("1", "B"));
    }


    /**
     * Test if Equals method works with a different object with different values
     */
    @Test
    public void testA1EqualsB1(){
        assertNotEquals(pair_A1, pair_B1);
    }


    /**
     * Test if Equals method works with a different object with different values
     */
    @Test
    public void testA1Equals2A(){
        assertNotEquals(pair_A1, new Pair<>("2", "A"));
    }


    /**
     * Test if Equals method works with a different object with different values
     */
    @Test
    public void testA1EqualsA2(){
        assertNotEquals(pair_A1, pair_A2);
    }


    /**
     * Test if Equals method works with a different object with different values
     */
    @Test
    public void testA1EqualsB2(){
        assertNotEquals(pair_A1, pair_B2);
    }
}