/**
 * @author João Francisco Barata (68004) jf.barata@campus.fct.unl.pt
 * @author Rodrigo Pinheiro (69067) rse.pinheiro@campus.fct.unl.pt
 */
package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

public interface TwoWayIterator<E> extends Iterator<E> {
    // Returns true iff the iteration has more elements in the reverse direction. In other words, returns true if a call to previous() would return an element instead of throwing an exception.
    boolean hasPrevious();

    // Returns the previous element in the iteration.
    E previous() throws NoSuchElementException;

    // Restarts the iteration in the reverse direction.
    // After fullForward, if the iteration is not empty, previous() will return the last element in the iteration.
    void fullForward();
}
