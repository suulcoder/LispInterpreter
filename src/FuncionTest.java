import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FuncionTest {

    private List<Object> args = new DoublyLinkedList<Object>();
    private List<Object> action = new DoublyLinkedList<Object>();
    @Test
    void doAction() {
        args.add("a");
        args.add("b");
        action.add("+");
        action.add("a");
        action.add("b");
        Funcion fun = new Funcion("sumar",args,action);
        Object[] realargs = {"1","2"};
        assertEquals(fun.doAction(realargs),"(+ 1 2)");
    }
}