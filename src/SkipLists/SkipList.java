package SkipLists;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * This class implements the SkipLists.Container interface and represents
 * the skip list data structure to fulfil the interface functionalities
 *
 * @param <K> Key
 * @param <V> Value
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class SkipList<K extends Comparable<? super K>, V>
        extends AbstractContainer<K, V> {
    private SkipNode head; // First element of the top level
    private int size; // number of entries in the Skip List
    private int level; //Highest level in the skip list

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        level = -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KVPair<K, V>> search(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (level == -1) {
            return null; //In case list is empty
        }

        //Find the value by iterating the list from head and the highest level
        SkipNode current = head;

        //Find the node pointing to the key
        for (int i = level; i > -1; i--) {
            while (current.forward[i] != null &&
                   current.forward[i].element().getKey().compareTo(key) < 0) {
                current = current.forward[i];
            }
        }
        current = current.forward[0]; //Move to the found element

        //As all equal keys are placed adjacent to each other
        //We iterate through the list when first is
        // found till the last and return the list
        if (current != null && current.element().getKey().equals(key)) {
            List<KVPair<K, V>> foundElements = new ArrayList<>();

            while (current != null && current.element().getKey().equals(key)) {
                foundElements.add(current.pair);
                current = current.forward[0];
            }

            return foundElements;
        }
        else {
            return null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KVPair<K, V> first() {
        return (head == null || head.forward[0] == null) ?
                null : head.forward[0].element();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
        if (it == null) {
            throw new IllegalArgumentException();
        }

        int newLevel = randomLevel();
        if (newLevel > level) { //Adjust header if new node is deeper
            adjustHead(newLevel);
            adjustLevel(newLevel);
        }

        //The nodes who will point to newNode on their respective level
        SkipNode[] update = (SkipNode[]) Array.newInstance(SkipNode.class,
                level + 1);
        SkipNode current = head;

        //Find insertion point for the new item
        for (int i = level; i > -1; i--) {
            while (current.forward[i] != null &&
                   current.forward[i].element().getKey()
                           .compareTo(it.getKey()) < 0) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        SkipNode newNode = new SkipNode(it, newLevel);

        for (int i = 0; i <= newLevel; i++) {
            //who the new node points to
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode; //who points to the new node
        }

        size++;
    }


    /**
     * {@inheritDoc}
     */

    @Override
    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            return null;
        }

        //The nodes who will point to the deleted node`s forward nodes
        SkipNode[] update = (SkipNode[]) Array.newInstance(SkipNode.class,
                level + 1);
        SkipNode current = head;

        //Find deletion point
        for (int i = level; i > -1; i--) {
            while (current.forward[i] != null &&
                   current.forward[i].element().getKey().compareTo(key) < 0) {
                current = current.forward[i];
            }
            update[i] = current;
        }

        current = current.forward[0];

        //key does not exist
        if (current == null || !current.element().getKey().equals(key)) {
            return null;
        }

        //Disconnect all previous nodes connected to value
        for (int i = 0; i < update.length; i++) {
            update[i].forward[i] = null;
        }

        //Connect all previous nodes connected to nodes value was pointing to
        for (int i = 0; i < current.forward.length; i++) {
            update[i].forward[i] = current.forward[i];
        }

        size--;
        return current.element();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public KVPair<K, V> removeByValue(V val) {
        //Checks for null input and empty list
        if (val == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) {
            return null;
        }

        K key = null;

        //Find key for the given value
        for (KVPair<K, V> pair : this) {
            if (pair.getValue().equals(val)) {
                key = pair.getKey();
                break;
            }
        }

        //Remove and return the item
        return (key == null) ? null : remove(key);
    }


    /**
     * Implements method from Iterable class
     *
     * @return the iterator used to traverse through the list
     */
    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new SkipListIterator();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String dump() {
        //Builder to append string in
        StringBuilder sb = new StringBuilder("SkipList dump:");
        SkipNode current = head;

        //Iterate through each node and write their details to sb
        while (current != null) {
            sb.append("\nNode has depth ");
            if (current.pair == null) {
                sb.append(current.level + 1).append(", Value (null)");
            }
            else {
                sb.append(current.level + 1).append(", Value (")
                        .append(current.pair).append(")");
            }

            current = current.forward[0];
        }

        sb.append("\nSkipList size is: ").append(size());
        return sb.toString();
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     *
     * @param newLevel the number of levels to be added to head
     */
    private void adjustHead(int newLevel) {
        SkipNode node = new SkipNode(null, newLevel);
        System.arraycopy(head.forward, 0, node.forward, 0,
                head.forward.length);
        head = node;
    }

    /**
     * Set the level of the SkipList
     *
     * @param newLevel the new value of level to be set
     */
    private void adjustLevel(int newLevel) {
        level = newLevel;
    }

    /**
     * Returns a random level number which is used as the depth of the SkipNode
     *
     * @return a random level number
     */
    private int randomLevel() {
        int lev = 0;
        Random value = new Random();
        while (Math.abs(value.nextInt()) % 2 == 0) {
            lev++;
        }

        return lev; // returns a random level
    }

    /**
     * This class implements a SkipNode for the SkipLists.SkipList data structure.
     *
     * @author Muhammad Ali Qadri
     */
    private class SkipNode {

        // the SkipLists.KVPair to hold
        private final KVPair<K, V> pair;
        // what is this
        private final SkipNode[] forward;
        // the number of levels
        private final int level;

        /**
         * Initializes the fields with the required SkipLists.KVPair and the number of
         * levels from the random level method in the SkipLists.SkipList.
         *
         * @param tempPair the SkipLists.KVPair to be inserted
         * @param level    the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[]) Array.newInstance(SkipList.SkipNode.class,
                    level + 1);
            this.level = level;
        }


        /**
         * Returns the SkipLists.KVPair stored in the SkipLists.SkipList.
         *
         * @return the SkipLists.KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }

    }

    /**
     * This class implements an iterator for the SkipLists.SkipList data structure.
     * It will be used to iterate the nodes of the SkipLists.SkipList and
     * efficiently be used in any foreach loop or wherever required
     *
     * @author Muhammad Ali Qadri
     */
    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current; //Current node the iterator points to

        /**
         * Constructs this class object.
         */
        public SkipListIterator() {
            //Points to head, or the next element
            current = head;
            if (current.forward[0] != null) {
                current = current.forward[0];
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean hasNext() {
            return !isEmpty() && current != null;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public KVPair<K, V> next() {
            KVPair<K, V> pair = current.element();
            current = current.forward[0];
            return pair;
        }

    }

}
