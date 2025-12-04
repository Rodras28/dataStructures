/**
 * @author João Francisco Barata (68004) jf.barata@campus.fct.unl.pt
 * @author Rodrigo Pinheiro (69067) rse.pinheiro@campus.fct.unl.pt
 */
package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

public class FilterIterator<E> implements Iterator<E> {
    static final long serialVersionUID = 0L;

    /**
     *  Iterator of elements to filter.
     */
    private Iterator<E> iterator;

    /**
     *  Filter.
     */
    private Predicate<E> filter;

    /**
     * Node with the next element in the iteration.
     */
    private E nextToReturn;

    /**
     *
     * @param list to be iterated
     * @param filter filter
     */
    public FilterIterator(Iterator<E> list, Predicate<E> filter) {
        this.iterator = list;
        this.filter = filter;
        this.nextToReturn = null;
    }

    /**
     * Returns true if next would return an element
     *
     * @return true iff the iteration has more elements
     * Time complexity: O(1) best case. O(n) worst case.
     */
    public boolean hasNext() {
        while(this.nextToReturn == null && this.iterator.hasNext()) {
            E elem = this.iterator.next();
            if(this.filter.check(elem)) {
                this.nextToReturn = elem;
            }
        }

        return this.nextToReturn != null;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @throws NoSuchElementException - if call is made without verifying pre-condition
     * Time complexity: O(1) best and worst case.
     */
    public E next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }

        E toReturn = this.nextToReturn;
        this.nextToReturn = null;
        return toReturn;
    }

    /**
     * Restarts the iteration.
     * After rewind, if the iteration is not empty, next will return the first element.
     * Time complexity: O(1) best case. O(n) worst case.
     */
    public void rewind() {
        this.iterator.rewind();
        this.nextToReturn = null;
    }
}