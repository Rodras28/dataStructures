/**
 * @author João Francisco Barata (68004) jf.barata@campus.fct.unl.pt
 * @author Rodrigo Pinheiro (69067) rse.pinheiro@campus.fct.unl.pt
 */
package dataStructures;

import dataStructures.exceptions.NoSuchElementException;

class TwoWayDoublyIterator<E> extends DoublyIterator<E> implements TwoWayIterator<E> {
    static final long serialVersionUID = 0L;

    /**
     * Node with the first element in the iteration.
     */
    private DoublyListNode<E> lastNode;
    /**
     * Node with the previous element in the iteration.
     */
    private DoublyListNode<E> prevToReturn;

    /**
     * DoublyLLIterator constructor
     *
     * @param first - Node with the first element of the iteration
     * @param last  - Node with the last element of the iteration
     */
    public TwoWayDoublyIterator(DoublyListNode<E> first, DoublyListNode<E> last) {
        super(first);
        this.lastNode = last;
        this.prevToReturn = null;

    }

    /**
     * Returns true if previous would return an element
     * rather than throwing an exception.
     * @return true iff the iteration has more elements in the reverse direction
     * Time complexity: O(1) best and worst case.
     */
    public boolean hasPrevious( ) {
        return this.prevToReturn != null;
    }

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration
     * @throws NoSuchElementException - if call is made without verifying pre-condition
     * Time complexity: O(1) best and worst case.
     */
    public E next( ){
        E toReturn = super.next();
        this.prevToReturn = (this.nextToReturn != null) ? this.nextToReturn.getPrevious() : null;
        return toReturn;
    }

    /**
     * Returns the previous element in the iteration.
     * @return previous element in the iteration
     * @throws NoSuchElementException - if call is made without verifying pre-condition
     * Time complexity: O(1) best and worst case.
     */
    public E previous( ) throws NoSuchElementException {
        if (!this.hasPrevious()) {
            throw new NoSuchElementException();
        }

        E toReturn = this.prevToReturn.getElement();
        this.prevToReturn = this.prevToReturn.getPrevious();
        return toReturn;
    }
    /**
     * Restarts the iteration in the reverse direction.
     * After fullForward, if iteration is not empty,
     * previous will return the last element
     * Time complexity: O(1) best and worst case.
     */

    public void fullForward() {
        this.nextToReturn = null;
        this.prevToReturn = this.lastNode;
    }

    /**
     * Restarts the iteration.
     * After rewind, if the iteration is not empty, next will return the first element.
     * Time complexity: O(1) best and worst case.
     */
    public void rewind() {
        super.rewind();
        this.prevToReturn = null;
    }
}
