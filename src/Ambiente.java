

import java.util.HashMap;
import java.util.Map;


/*These class will allow us to set an environment, with an Map with
Objects to generalize the types.
 */
public class Ambiente {

    private Map<Object, Object> inner = new HashMap<Object, Object>();
    private Ambiente out;

    public Ambiente(Ambiente out, Object[] params, Object[] args) {
        /*Constructor que nos permitira agregar los parametros y los argmentos de una funcion
          al map, siendo los parametros el key y los argumentos la clave.
         */
        this.out = out;
        if (params != null) {
            for (int i = 0; i < params.length; ++i) {
                inner.put(params[i], args[i]);
            }
        }
    }

    public Ambiente() {//Un segundo constructor para instanciar un Ambiente=null sin caer en un error
        this(null, null, null);
    }

    public void put(Object key, Object value) {//Agregar al map
        inner.put(key, value);
    }

    public Object get(Object exp) {//Obtener por medio de un id
        Object value = inner.get(exp);
        if (value != null)
            return value;
        if (out != null)
            return out.get(exp);
        return null;
    }

    public boolean containsKey(Object key) {//Devuelve si contiene la llave correspondiente
        if (inner.containsKey(key))
            return true;
        if (out != null)
            return out.containsKey(key);
        return false;
    }
}