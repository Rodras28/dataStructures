/**
 * @author João Francisco Barata (68004) jf.barata@campus.fct.unl.pt
 * @author Rodrigo Pinheiro (69067) rse.pinheiro@campus.fct.unl.pt
 */
package dataStructures;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dataStructures.exceptions.*;

public class SortedDoublyLinkedList<E> implements SortedList<E> {
    static final long serialVersionUID = 0L;

    /**
     *  Node at the head of the list.
     */
    private DoublyListNode<E> head;
    /**
     * Node at the tail of the list.
     */
    private DoublyListNode<E> tail;
    /**
     * Number of elements in the list.
     */
    private int currentSize;
    /**
     * Comparator of elements.
     */
    private final Comparator<E> comparator;
    /**
     * Constructor of an empty sorted double linked list.
     * head and tail are initialized as null.
     * currentSize is initialized as 0.
     */
    public SortedDoublyLinkedList(Comparator<E> comparator) {
        this.head = null;
        this.tail = null;
        this.currentSize = 0;
        this.comparator = comparator;
    }

    /**
     * Returns true iff the list contains no elements.
     * @return true if list is empty
     * Time complexity: O(1) best and worst case.
     */
    public boolean isEmpty() {
        return currentSize==0;
    }

    /**
     * Returns the number of elements in the list.
     * @return number of elements in the list
     * Time complexity: O(1) best and worst case.
     */

    public int size() {
        return currentSize;
    }

    /**
     * Returns an iterator of the elements in the list (in proper sequence).
     * @return Iterator of the elements in the list
     * Time complexity: O(1) best and worst case.
     */
    public Iterator<E> iterator() {
        return new DoublyIterator<>(head);
    }

    /**
     * Returns the first element of the list.
     * @return first element in the list
     * @throws NoSuchElementException - if size() == 0
     * Time complexity: O(1) best and worst case.
     */
    public E getMin( ) throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return head.getElement();
    }

    /**
     * Returns the last element of the list.
     * @return last element in the list
     * @throws NoSuchElementException - if size() == 0
     * Time complexity: O(1) best and worst case.
     */
    public E getMax( ) throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return tail.getElement();
    }
    /**
     * Returns the first occurrence of the element equals to the given element in the list.
     * @param element to be got
     * @return element in the list or null
     * Time complexity: O(1) best case. O(n) worst case.
     */
    public E get(E element) {
        DoublyListNode<E> current = head;
        while (current != null) {
            if (comparator.compare(current.getElement(), element) == 0) {
                return current.getElement();
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Returns true iff the element exists in the list.
     *
     * @param element to be found
     * @return true iff the element exists in the list.
     * Time complexity: O(1) best case. O(n) worst case.
     */
    public boolean contains(E element) {
        return get(element) != null;
        
    }

    /**
     * Inserts the specified element at the list, according to the natural order.
     * If there is an equal element, the new element is inserted after it.
     * @param element to be inserted
     * Time complexity: O(1) best case. O(n) worst case.
     */
    public void add(E element) {
        DoublyListNode<E> newNode = new DoublyListNode<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            DoublyListNode<E> current = head;
            DoublyListNode<E> previous = null;
            while (current != null && comparator.compare(current.getElement(), element) <= 0) {
                previous = current;
                current = current.getNext();
            }

            if (previous == null) {
                newNode.setNext(head);
                head.setPrevious(newNode);
                head = newNode;
            } else if (current == null) {
                previous.setNext(newNode);
                newNode.setPrevious(previous);
                tail = newNode;
            } else {
                previous.setNext(newNode);
                newNode.setPrevious(previous);
                newNode.setNext(current);
                current.setPrevious(newNode);
            }
        }
        currentSize++;
    }

    /**
     * Removes and returns the first occurrence of the element equals to the given element in the list.
     * @param element to be removed
     * @return element removed from the list or null if !belongs(element)
     */
    public E remove(E element) {
        DoublyListNode<E> current = head;
        while (current != null) {
            if (comparator.compare(current.getElement(), element) == 0) {
                // Element found, remove it
                if (current.getPrevious() != null) {
                    current.getPrevious().setNext(current.getNext());
                } else {
                    head = current.getNext();
                }
                if (current.getNext() != null) {
                    current.getNext().setPrevious(current.getPrevious());
                } else {
                    tail = current.getPrevious();
                }
                currentSize--;
                return current.getElement();
            }
            current = current.getNext();
        }
        return null;
    }

    protected void addFirst(E elem) {
        DoublyListNode<E> newNode = new DoublyListNode<>(elem, null, head);
        if (head != null) {
            head.setPrevious(newNode);
        }
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        currentSize++;
    }

    protected void addLast(E elem) {
        DoublyListNode<E> newNode = new DoublyListNode<>(elem, tail, null);
        if (tail != null) {
            tail.setNext(newNode);
        }
        tail = newNode;
        if (head == null) {
            head = newNode;
        }
        currentSize++;
    }

    protected void addMiddle(DoublyListNode<E> previous, DoublyListNode<E> current, DoublyListNode<E> newNode) {
        previous.setNext(newNode);
        newNode.setPrevious(previous);
        newNode.setNext(current);
        current.setPrevious(newNode);
        currentSize++;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeInt(this.currentSize);

        DoublyListNode<E> currentNode = this.head;
        while(currentNode != null) {
            out.writeObject(currentNode.getElement());
            currentNode = currentNode.getNext();
        }
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.head = null;
        this.tail = null;
        this.currentSize = 0;

        int size = in.readInt();

        for(int i = 0; i < size; i++) {
            E element = (E) in.readObject();
            this.addLast(element);
        }
    }
}