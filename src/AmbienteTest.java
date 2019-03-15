import org.junit.Test;

import static org.junit.Assert.*;

public class AmbienteTest {

    @Test
    public void get() {
        Ambiente amb = new Ambiente();
        String value =  "value";
        assertEquals(amb.get(value),null);

    }

    @Test
    public void containsKey() {
        Ambiente amb = new Ambiente();
        int value =  11122;
        assertEquals(amb.containsKey(value),false);
    }
}