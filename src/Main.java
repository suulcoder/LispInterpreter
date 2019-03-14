

import javafx.scene.AmbientLight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.awt.event.*;
import java.awt.*;
import java.util.stream.Stream;
import javax.swing.*;

public class Main extends JFrame implements ActionListener{

    static JButton botton, reset;
    static JTextField textField = new JTextField(20);
    private Panel panelEntrada, panelSalida;
    private JPanel panelDeLaVentana;
    private JTextArea salida = new JTextArea();

    public Main(){
        super("LISP-INTERPRETER");/*Sera el nombre de la ventana*/
        botton = new JButton("Empezar");
        reset = new JButton("RESET");
        botton.setActionCommand("empezar");
        botton.addActionListener(this);
        textField.addActionListener(this);
        panelDeLaVentana = (JPanel)this.getContentPane();
        panelEntrada = new Panel();//los siguientes paneles son para poner orden y estetica
        panelSalida = new Panel();
        panelEntrada.add(botton);
        panelEntrada.add(reset);
        panelSalida.add(textField);
        panelSalida.add(salida);
        panelDeLaVentana.add(panelEntrada,BorderLayout.NORTH);//agreamos las ventanas a la interfaz grafica
        panelDeLaVentana.add(panelSalida,BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        /*Imprimimos la ventana en la pantalla*/

        Main miAplicacion = new Main();
        miAplicacion.setBounds(10,10,200,200);
        miAplicacion.pack();
        miAplicacion.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if("empezar".equals((e.getActionCommand()))){
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Interpreter interprete = new Interpreter();

            interprete.getGlobal().put("pi", Math.PI);
            interprete.getGlobal().put("+", (BinaryOperator<Number>) Interpreter::add);//Agregamos estas funciones a nuestro map
            interprete.getGlobal().put("*", (BinaryOperator<Number>) Interpreter::multiply);
            interprete.getGlobal().put("abs", (UnaryOperator<Number>) Interpreter::abs);
            interprete.getGlobal().put("-", (BinaryOperator<Number>) Interpreter::subtract);
            interprete.getGlobal().put("/", (BinaryOperator<Number>) Interpreter::divide);
            final Integer[] lineCounter = {1};

            try {
                Stream<String> lines = Files.lines(
                        Paths.get("datos.txt"),
                        StandardCharsets.UTF_8
                );
                lines.forEach(s ->{
                    if (s == null || s.equals("quit"))
                    try {
                        String retorno = " ";
                        retorno = retorno + Arrays.toString(Interpreter.reemplazar(s)) + "\n";
                        retorno = retorno + Interpreter.leer(s) + "\n";
                        System.out.println(retorno);
                        retorno = retorno + Interpreter.evaluate(Interpreter.leer(s)) + "\n";
                        salida.setText(retorno);
                        System.out.println(retorno);
                        lineCounter[0] +=1;

                    } catch (Exception f) {
                        f.printStackTrace();
                        System.out.println("Error en linea"+lineCounter[0]);
                    }

                });
            }catch (IOException exception){
                System.out.println("Error");
            }


        }
        else{
            if("RESET".equals((e.getActionCommand()))){
                salida.setText(" ");
            }
        }
    }
}