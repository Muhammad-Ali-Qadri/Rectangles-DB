import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * This test class is responsible for testing each scenario of each method in
 * the SkipList class
 *
 * @author Muhammad Ali Qadri 
 */
public class SkipListTest {

    //Represents an empty skip list with size and depth 0
    private SkipList<String, String> list_empty;
    //Represents a skip list with 3 elements with keys A, B, C respectively
    private SkipList<String, String> list_A1_B2_C3;
    //Represents a skip list with 3 elements with keys D, F, G respectively
    private SkipList<String, String> list_D1_F3_G4;

    /**
     * Set up variables for testing.
     * Executed before every test case.
     */
    @Before
    public void setUp() {
        list_empty = new SkipList<>();

        list_A1_B2_C3 = new SkipList<>();
        list_A1_B2_C3.insert(new KVPair<>("A", "1"));
        list_A1_B2_C3.insert(new KVPair<>("B", "2"));
        list_A1_B2_C3.insert(new KVPair<>("C", "3"));

        list_D1_F3_G4 = new SkipList<>();
        list_D1_F3_G4.insert(new KVPair<>("D", "1"));
        list_D1_F3_G4.insert(new KVPair<>("F", "3"));
        list_D1_F3_G4.insert(new KVPair<>("G", "4"));
    }

    /**
     * Tests isEmpty function on an empty list
     */
    @Test
    public void testEmptyIsEmpty() {
        assertTrue(list_empty.isEmpty());
    }

    /**
     * Tests isEmpty function on a filled list
     */
    @Test
    public void testA1B2C3IsEmpty() {
        assertFalse(list_A1_B2_C3.isEmpty());
    }

    /**
     * Tests toString method on an empty list
     */
    @Test
    public void testEmptyToString() {
        assertEquals("[]:0", list_empty.toString());
    }


    /**
     * Tests toString function on a filled list
     */
    @Test
    public void testA1B2C3ToString() {
        assertEquals("[(A, 1), (B, 2), (C, 3)]:3",
                list_A1_B2_C3.toString());
    }

    /**
     * Tests isEmpty function on a filled list
     */
    @Test
    public void testD1F3G4ToString() {
        assertEquals("[(D, 1), (F, 3), (G, 4)]:3",
                list_D1_F3_G4.toString());
    }

    /**
     * Tests insertion of element into an empty list
     */
    @Test
    public void testInsertA1InEmpty() {
        list_empty.insert(new KVPair<>("A", "1"));
        assertEquals(1, list_empty.size());
        assertEquals(new KVPair<>("A", "1"), list_empty.first());
    }


    /**
     * Tests insertion of element into a list that already has elements
     */
    @Test
    public void testEmptyInsertA1B2() {
        list_empty.insert(new KVPair<>("A", "1"));
        assertEquals(1, list_empty.size());
        assertEquals(new KVPair<>("A", "1"), list_empty.first());

        list_empty.insert(new KVPair<>("B", "2"));
        assertEquals(2, list_empty.size());
        assertEquals("[(A, 1), (B, 2)]:2", list_empty.toString());
    }

    /**
     * Tests insertion of two elements into an empty list with same key
     */
    @Test
    public void testEmptyInsertA1A1() {
        list_empty.insert(new KVPair<>("A", "1"));
        assertEquals(1, list_empty.size());
        assertEquals(new KVPair<>("A", "1"), list_empty.first());

        list_empty.insert(new KVPair<>("A", "1"));
        assertEquals(2, list_empty.size());
        assertEquals("[(A, 1), (A, 1)]:2", list_empty.toString());
    }


    /**
     * Tests insertion of two elements into an empty list
     */
    @Test
    public void testEmptyInsertA1A2() {
        list_empty.insert(new KVPair<>("A", "1"));
        assertEquals(1, list_empty.size());
        assertEquals(new KVPair<>("A", "1"), list_empty.first());

        list_empty.insert(new KVPair<>("A", "2"));
        assertEquals(2, list_empty.size());
        assertEquals("[(A, 2), (A, 1)]:2", list_empty.toString());
    }


    /**
     * Tests insertion of element into a list that already has elements
     */
    @Test
    public void testA1B2C3InsertD1() {
        list_A1_B2_C3.insert(new KVPair<>("D", "4"));
        assertEquals(4, list_A1_B2_C3.size());
        assertEquals("[(A, 1), (B, 2), (C, 3), (D, 4)]:4",
                list_A1_B2_C3.toString());
    }

    /**
     * Tests insertion of element into a list that already has elements.
     * new element will be inserted in between existing elements
     */
    @Test
    public void testD1F3G4InsertE2() {
        list_D1_F3_G4.insert(new KVPair<>("E", "2"));
        assertEquals(4, list_D1_F3_G4.size());
        assertEquals("[(D, 1), (E, 2), (F, 3), (G, 4)]:4",
                list_D1_F3_G4.toString());
    }


    /**
     * Tests if list allows insertion of null element
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyInsertNull() {
        list_empty.insert(null);
        fail();
    }


    /**
     * Tests if list removes an element from an empty list
     */
    @Test
    public void testEmptyRemoveA() {
        assertNull(list_empty.remove("A"));
    }


    /**
     * Tests if list removes the first element from a filled list
     */
    @Test
    public void testA1B2C3RemoveA() {
        assertEquals(new KVPair<>("A", "1"),
                list_A1_B2_C3.remove("A"));
        assertEquals("[(B, 2), (C, 3)]:2", list_A1_B2_C3.toString());
        assertEquals(new KVPair<>("B", "2"), list_A1_B2_C3.first());
    }

    /**
     * Tests if list removes any element from a filled list
     */
    @Test
    public void testA1B2C3RemoveB() {
        assertEquals(new KVPair<>("B", "2"),
                list_A1_B2_C3.remove("B"));
        assertEquals("[(A, 1), (C, 3)]:2", list_A1_B2_C3.toString());
        assertEquals(new KVPair<>("A", "1"), list_A1_B2_C3.first());
    }

    /**
     * Tests if list removes the last element from a filled list
     */
    @Test
    public void testA1B2C3RemoveC() {
        assertEquals(new KVPair<>("C", "3"),
                list_A1_B2_C3.remove("C"));
        assertEquals("[(A, 1), (B, 2)]:2", list_A1_B2_C3.toString());
        assertEquals(new KVPair<>("A", "1"), list_A1_B2_C3.first());
    }


    /**
     * Tests if list removes an element not existing from a filled list
     */
    @Test
    public void testA1B2C3RemoveD() {
        assertNull(list_A1_B2_C3.remove("D"));
    }

    /**
     * Tests if list removes multiple elements until list is empty
     */
    @Test
    public void testD1F3G4RemoveDFG() {
        assertEquals(new KVPair<>("D", "1"),
                list_D1_F3_G4.remove("D"));
        assertEquals("[(F, 3), (G, 4)]:2", list_D1_F3_G4.toString());
        assertEquals(new KVPair<>("F", "3"), list_D1_F3_G4.first());

        assertEquals(new KVPair<>("F", "3"),
                list_D1_F3_G4.remove("F"));
        assertEquals("[(G, 4)]:1", list_D1_F3_G4.toString());
        assertEquals(new KVPair<>("G", "4"), list_D1_F3_G4.first());

        assertEquals(new KVPair<>("G", "4"),
                list_D1_F3_G4.remove("G"));
        assertEquals("[]:0", list_D1_F3_G4.toString());
        assertNull(list_D1_F3_G4.first());
    }


    /**
     * Tests if list throws exception if null is passed to remove
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullInEmpty() {
        list_empty.remove(null);
        fail();
    }


    /**
     * Tests if list removes an element from an empty list by value
     */
    @Test
    public void testEmptyRemoveByValue1() {
        assertNull(list_empty.removeByValue("1"));
    }


    /**
     * Tests if list removes first element from a filled list by value
     */
    @Test
    public void testA1B2C3RemoveByValue1() {
        assertEquals(new KVPair<>("A", "1"),
                list_A1_B2_C3.removeByValue("1"));
        assertEquals("[(B, 2), (C, 3)]:2", list_A1_B2_C3.toString());
        assertEquals(new KVPair<>("B", "2"), list_A1_B2_C3.first());
    }


    /**
     * Tests if list removes any element from a filled list by value
     */
    @Test
    public void testA1B2C3RemoveByValue2() {
        assertEquals(new KVPair<>("B", "2"),
                list_A1_B2_C3.removeByValue("2"));
        assertEquals("[(A, 1), (C, 3)]:2", list_A1_B2_C3.toString());
        assertEquals(new KVPair<>("A", "1"), list_A1_B2_C3.first());
    }


    /**
     * Tests if list removes the last element from a filled list by value
     */
    @Test
    public void testA1B2C3RemoveByValue3() {
        assertEquals(new KVPair<>("C", "3"),
                list_A1_B2_C3.removeByValue("3"));
        assertEquals("[(A, 1), (B, 2)]:2", list_A1_B2_C3.toString());
        assertEquals(new KVPair<>("A", "1"), list_A1_B2_C3.first());
    }


    /**
     * Tests if list removes a non-existing element from a filled list by
     * value
     */
    @Test
    public void testA1B2C3RemoveByValue4() {
        assertNull(list_A1_B2_C3.removeByValue("4"));
    }


    /**
     * Tests if list removes multiple elements by value until list is empty
     */
    @Test
    public void testD1F3G4RemoveByValue134() {
        assertEquals(new KVPair<>("D", "1"),
                list_D1_F3_G4.removeByValue("1"));
        assertEquals("[(F, 3), (G, 4)]:2", list_D1_F3_G4.toString());
        assertEquals(new KVPair<>("F", "3"), list_D1_F3_G4.first());

        assertEquals(new KVPair<>("F", "3"),
                list_D1_F3_G4.removeByValue("3"));
        assertEquals("[(G, 4)]:1", list_D1_F3_G4.toString());
        assertEquals(new KVPair<>("G", "4"), list_D1_F3_G4.first());

        assertEquals(new KVPair<>("G", "4"),
                list_D1_F3_G4.removeByValue("4"));
        assertEquals("[]:0", list_D1_F3_G4.toString());
        assertNull(list_D1_F3_G4.first());
    }


    /**
     * Tests if remove by value function throws exception when null is
     * passed to it
     */
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveByValueNullInEmpty() {
        list_empty.removeByValue(null);
        fail();
    }

    /**
     * Searches if an empty list contains an element
     */
    @Test
    public void testEmptySearchA() {
        assertNull(list_empty.search("A"));
    }


    /**
     * Searches if a filled list contains a specific element at the start
     */
    @Test
    public void testA1B2C3SearchA() {
        List<KVPair<String, String>> searchList = new ArrayList<>();
        searchList.add(new KVPair<>("A", "1"));

        assertEquals(searchList, list_A1_B2_C3.search("A"));
        assertEquals("[(A, 1), (B, 2), (C, 3)]:3",
                list_A1_B2_C3.toString());
    }


    /**
     * Searches if a filled list contains a specific element at any position
     */
    @Test
    public void testA1B2C3SearchB() {
        List<KVPair<String, String>> searchList = new ArrayList<>();
        searchList.add(new KVPair<>("B", "2"));

        assertEquals(searchList, list_A1_B2_C3.search("B"));
        assertEquals("[(A, 1), (B, 2), (C, 3)]:3",
                list_A1_B2_C3.toString());
    }


    /**
     * Searches if a filled list contains an element at the end
     */
    @Test
    public void testD1F3G4SearchG() {
        List<KVPair<String, String>> searchList = new ArrayList<>();
        searchList.add(new KVPair<>("G", "4"));

        assertEquals(searchList, list_D1_F3_G4.search("G"));
        assertEquals("[(D, 1), (F, 3), (G, 4)]:3",
                list_D1_F3_G4.toString());
    }

    /**
     * Searches if a filled list contains duplicate keyed elements
     */
    @Test
    public void testD1F3G4G5G6SearchGDuplicateKey() {
        list_D1_F3_G4.insert(new KVPair<>("G", "5"));
        list_D1_F3_G4.insert(new KVPair<>("G", "6"));

        List<KVPair<String, String>> searchList = new ArrayList<>();
        searchList.add(new KVPair<>("G", "6"));
        searchList.add(new KVPair<>("G", "5"));
        searchList.add(new KVPair<>("G", "4"));

        assertEquals(searchList, list_D1_F3_G4.search("G"));
        assertEquals("[(D, 1), (F, 3), (G, 6), (G, 5), (G, 4)]:5",
                list_D1_F3_G4.toString());
    }


    /**
     * Searches if a filled list contains non-existent element
     */
    @Test
    public void testD1F3G4SearchB() {
        assertNull(list_D1_F3_G4.search("B"));
        assertEquals("[(D, 1), (F, 3), (G, 4)]:3",
                list_D1_F3_G4.toString());
    }


    /**
     * Tests if search function throws an exception for null input
     */
    @Test(expected = IllegalArgumentException.class)
    public void testD1F3G4SearchNull() {
        list_D1_F3_G4.search(null);
        fail();
    }


    /**
     * Tests the size of the empty list
     */
    @Test
    public void testEmptySize() {
        assertEquals(0, list_empty.size());
    }


    /**
     * Tests the size of the filled list
     */
    @Test
    public void testA1B2C3Size() {
        assertEquals(3,
                list_A1_B2_C3.size());
    }


    /**
     * Tests the first element in an empty list
     */
    @Test
    public void testEmptyFirst() {
        assertNull(list_empty.first());
    }


    /**
     * Tests the first element in a filled list
     */
    @Test
    public void testA1B2C3First() {
        assertEquals(new KVPair<>("A", "1"), list_A1_B2_C3.first());
    }


    /**
     * Tests the empty list is iterable
     */
    @Test
    public void testEmptyIterable() {
        StringBuilder sb = new StringBuilder();

        for (KVPair<String, String> pair : list_empty)
            sb.append(pair.toString());

        assertEquals("", sb.toString());
    }


    /**
     * Tests the filled list is iterable
     */
    @Test
    public void testD1F3G4Iterable() {
        StringBuilder sb = new StringBuilder();

        for (KVPair<String, String> pair : list_D1_F3_G4)
            sb.append("(").append(pair.toString()).append(")");

        assertEquals("(D, 1)(F, 3)(G, 4)", sb.toString());
    }

    /**
     * Check if filled list outputs a correct dump
     */
    @Test
    public void testA1B2C3Dump() {
        String dump = list_A1_B2_C3.dump();

        assertTrue(dump.contains("(A, 1)"));
        assertTrue(dump.contains("(B, 2)"));
        assertTrue(dump.contains("(C, 3)"));
        assertTrue(dump.contains("SkipList size is: 3"));
    }


    /**
     * Check if an empty list outputs a correct dump
     */
    @Test
    public void testEmptyDump() {
        String dump = list_empty.dump();

        assertTrue(dump.contains("Node has depth 0, Value (null)"));
        assertTrue(dump.contains("SkipList size is: 0"));
    }
}