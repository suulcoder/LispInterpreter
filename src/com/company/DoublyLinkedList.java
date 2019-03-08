package com.company;

/*Algnos metodos son tomados de los archivos de la clase*/
import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

class DoublyLinkedList<E> extends AbstractList<E>{

    private int count;
    private DoublyLinkedNode<E> head;
    private DoublyLinkedNode<E> tail;

    public DoublyLinkedList()
    // post: constructs an empty list
    {
        head = null;
        tail = null;
        count = 0;
    }
    public DoublyLinkedList(E... array)
    // array: un array de Strings
    {
        head = null;
        tail = null;
        count = 0;
        for(int i = 0; 0<array.length; i++){
            addLast(array[i]);
        }
    }


    public void addFirst(E value)
    // pre: value is not null
    // post: adds element to head of list
    {
        // construct a new element, making it head
        head = new DoublyLinkedNode<E>(value, head, null);
        // fix tail, if necessary
        if (tail == null) tail = head;
        count++;
    }


    public void addLast(E value)
    // pre: value is not null
    // post: adds new value to tail of list
    {
        // construct new element
        tail = new DoublyLinkedNode<E>(value, null, tail);
        // fix up head
        if (head == null) head = tail;
        count++;
    }


    public E removeLast()
    // pre: list is not empty
    // post: removes value from tail of list
    {
        DoublyLinkedNode<E> temp = tail;
        tail = tail.getPreviousElement();
        if (tail == null) {
            head = null;
        } else {
            tail.setNext(null);
        }
        count--;
        return temp.getData();
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return false;
    }

    @Override
    public boolean addAll(int i, Collection<? extends E> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int i) {
        DoublyLinkedNode<E> finger = head;
        for (int j=0;j<i-1;j++){
            finger = finger.nextElement;
        }
        return finger.getData();
    }

    @Override
    public E set(int i, E e) {
        return null;
    }

    @Override
    public void add(int i, E e) {

    }

    @Override
    public E remove(int i) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int i) {
        return null;
    }
}
