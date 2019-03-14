

/**
 * Node used on the double linked list
 * @param <E>
 */
public class DoublyLinkedNode<E> {
    protected E data;
    protected DoublyLinkedNode<E> nextElement;
    protected DoublyLinkedNode<E> previousElement;

    /**
     * Constructor of a double linked node
     * @param v type of element the node contains
     * @param next the node it points to
     * @param previous the node that points to this node
     */
    public DoublyLinkedNode(E v,
                            DoublyLinkedNode<E> next,
                            DoublyLinkedNode<E> previous) {
        data = v;
        nextElement = next;
        if (nextElement != null)
            nextElement.previousElement = this;
        previousElement = previous;
        if (previousElement != null)
            previousElement.nextElement = this;
    }

    public DoublyLinkedNode(E v)
// post: constructs a single element
    {
        this(v, null, null);
    }

    /**
     * Sets the pointer of the node
     * @param objeto the value of the next pointer
     */
    public void setNext(DoublyLinkedNode<E> objeto) {
        this.nextElement = objeto;
    }

    /**
     * Method used to get the previous node
     * @return the previous node
     */
    public DoublyLinkedNode<E> getPreviousElement(){
        return (previousElement);
    }

    /**
     * Used to get the data of the current node
     * @return the object the node contains
     */
    public E getData(){
        return  data;
    }

}
