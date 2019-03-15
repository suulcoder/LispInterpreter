import org.junit.Test;

import static org.junit.Assert.*;

public class DoublyLinkedListTest {

    @Test
    public void removeLast() {


        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addFirst("First");
        list.add("Second");
        list.add("Third");
        list.add("Fourth");
        list.add("Fifth");
        list.add("Sixth");
        list.add("Seventh");
        list.add("Eighth");
        list.add("Nineth");
        list.add("Tenth");
        assertEquals(list.removeLast(),"First");



    }



    @Test
    public void get() {


        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addFirst("First");
        list.add("Second");
        list.add("Third");
        list.add("Fourth");
        list.add("Fifth");
        list.add("Sixth");
        list.add("Seventh");
        list.add("Eighth");
        list.add("Nineth");
        list.add("Tenth");
        assertEquals(list.get(0),"First");

    }
}