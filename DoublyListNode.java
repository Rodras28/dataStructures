/**
 * @author João Francisco Barata (68004) jf.barata@campus.fct.unl.pt
 * @author Rodrigo Pinheiro (69067) rse.pinheiro@campus.fct.unl.pt
 */
package dataStructures;

import java.io.Serializable;

public class DoublyListNode<E> implements Serializable {
    static final long serialVersionUID = 0L;

    // Element stored in the node.
    private E elem;

    // (Pointer to) the previous node.
    private DoublyListNode<E> previous;

    // (Pointer to) the next node.
    private DoublyListNode<E> next;

    DoublyListNode(E element, DoublyListNode<E> prev, DoublyListNode<E> nxt) {
        this.elem = element;
        this.previous = prev;
        this.next = nxt;
    }

    DoublyListNode(E elem) {
        this(elem, null, null);
    }

    /*
     * Returns the element stored in the node.
     * Time complexity: O(1) best and worst case.
     */
    public E getElement() {
        return this.elem;
    }

    /*
     * Returns the previous node.
     * Time complexity: O(1) best and worst case.
     */
    public DoublyListNode<E> getPrevious() {
        return this.previous;
    }

    /*
     * Returns the next node.
     * Time complexity: O(1) best and worst case.
     */
    public DoublyListNode<E> getNext() {
        return this.next;
    }

    /*
     * Sets the element stored in the node.
     * Time complexity: O(1) best and worst case.
     */
    public void setElement(E newElem) {
        this.elem = newElem;
    }

    /*
     * Sets the previous node.
     * Time complexity: O(1) best and worst case.
     */
    public void setPrevious(DoublyListNode<E> newPrev) {
        this.previous = newPrev;
    }

    /*
     * Sets the next node.
     * Time complexity: O(1) best and worst case.
     */
    public void setNext(DoublyListNode<E> newNxt) {
        this.next = newNxt;
    }
}