import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PairTest {

    Pair<String, String> pair_A1;
    Pair<String, String> pair_A2;
    Pair<String, String> pair_B1;
    Pair<String, String> pair_B2;

    @Before
    public void setUp() {
        pair_A1 = new Pair<>("A", "1");
        pair_A2 = new Pair<>("A", "2");
        pair_B1 = new Pair<>("B", "1");
        pair_B2 = new Pair<>("B", "2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNull(){
        new Pair<String, String>(null, null);
        fail();
    }

    @Test
    public void testA1ToString(){
        assertEquals("A | 1", pair_A1.toString());
    }

    @Test
    public void testA1EqualsDifferentObject(){
        assertNotEquals(pair_A1, pair_A1.toString());
    }

    @Test
    public void testA1EqualsSelf(){
        assertEquals(pair_A1, pair_A1);
    }

    @Test
    public void testA1EqualsDifferentA1(){
        assertEquals(pair_A1, new Pair<>("A", "1"));
    }

    @Test
    public void testA1EqualsDifferent1A(){
        assertEquals(pair_A1, new Pair<>("1", "A"));
    }

    @Test
    public void testA1Equals1B(){
        assertNotEquals(pair_A1, new Pair<>("1", "B"));
    }

    @Test
    public void testA1EqualsB1(){
        assertNotEquals(pair_A1, pair_B1);
    }

    @Test
    public void testA1Equals2A(){
        assertNotEquals(pair_A1, new Pair<>("2", "A"));
    }

    @Test
    public void testA1EqualsA2(){
        assertNotEquals(pair_A1, pair_A2);
    }

    @Test
    public void testA1EqualsB2(){
        assertNotEquals(pair_A1, pair_B2);
    }
}