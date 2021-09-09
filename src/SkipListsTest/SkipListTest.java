package SkipListsTest;

import SkipLists.KVPair;
import SkipLists.SkipList;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SkipListTest {

    SkipList<String, String> list_empty;
    SkipList<String, String> list_A1_B2_C3;
    SkipList<String, String> list_D1_F3_G4;

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

    @Test
    public void testEmptyIsEmpty(){
        assertTrue(list_empty.isEmpty());
    }

    @Test
    public void testA1B2C3IsEmpty(){
        assertFalse(list_A1_B2_C3.isEmpty());
    }

    @Test
    public void testEmptyToString(){
        assertEquals("[]:0", list_empty.toString());
    }

    @Test
    public void testA1B2C3ToString(){
        assertEquals("[(A, 1), (B, 2), (C, 3)]:3", list_A1_B2_C3.toString());
    }

    @Test
    public void testD1F3G4ToString(){
        assertEquals("[(D, 1), (F, 3), (G, 4)]:3", list_D1_F3_G4.toString());
    }

    @Test
    public void testInsertA1InEmpty() {
        list_empty.insert(new KVPair<>("A", "1"));
        assertEquals(1, list_empty.size());
        assertEquals(new KVPair<>("A", "1"), list_empty.first());
    }

    @Test
    public void testEmptyInsertA1B2() {
        list_empty.insert(new KVPair<>("A", "1"));
        assertEquals(1, list_empty.size());
        assertEquals(new KVPair<>("A", "1"), list_empty.first());

        list_empty.insert(new KVPair<>("B", "2"));
        assertEquals(2, list_empty.size());
        assertEquals("[(A, 1), (B, 2)]:2", list_empty.toString());
    }

    @Test
    public void testEmptyInsertA1A1() {
        list_empty.insert(new KVPair<>("A", "1"));
        assertEquals(1, list_empty.size());
        assertEquals(new KVPair<>("A", "1"), list_empty.first());

        list_empty.insert(new KVPair<>("A", "1"));
        assertEquals(2, list_empty.size());
        assertEquals("[(A, 1), (A, 1)]:2", list_empty.toString());
    }

    @Test
    public void testEmptyInsertA1A2() {
        list_empty.insert(new KVPair<>("A", "1"));
        assertEquals(1, list_empty.size());
        assertEquals(new KVPair<>("A", "1"), list_empty.first());

        list_empty.insert(new KVPair<>("A", "2"));
        assertEquals(2, list_empty.size());
        assertEquals("[(A, 2), (A, 1)]:2", list_empty.toString());
    }

    @Test
    public void testA1B2C3InsertD1() {
        list_A1_B2_C3.insert(new KVPair<>("D", "4"));
        assertEquals(4, list_A1_B2_C3.size());
        assertEquals("[(A, 1), (B, 2), (C, 3), (D, 4)]:4", list_A1_B2_C3.toString());
    }

    @Test
    public void testD1F3G4InsertE2() {
        list_D1_F3_G4.insert(new KVPair<>("E", "2"));
        assertEquals(4, list_D1_F3_G4.size());
        assertEquals("[(D, 1), (E, 2), (F, 3), (G, 4)]:4", list_D1_F3_G4.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyInsertNull() {
        list_empty.insert(null);
        fail();
    }

    @Test
    public void testEmptyRemoveA() {
        assertNull(list_empty.remove("A"));
    }

    @Test
    public void testA1B2C3RemoveA() {
        assertEquals(new KVPair<>("A", "1"), list_A1_B2_C3.remove("A"));
        assertEquals("[(B, 2), (C, 3)]:2", list_A1_B2_C3.toString());
        assertEquals(new KVPair<>("B", "2"), list_A1_B2_C3.first());
    }

    @Test
    public void testA1B2C3RemoveB() {
        assertEquals(new KVPair<>("B", "2"), list_A1_B2_C3.remove("B"));
        assertEquals("[(A, 1), (C, 3)]:2", list_A1_B2_C3.toString());
        assertEquals(new KVPair<>("A", "1"), list_A1_B2_C3.first());
    }

    @Test
    public void testA1B2C3RemoveC() {
        assertEquals(new KVPair<>("C", "3"), list_A1_B2_C3.remove("C"));
        assertEquals("[(A, 1), (B, 2)]:2", list_A1_B2_C3.toString());
        assertEquals(new KVPair<>("A", "1"), list_A1_B2_C3.first());
    }

    @Test
    public void testA1B2C3RemoveD() {
        assertNull(list_A1_B2_C3.remove("D"));
    }

    @Test
    public void testD1F3G4RemoveDFG() {
        assertEquals(new KVPair<>("D", "1"), list_D1_F3_G4.remove("D"));
        assertEquals("[(F, 3), (G, 4)]:2", list_D1_F3_G4.toString());
        assertEquals(new KVPair<>("F", "3"), list_D1_F3_G4.first());

        assertEquals(new KVPair<>("F", "3"), list_D1_F3_G4.remove("F"));
        assertEquals("[(G, 4)]:1", list_D1_F3_G4.toString());
        assertEquals(new KVPair<>("G", "4"), list_D1_F3_G4.first());

        assertEquals(new KVPair<>("G", "4"), list_D1_F3_G4.remove("G"));
        assertEquals("[]:0", list_D1_F3_G4.toString());
        assertNull(list_D1_F3_G4.first());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNullInEmpty() {
        list_empty.remove(null);
        fail();
    }

    @Test
    public void testEmptyRemoveByValue1() {
        assertNull(list_empty.removeByValue("1"));
    }

    @Test
    public void testA1B2C3RemoveByValue1() {
        assertEquals(new KVPair<>("A", "1"), list_A1_B2_C3.removeByValue("1"));
        assertEquals("[(B, 2), (C, 3)]:2", list_A1_B2_C3.toString());
        assertEquals(new KVPair<>("B", "2"), list_A1_B2_C3.first());
    }

    @Test
    public void testA1B2C3RemoveByValue2() {
        assertEquals(new KVPair<>("B", "2"), list_A1_B2_C3.removeByValue("2"));
        assertEquals("[(A, 1), (C, 3)]:2", list_A1_B2_C3.toString());
        assertEquals(new KVPair<>("A", "1"), list_A1_B2_C3.first());
    }

    @Test
    public void testA1B2C3RemoveByValue3() {
        assertEquals(new KVPair<>("C", "3"), list_A1_B2_C3.removeByValue("3"));
        assertEquals("[(A, 1), (B, 2)]:2", list_A1_B2_C3.toString());
        assertEquals(new KVPair<>("A", "1"), list_A1_B2_C3.first());
    }

    @Test
    public void testA1B2C3RemoveByValue4() {
        assertNull(list_A1_B2_C3.removeByValue("4"));
    }

    @Test
    public void testD1F3G4RemoveByValue134() {
        assertEquals(new KVPair<>("D", "1"), list_D1_F3_G4.removeByValue("1"));
        assertEquals("[(F, 3), (G, 4)]:2", list_D1_F3_G4.toString());
        assertEquals(new KVPair<>("F", "3"), list_D1_F3_G4.first());

        assertEquals(new KVPair<>("F", "3"), list_D1_F3_G4.removeByValue("3"));
        assertEquals("[(G, 4)]:1", list_D1_F3_G4.toString());
        assertEquals(new KVPair<>("G", "4"), list_D1_F3_G4.first());

        assertEquals(new KVPair<>("G", "4"), list_D1_F3_G4.removeByValue("4"));
        assertEquals("[]:0", list_D1_F3_G4.toString());
        assertNull(list_D1_F3_G4.first());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveByValueNullInEmpty() {
        list_empty.removeByValue(null);
        fail();
    }

    @Test
    public void testEmptySearchA() {
        assertNull(list_empty.search("A"));
    }

    @Test
    public void testA1B2C3SearchA() {
        assertEquals("1", list_A1_B2_C3.search("A"));
        assertEquals("[(A, 1), (B, 2), (C, 3)]:3", list_A1_B2_C3.toString());
    }

    @Test
    public void testA1B2C3SearchB() {
        assertEquals("2", list_A1_B2_C3.search("B"));
        assertEquals("[(A, 1), (B, 2), (C, 3)]:3", list_A1_B2_C3.toString());
    }

    @Test
    public void testD1F3G4SearchG() {
        assertEquals("4", list_D1_F3_G4.search("G"));
        assertEquals("[(D, 1), (F, 3), (G, 4)]:3", list_D1_F3_G4.toString());
    }


    @Test
    public void testD1F3G4SearchB() {
        assertNull(list_D1_F3_G4.search("B"));
        assertEquals("[(D, 1), (F, 3), (G, 4)]:3", list_D1_F3_G4.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testD1F3G4SearchNull() {
        list_D1_F3_G4.search(null);
        fail();
    }

    @Test
    public void testEmptySize(){
        assertEquals(0, list_empty.size());
    }

    @Test
    public void testA1B2C3Size(){
        assertEquals(3, list_A1_B2_C3.size());
    }

    @Test
    public void testEmptyFirst(){
        assertNull(list_empty.first());
    }

    @Test
    public void testA1B2C3First(){
        assertEquals(new KVPair<>("A", "1"), list_A1_B2_C3.first());
    }

    @Test
    public void testEmptyIterable(){
        StringBuilder sb = new StringBuilder();

        for(KVPair<String, String> pair: list_empty)
            sb.append(pair.toString());

        assertEquals("", sb.toString());
    }

    @Test
    public void testD1F3G4Iterable(){
        StringBuilder sb = new StringBuilder();

        for(KVPair<String, String> pair: list_D1_F3_G4)
            sb.append(pair.toString());

        assertEquals("(D, 1)(F, 3)(G, 4)", sb.toString());
    }
}