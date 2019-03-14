

import org.omg.CORBA.Any;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static java.util.logging.Level.parse;
import static sun.misc.Version.print;

public class Interpreter {
    private static Ambiente global = new Ambiente();//Establecemos un tipo Ambiente con un map

    public static String[] reemplazar(String code) {//Metodo estatico que permite que se puedan remplazar las cadenas por simples parentesis
        return code.replaceAll("\\(", " ( ").replaceAll("\\)", " ) ").trim().split("\\s+");
    }

    /*This method will allow us to convert a String number type, to a possible
     *type, Integer, Float or Double in that order and if it is not possible it returns the same String
     * */

    public Ambiente getGlobal(){
        return global;
    }
    private static Object convert(String numero) {
        try {
            return Integer.parseInt(numero);
        } catch (NumberFormatException e) {
            try {
                return Float.parseFloat(numero);
            } catch (NumberFormatException e2) {
                try {
                    return Double.parseDouble(numero);
                } catch (NumberFormatException e3) {
                    return numero;
                }
            }
        }
    }

    private static Object read(List<String> elementos) throws Exception {
        if (elementos.isEmpty()) {//si no ha parametros
            throw new IllegalArgumentException("unexpected EOF while reading");//Devolver un EOF unexpected while reading
        }
        String first = elementos.remove(0);//Obtenemos un posbile parentesis

        if (first.equals("(")) {//Si es un parentesis
            List<Object> retorno = new ArrayList<Object>(elementos.size() - 1);//Convertimos en una lista
            while (!elementos.get(0).equals(")"))//Hasta que se cierre el parentesis
                retorno.add(read(elementos));
            elementos.remove(0);//Removemos el parentesis, para dejar solo los valores en un List
            return retorno;
        } else if (first.equals(")")) {//Si el primero devuelve un parentesis cerrado, devolver error
            throw new Exception("unexpected ')'");
        } else {
            return convert(first);//Convertir el elemento a numero para leerlo
        }
    }

    public static Object leer(String code) throws Exception {
        return read(new ArrayList<String>(Arrays.asList(reemplazar(code))));//Leemos nuestro archivo, con una lista. y los metodos anteriores
    }

    private static Object evaluate(Object exp, Ambiente env) {//Permite evaluar el comando
        if (exp instanceof String) {//Si es un string
            return env.get(exp);//devolvemos el valor que tiene dentro del hashmap
        } else if (exp instanceof List) {//Si es una lista
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) exp;//Creamos lista
            Object elemento = list.get(0);
            if (elemento instanceof String) {//si el elemento es un String
                String symbol = (String) elemento;
                switch (symbol) {
                    /*Evaluamos si el comando es:*/
                    case "if": {
                        boolean result = !evaluate(list.get(1), env).equals(0);
                        Object action = result ? list.get(2) : list.get(3);
                        return evaluate(action, env);//devolvemos hasta que no se repita mas el ciclo
                    }
                    case "define": {
                        Object result = evaluate(list.get(2), env);
                        env.put(list.get(1), result);
                        return result;//devolver resutltado.
                    }
                    case "set!"://permite hacer set de una comando
                        Object key = list.get(1);//tomamos el key
                        if (!env.containsKey(key))
                            System.out.println(
                                    "WARNING: setting undefined variable '" + list.get(1) + '\'');
                        Object result = evaluate(list.get(2));
                        env.put(key, result);
                        return result;
                    case "lambda":
                        @SuppressWarnings("unchecked")
                        List<Object> params = (List<Object>) list.get(1);
                        return (Function<Object[], Object>)(x) -> {
                            return evaluate(list.get(2), new Ambiente(env, params.toArray(), x));
                        };
                    case "quote":
                        return list.get(1);
                    case "repeat":
                        int count = (Integer) list.get(1);
                        for (int i = 0; i < count; ++i) {
                            System.out.println(evaluate(list.get(2), env));
                        }
                        return null;
                    case "defun":
                        try{
                            List<Object> control = (List<Object>) list.get(1);
                            List<Object> control2 = (List<Object>) list.get(2);
                            String nombre = (String)control.get(0);
                            control.remove(0);
                            Funcion funcion = new Funcion(nombre,control,control2);
                            global.put(nombre, funcion);
                        }
                        catch (Exception e){
                            System.out.println("There is something wrong with your Function");
                        }
                        return null;
                    case "atom":
                        try{
                            if (list.get(1) instanceof String || list.get(1) instanceof Integer){
                                return true;
                            }
                            return false;

                        }catch (Exception e){
                            System.out.println("There is an error with an ATOM");
                        }
                    default:
                        Object proc = env.get(elemento);
                        Object[] args = new Object[list.size() - 1];
                        for (int i = 0; i < args.length; ++i) {
                            args[i] = evaluate(list.get(i + 1), env);
                        }
                        if (proc instanceof UnaryOperator) {
                            @SuppressWarnings("unchecked")
                            UnaryOperator<Object> op = (UnaryOperator<Object>) proc;
                            return op.apply(args[0]);
                        } else if (proc instanceof BinaryOperator) {
                            BinaryOperator<Object> op = (BinaryOperator<Object>) proc;
                            return op.apply((Number)args[0], (Number)args[1]);
                        }
                        else {
                            Object fun = env.get(symbol);
                            if(fun instanceof Funcion){
                                Object[] newargs = new Object[((List<Object>) exp).size() - 1];
                                for(int i=0;i<newargs.length;i++){
                                    newargs[i]=((List<Object>) exp).get(i+1);
                                }
                                return  ((Funcion) fun).doAction(newargs);

                            }

                        }
                }
            }
        } else if (exp instanceof Number) {//Si es un numero
            return exp;
        }
        return null;
    }

    public static Object evaluate(Object exp) {//evaluar
        return evaluate(exp, global);
    }

    /*A continuacion aparecen las funciones logicas*/
    static Number add(Number one, Number two) {//SUMAR
        if (one instanceof Double || two instanceof Double) {
            return one.doubleValue() + two.doubleValue();
        } else if (one instanceof Float || two instanceof Float) {
            return one.floatValue() + two.floatValue();
        } else if (one instanceof Integer || two instanceof Integer) {
            return one.intValue() + two.intValue();
        } else if (one instanceof Long || two instanceof Long) {
            return one.longValue() + two.longValue();
        } else if (one instanceof Short || two instanceof Short) {
            return one.shortValue() + two.shortValue();
        }
        return one.byteValue() + two.byteValue();
    }

    static Number multiply(Number one, Number two) {//MULTIPLICAR
        if (one instanceof Double || two instanceof Double) {
            return one.doubleValue() * two.doubleValue();
        } else if (one instanceof Float || two instanceof Float) {
            return one.floatValue() * two.floatValue();
        } else if (one instanceof Integer || two instanceof Integer) {
            return one.intValue() * two.intValue();
        } else if (one instanceof Long || two instanceof Long) {
            return one.longValue() * two.longValue();
        } else if (one instanceof Short || two instanceof Short) {
            return one.shortValue() * two.shortValue();
        }

        return one.byteValue() * two.byteValue();
    }
    static Number subtract(Number one, Number two){//Restar
        if (one instanceof Double || two instanceof Double){
            return one.doubleValue()-two.doubleValue();
        }
        else if (one instanceof Float || two instanceof Float){
            return one.floatValue()-two.floatValue();
        }
        else if (one instanceof Integer || two instanceof Integer) {
            return one.intValue() - two.intValue();
        }
        else if (one instanceof Long || two instanceof Long) {
            return one.longValue() - two.longValue();
        }
        else if (one instanceof Short || two instanceof Short) {
            return one.shortValue() - two.shortValue();
        }

        return one.byteValue() - two.byteValue();
    }
    static Number divide(Number one, Number two){//divide
        if (one instanceof Double || two instanceof Double){
            return one.doubleValue()/two.doubleValue();
        }
        else if (one instanceof Float || two instanceof Float){
            return one.floatValue()/two.floatValue();
        }
        else if (one instanceof Integer || two instanceof Integer) {
            return one.intValue()/two.intValue();
        }
        else if (one instanceof Long || two instanceof Long) {
            return one.longValue()/two.longValue();
        }
        else if (one instanceof Short || two instanceof Short) {
            return one.shortValue()/two.shortValue();
        }

        return one.byteValue()/two.byteValue();
    }


    static Number abs(Number val) {//VALOR ASBSOLUTO
        if (val instanceof Double) {
            return Math.abs(val.doubleValue());
        } else if (val instanceof Float) {
            return Math.abs(val.floatValue());
        } else if (val instanceof Long) {
            return Math.abs(val.longValue());
        }

        return Math.abs(val.intValue());
    }


}
