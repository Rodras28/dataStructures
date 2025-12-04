/**
 * @author João Francisco Barata (68004) jf.barata@campus.fct.unl.pt
 * @author Rodrigo Pinheiro (69067) rse.pinheiro@campus.fct.unl.pt
 */
package dataStructures;
import dataStructures.exceptions.NoSuchElementException;

class ArrayIterator<E> implements Iterator<E> {
    private E[] elems;
    private int counter;
    private int current;
    
    /** Constructor.
     *
     * @param elems array of elements
     * @param counter number of elements in array
     */
    // Time complexity: O(1) best and worst case.
    public ArrayIterator(E[] elems, int counter) {
        this.elems = elems;
        this.counter = counter;
        rewind();
    }

    /* Restarts the iteration.
     Time complexity: O(1) best and worst case.
     */
    @Override
    public void rewind() {
        current = 0;
    }

    /* Returns true if the iteration has more elements. 
     Time complexity: O(1) best and worst case.
     */
    @Override
    public boolean hasNext() {
        return current < counter;
    }

    /* Returns the current element in the iteration.
     Time complexity: O(1) best and worst case.
     */
    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return elems[current++];
    }

}