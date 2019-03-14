

import java.util.List;

/**
 * Parent class of the different list clasees
 * @param <E> type of object that will be stored on the list
 */
abstract class AbstractList<E>
        implements List<E> {

    public AbstractList()
    // post: does nothing
    {
    }

    /**
     * Method used to add an element to the last position of the list
     * @param value item that should be added
     */

    public abstract void addLast(E value);

    /**
     * Method used to remove the last element of the list
     * @return the removed element
     */
    public abstract E removeLast();

    /**
     * Method used to return if the list is empty or not
     * @return true if the list is empty false if not
     */
    public boolean isEmpty()
    // post: returns true iff list has no elements
    {
        return size() == 0;
    }

    /**
     * Method used to get a specified element of the list
     * @param value element wanted
     * @return if the list contains the element or not
     */
    public boolean contains(Object value) {
        // pre: value is not null
        // post: returns true iff list contains an object equal to value

        return -1 != indexOf(value);
    }

    /**
     * Method used to return a smaller list of the main list
     * @param fromIndex start of the new list
     * @param toIndex end of the new list
     * @return the smaller list
     */
    public List<E> subList(int fromIndex, int toIndex) {
        //Los tipos de lista Circular, doubleLinkedlist y simple list
        //funcionan a través de nodos y no de indices.
        return null;
    }
}