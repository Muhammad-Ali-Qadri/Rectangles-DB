package SkipLists;

import java.util.Objects;

/**
 * Represents a pair of objects
 *
 * @param <A> Any type representing the first element in this pair
 * @param <B> Any type representing the second element in this pair
 * @author Muhammad Ali Qadri
 * @version 1
 */
public class Pair<A, B> {
    /**
     * Represents the first object in the pair with type < A >
     */
    public final A val1;
    /**
     * Represents the second object in the pair with type < B >
     */
    public final B val2;

    /**
     * Constructs this class and initializes values with respective input values
     *
     * @param v1 the first value to be added in this object with type < A >
     * @param v2 the first value to be added in this object with type < B >
     */
    public Pair(A v1, B v2) {
        if (v1 == null || v2 == null) {
            throw new IllegalArgumentException();
        }

        val1 = v1;
        val2 = v2;
    }


    /**
     * Returns the string representation of this object
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return val1 + " | " + val2;
    }


    /**
     * Returns if this object is equals to another object
     *
     * @param o the object to compare this with
     * @return true if this object is equal to input object
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return ((Objects.equals(val1, pair.val1) && Objects.equals(val2,
                pair.val2))
                || (Objects.equals(val2, pair.val1) && Objects.equals(val1,
                pair.val2)));
    }
}
