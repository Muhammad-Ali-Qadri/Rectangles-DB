package SkipListsTest;

import SkipLists.KVPair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class KVPairTest {

    KVPair<String, String> pair_A_1;
    KVPair<String, String> pair_B_1;
    KVPair<String, String> pair_A_2;

    @Before
    public void setUp() {
        pair_A_1 = new KVPair<>("A", "1");
        pair_B_1 = new KVPair<>("B", "1");
        pair_A_2 = new KVPair<>("A", "2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNullKeyConstructor(){
        new KVPair<String, String>(null, "null");
        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckNullValueConstructor(){
        new KVPair<String, String>("null", null);
        fail();
    }

    @Test
    public void testA1GetKey(){
        assertEquals("A", pair_A_1.getKey());
    }

    @Test
    public void testA1GetValue(){
        assertEquals("1", pair_A_1.getValue());
    }

    @Test
    public void testA1ToString(){
        assertEquals("(A, 1)", pair_A_1.toString());
    }

    @Test
    public void testA1EqualsSelf(){
        assertEquals(pair_A_1, pair_A_1);
    }

    @Test
    public void testA1EqualsDifferentA1(){
        assertEquals(pair_A_1, new KVPair<String, String>("A", "1"));
    }

    @Test
    @SuppressWarnings("Possible redundant assertion")
    public void testA1EqualsDifferentObject(){
        assertNotEquals(pair_A_1, "A1");
    }

    @Test
    public void testA1EqualsB1(){
        assertNotEquals(pair_A_1, pair_B_1);
    }

    @Test
    public void testA1EqualsA2(){
        assertNotEquals(pair_A_1, pair_A_2);
    }
}