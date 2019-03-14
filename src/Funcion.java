import oracle.jrockit.jfr.StringConstantPool;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class Funcion {

    private String name;
    private List<Object> args;
    private List<Object> action;

    public Funcion(String name, List<Object> args, List<Object> action){
        this.name=name;
        this.args=args;
        this.action=action;
        List<Object> test = action;
    }

    public Object doAction(Object[] realArgs){
        String accion = action.toString();
        int index = accion.indexOf("a");
        try{
            for (int i=0;i<args.size();i++){
                accion = accion.replaceAll(args.get(i).toString(),realArgs[i].toString());
            }
        }
        catch (Exception e){
            System.out.println("This fucntion does not take given parameters");
        }
        accion = accion.replaceAll("]",")");
        accion = accion.replaceAll("[\\[]", "(");
        accion = accion.replaceAll(",","");
        try {
            return Interpreter.evaluate(Interpreter.leer(accion));
        } catch (Exception f) {
            f.printStackTrace();
        }
        return null;
    }

    private String remplazar(Object[] realArgs){
        String retorno = "";
        System.out.println(action);
        for (int i=0;i<action.size();i++){
            if (action.get(i) instanceof  List){
                Object[] lista = (Object[])action.get(i);
                retorno = remplazar(lista) + retorno;
            }
            else{
                for (int j=0;j<args.size();j++){
                    if(args.get(j).equals(action.get(i))){
                        retorno = retorno +realArgs[j];
                    }
                }
            }
        }
        return retorno;
    }

}
