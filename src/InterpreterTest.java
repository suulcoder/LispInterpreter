import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class InterpreterTest {

    Interpreter interprete = new Interpreter();

    @org.junit.jupiter.api.Test
    void reemplazar() {
        String[] newcode = {"(","+","2","1",")"};
        String code = "(+ 2 1)";
        assertEquals(Interpreter.reemplazar(code)[0],newcode[0]);
        assertEquals(Interpreter.reemplazar(code)[1],newcode[1]);
        assertEquals(Interpreter.reemplazar(code)[2],newcode[2]);
        assertEquals(Interpreter.reemplazar(code)[3],newcode[3]);
        assertEquals(Interpreter.reemplazar(code)[4],newcode[4]);
    }

    @org.junit.jupiter.api.Test
    void leer() {
        String[] newcode = {"+","2","1"};
        String code = "(+ 2 1)";
        try{
            Object val = Interpreter.leer(code);
            if (val instanceof List){
                List<Object> lista = (List<Object>)val;
                assertEquals(lista.get(0).toString(),newcode[0]);
                assertEquals(lista.get(1).toString(),newcode[1]);
                assertEquals(lista.get(2).toString(),newcode[2]);

            }
            }
        catch (Exception f){
            assertEquals(1,2);
        }

    }

    @org.junit.jupiter.api.Test
    void evaluate() {
        try{
            String input = "( + 2 1)";
            System.out.println(Interpreter.evaluate(Interpreter.leer(input)));
        }
        catch (Exception f){
            assertEquals(1,2);
        }
        }

    @org.junit.jupiter.api.Test
    void add() {
        assertEquals(Interpreter.add(1,2),3);
        assertEquals(Interpreter.add(20,0.1),20.1);
        assertEquals(Interpreter.add(1.21,2),3.21);
        assertEquals(Interpreter.add(97,87),97+87);
    }

    @org.junit.jupiter.api.Test
    void multiply() {
        assertEquals(Interpreter.multiply(5,2),10);
        assertEquals(Interpreter.multiply(0.1,1),0.1);
        assertEquals(Interpreter.multiply(50.003,10),500.03);
        assertEquals(Interpreter.multiply(1,1),1);
    }

    @org.junit.jupiter.api.Test
    void subtract() {
        assertEquals(Interpreter.subtract(70,1),69);
        assertEquals(Interpreter.subtract(79.1,10.1),69.0);
        assertEquals(Interpreter.subtract(45.5,30.5),15.0);
    }

    @org.junit.jupiter.api.Test
    void divide() {
        assertEquals(Interpreter.divide(70,1),70);
        assertEquals(Interpreter.divide(79.1,10).intValue(),7);
    }

    @org.junit.jupiter.api.Test
    void abs() {
        assertEquals(Interpreter.abs(-15),15);
        assertEquals(Interpreter.abs(7),7);
    }
}