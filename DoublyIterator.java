/**
 * @author João Francisco Barata (68004) jf.barata@campus.fct.unl.pt
 * @author Rodrigo Pinheiro (69067) rse.pinheiro@campus.fct.unl.pt
 */
package dataStructures;
import dataStructures.exceptions.NoSuchElementException;

public class DoublyIterator<E> implements Iterator<E> {
    static final long serialVersionUID = 0L;

    /**
     * Node with the first element in the iteration.
     */
    private DoublyListNode<E> firstNode;

    /**
     * Node with the next element in the iteration.
     */
    DoublyListNode<E> nextToReturn;

     /**
     * Constructor.
     * Time complexity: O(1) best and worst case.
     */
    public DoublyIterator(DoublyListNode<E> first) {
        this.firstNode = first;
       
        this.rewind();
    }


    /**
     * Restarts the iteration.
     * After rewind, if the iteration is not empty, next will return the first element in the iteration.
     * Time complexity: O(1) best and worst case.
     */
    public void rewind() {
        this.nextToReturn = this.firstNode;
    }

   
     /**
     * Returns true if the iteration has more elements.
     * In other words, returns true if a call to next() would return an element instead of throwing an exception.
     * Time complexity: O(1) best and worst case.
     */
    public boolean hasNext() {
        return this.nextToReturn != null;
    }

    
/**
     * Returns the next element in the iteration.
     * Time complexity: O(1) best and worst case.
     */
    public E next() throws NoSuchElementException {
        if(!this.hasNext()) {
            throw new NoSuchElementException();
        }

        E toReturn = this.nextToReturn.getElement();
        this.nextToReturn = this.nextToReturn.getNext();
        return toReturn;
    }

}
