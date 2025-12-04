/**
 * @author João Francisco Barata (68004) jf.barata@campus.fct.unl.pt
 * @author Rodrigo Pinheiro (69067) rse.pinheiro@campus.fct.unl.pt
 */
package dataStructures;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dataStructures.exceptions.NoSuchElementException;
import dataStructures.exceptions.InvalidPositionException;

public class DoublyLinkedList<E> implements TwoWayList<E> {
    static final long serialVersionUID = 0L;

    // Node at the head of the list.
    protected DoublyListNode<E> head;

    // Node at the tail of the list.
    protected DoublyListNode<E> tail;

     // Number of elements in the list.
    protected int currentSize;

    /**
     * Constructor.
     * Time complexity: O(1) best and worst case.
     */
    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.currentSize = 0;
    }

    /**
     * Checks if the list is empty.
     * Time complexity: O(1) best and worst case.
     */
    public boolean isEmpty() {
        return this.currentSize == 0;
    }

    /**
     * Returns the number of elements in the list.
     * Time complexity: O(1) best and worst case.
     */
    public int size() {
        return this.currentSize;
    }

    /**
     * Returns the first element in the list.
     * Time complexity: O(1) best and worst case.
     */
    public E getFirst() throws NoSuchElementException {
        if(this.isEmpty()) {
            throw new NoSuchElementException();
        }
        
        return this.head.getElement();
    }

    /**
     * Returns the last element in the list.
     * Time complexity: O(1) best and worst case.
     */
    public E getLast() throws NoSuchElementException {
        if(this.isEmpty()) {
            throw new NoSuchElementException();
        }

        return this.tail.getElement();
    }

    /**
     * Returns the node at the specified position in the list.
     * Requires: position ranges from 0 to currentSize - 1.
     * Time complexity: O(1) best case. O(n) worst case.
     */
    protected DoublyListNode<E> getNode(int position) {
        DoublyListNode<E> element = this.head;

        if(position < this.size()/2) {
            element = this.head;
            for(int j=0; j < position; j++) {
                element = element.getNext();
            }
        } else {
            element = this.tail;
            for(int j=this.size()-1; j > position; j--) {
                element = element.getPrevious();
            }
        }

        return element;
    }

    /**
     * Returns the element at the specified position in the list.
     * Range of valid positions: 0, ..., size() - 1.
     * If the specified position is 0, get corresponds to getFirst.
     * If the specified position is size()-1, get corresponds to getLast.
     * Time complexity: O(1) best case. O(n) worst case.
     */
    public E get(int position) throws InvalidPositionException {
        if (position < 0 || position >= this.size()) {
            throw new InvalidPositionException();
        }

        if(position == 0) {
            return this.getFirst();
        } else if(position == this.size() - 1) {
            return this.getLast();
        } 

        return this.getNode(position).getElement();
    }

    /**
    /**
     * Returns the position of the first occurrence of the specified element in the list, if the list contains the element.
     * Otherwise, returns -1.
     * Time complexity: O(1) best case. O(n) worst case.
     */
    public int IndexOf(E elem) {
        DoublyListNode<E> currentElement = this.head;

        int idx = 0;

        while(currentElement != null && !currentElement.getElement().equals(elem)) {
            currentElement = currentElement.getNext();
            idx++;
        }

        if(currentElement == null) {
            return -1;
        }

        return idx;
    }

    /**
     * Returns the node with the first occurrence of the specified element in the list, if the list contains the element.
     * Otherwise, returns null.
     */
    protected DoublyListNode<E> findNode(E elem) {
        DoublyListNode<E> currentElement = this.head;

        while(currentElement != null && !currentElement.getElement().equals(elem)) {
            currentElement = currentElement.getNext();
        }

        if(currentElement == null) {
            return null;
        }

        return currentElement;
    }


    /**
     * Finds the first element in the list equal to the one in the parameter.
     * Otherwise, returns null.
     * Time complexity: O(1) best case. O(n) worst case.
     */
    public E findEquals(E elem) {
        DoublyListNode<E> node = this.findNode(elem);

        if(node == null) {
            return null;
        }

        return node.getElement();
    }

    /**
     * Inserts the specified element at the first position in the list.
     * Time complexity: O(1) best and worst case.
     */
    public void addFirst(E elem) {
        DoublyListNode<E> newNode = new DoublyListNode<E>(elem, null, this.head);

        if(this.isEmpty()) {
            this.tail = newNode;
        } else {
            this.head.setPrevious(newNode);
        }

        this.head = newNode;
        this.currentSize++;
    }

    /**
     * Inserts the specified element at the last position in the list.
     * Time complexity: O(1) best and worst case.
     */
    public void addLast(E elem) {
        DoublyListNode<E> newNode = new DoublyListNode<E>(elem, this.tail, null);

        if(this.isEmpty()) {
            this.head = newNode;
        } else {
            this.tail.setNext(newNode);
        }

        this.tail = newNode;
        this.currentSize++;
    }

    // Inserts the specified element at the specified position in the list.
    // Requires: position ranges from 1 to currentSize - 1.
    protected void addMiddle(E elem, int position) {
        DoublyListNode<E> currentNode = this.getNode(position);
        DoublyListNode<E> prevNode = currentNode.getPrevious();

        DoublyListNode<E> newNode = new DoublyListNode<E>(elem, prevNode, currentNode);

        currentNode.setPrevious(newNode);
        prevNode.setNext(newNode);
        this.currentSize++;
    }

    public void add(int position, E elem) throws InvalidPositionException {
        if (position < 0 || position > this.size()) {
            throw new InvalidPositionException();
        }

        if(position == 0) {
            this.addFirst(elem);
        } else if(position == this.size()) {
            this.addLast(elem);
        } else {
            this.addMiddle(elem, position);
        }
    }

    /**
     * Removes the first node in the list.
     * Requires: the list is not empty.
     */
    protected void removeFirstNode() {
        this.head = this.head.getNext();

        // if head is now null, it means that head was equal to tail (the list only had one element), so we need to delete the reference to the node from the tail as well.
        if(this.head == null) {
            this.tail = null;
        } else {
            this.head.setPrevious(null);
        }
        this.currentSize--;
    }

    /**
     * Removes and returns the element at the first position in the list.
     * Time complexity: O(1) best and worst case.
     */
    public E removeFirst() throws NoSuchElementException {
        if(this.isEmpty()) {
            throw new NoSuchElementException();
        }

        E prevHead = this.head.getElement();

        this.removeFirstNode();
        return prevHead;
    }

    // Removes the last node in the list.
    // Requires: the list is not empty.
    protected void removeLastNode() {
        this.tail = this.tail.getPrevious();

        // if head is now null, it means that head was equal to tail (the list only had one element), so we need to delete the reference to the node from the tail as well.
        if(this.tail == null) {
            this.head = null;
        } else {
            this.tail.setNext(null);
        }
        this.currentSize--;
    }

    /* 
        *Removes and returns the element at the last position in the list.
        * Time complexity: O(1) best and worst case.
        */
    public E removeLast() throws NoSuchElementException {
        if(this.isEmpty()) {
            throw new NoSuchElementException();
        }

        E prevTail = this.tail.getElement();

        this.removeLastNode();
        return prevTail;
    }

    protected void removeMiddleNode(DoublyListNode<E> node) {
        DoublyListNode<E> prevNode = node.getPrevious();
        DoublyListNode<E> nextNode = node.getNext();

        prevNode.setNext(nextNode);
        nextNode.setPrevious(prevNode);
        this.currentSize--;
    }

    // Removes and returns the element at the specified position in the list.
    // Range of valid positions: 0, ..., size()-1.
    // If the specified position is 0, remove corresponds to removeFirst.
    // If the specified position is size()-1, remove corresponds to removeLast.
    // Time complexity: O(1) best case. O(n) worst case.
    public E remove(int position) throws InvalidPositionException {
        if(position < 0 || position >= this.size()) {
            throw new InvalidPositionException();
        }

        if(position == 0) {
            return this.removeFirst();
        } else if(position == this.size() - 1) {
            return this.removeLast();
        } else {
            DoublyListNode<E> node = this.getNode(position);
            this.removeMiddleNode(node);

            return node.getElement();
        }
    }

    // Removes the first occurrence of the specified element from the list and returns true, if the list contains the element.
    // Otherwise, returns false.
    // Time complexity: O(1) best case. O(n) worst case.
    public boolean remove(E elem) {
        int position = this.IndexOf(elem);

        if(position == -1) {
            return false;
        }

        this.remove(position);
        return true;
    }

    public TwoWayIterator<E> twoWayiterator() {
        return new TwoWayDoublyIterator<>(head, tail);
    }

    // Returns an iterator of the elements in the list (in proper sequence).
    // Time complexity: O(1) best and worst case.
     public Iterator<E> iterator() {
        return new DoublyIterator<>(head);
    }

    // Removes all of the elements from the specified list and inserts them at the end of the list (in proper sequence).
    // Time complexity: O(1) best case. O(m) worst case.
    public void append(DoublyLinkedList<E> list) {
        if(list.isEmpty()) {
            return;
        }

        while(!list.isEmpty()) {
            E listElem = list.removeFirst();
            this.addLast(listElem);
        }
    }

    /*
     * Returns the position of the first occurrence of the specified element in the list, if the list contains the element.
     * Otherwise, returns -1.
     * Time complexity: O(1) best case. O(n) worst case.
     */
    public int indexOf(E elem) {
        DoublyListNode<E> currentElement = this.head;

        int idx = 0;

        while(currentElement != null && !currentElement.getElement().equals(elem)) {
            currentElement = currentElement.getNext();
            idx++;
        }

        if(currentElement == null) {
            return -1;
        }

        return idx;
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