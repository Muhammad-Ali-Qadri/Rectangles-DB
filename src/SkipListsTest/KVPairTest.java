package SkipListsTest;

import SkipLists.KVPair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KVPairTest {

    KVPair<String, String> pair_A_1; //A pair with key "A" and value "1"
    KVPair<String, String> pair_B_1; //A pair with key "B" and value "1"
    KVPair<String, String> pair_A_2; //A pair with key "A" and value "2"


    /**
     * Set up the pair objects with values to test before
     * every test case execution.
     * */
    @Before
    public void setUp() {
        pair_A_1 = new KVPair<>("A", "1");
        pair_B_1 = new KVPair<>("B", "1");
        pair_A_2 = new KVPair<>("A", "2");
    }


    /**
     * <p>Test if KVPair constructor accepts null value for key.</p>
     *
     * <p><b>Pass</b> - If <code>IllegalArgumentException</code> exception is thrown</p>
     * */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckNullKeyConstructor(){
        new KVPair<String, String>(null, "null");
        fail();
    }


    /**
     * <p>Test if KVPair constructor accepts null value for value.</p>
     *
     * <p><b>Pass</b> - If <code>IllegalArgumentException</code> exception is thrown</p>
     * */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckNullValueConstructor(){
        new KVPair<String, String>("null", null);
        fail();
    }


    /**
     * <p>Test if pair_A_1 returns correct key</p>
     *
     * <p><b>Pass</b> - If "A" is returned</p>
     * */
    @Test
    public void testA1GetKey(){
        assertEquals("A", pair_A_1.getKey());
    }


    /**
     * <p>Test if pair_A_1 returns correct value</p>
     *
     * <p><b>Pass</b> - If "1" is returned</p>
     * */
    @Test
    public void testA1GetValue(){
        assertEquals("1", pair_A_1.getValue());
    }


    /**
     * <p>Test if pair_A_1 is converted to correct string format</p>
     *
     * <p><b>Pass</b> - If "(A, 1)" is returned</p>
     * */
    @Test
    public void testA1ToString(){
        assertEquals("(A, 1)", pair_A_1.toString());
    }


    /**
     * <p>Test if pair_A_1 is equal to itself. Provokes the <code>.equals()</code> method</p>
     *
     * <p><b>Pass</b> - true is returned</p>
     * */
    @Test
    public void testA1EqualsSelf(){
        assertEquals(pair_A_1, pair_A_1);
    }


    /**
     * <p>Test if pair_A_1 is equal to a different KVPair object
     * with same key and value. Provokes the <code>.equals()</code> method</p>
     *
     * <p><b>Pass</b> - true is returned</p>
     * */
    @Test
    public void testA1EqualsDifferentA1(){
        assertEquals(pair_A_1, new KVPair<String, String>("A", "1"));
    }


    /**
     * <p>Test if pair_A_1 is equal to a different object
     * entirely. Provokes the <code>.equals()</code> method</p>
     *
     * <p><b>Pass</b> - false is returned</p>
     * */
    @Test
    public void testA1EqualsDifferentObject(){
        assertNotEquals(pair_A_1, "A1");
    }


    /**
     * <p>Test if pair_A_1 is equal to pair_B_1.
     * Provokes the <code>.equals()</code> method</p>
     *
     * <p><b>Pass</b> - false is returned</p>
     * */
    @Test
    public void testA1EqualsB1(){
        assertNotEquals(pair_A_1, pair_B_1);
    }


    /**
     * <p>Test if pair_A_1 is equal to pair_A_2.
     * Provokes the <code>.equals()</code> method</p>
     *
     * <p><b>Pass</b> - false is returned</p>
     * */
    @Test
    public void testA1EqualsA2(){
        assertNotEquals(pair_A_1, pair_A_2);
    }
}