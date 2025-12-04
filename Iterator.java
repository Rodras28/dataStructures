/**
 * @author João Francisco Barata (68004) jf.barata@campus.fct.unl.pt
 * @author Rodrigo Pinheiro (69067) rse.pinheiro@campus.fct.unl.pt
 */
package dataStructures;

import java.io.Serializable;

import dataStructures.exceptions.NoSuchElementException;

public interface Iterator<E> extends Serializable {
    // Returns true iff the iteration has more elements.
    // In other words, returns true if a call to next() would return an element instead of throwing an exception.
    boolean hasNext();

    // Returns the next element in the iteration.
    E next() throws NoSuchElementException;

    // Restarts the iteration.
    // After rewind, if the iteration is not empty, next() will return the first element in the iteration.
    void rewind();
}
