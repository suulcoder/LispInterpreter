package com.company;

import javafx.scene.AmbientLight;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

            while (true) {
                System.out.print(">>>");
                String input = textField.getText();
                System.out.println(input);
                try {
                    input = br.readLine();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                if (input == null || input.equals("quit")) break;
                try {
                    String retorno = " ";
                    retorno = retorno + Arrays.toString(Interpreter.reemplazar(input)) + "\n";
                    retorno = retorno + Interpreter.leer(input) + "\n";
                    System.out.println(retorno);
                    retorno = retorno + Interpreter.evaluate(Interpreter.leer(input)) + "\n";
                    salida.setText(retorno);
                    System.out.println(retorno);
                } catch (Exception f) {
                    f.printStackTrace();
                }
            }
        }
        else{
            if("RESET".equals((e.getActionCommand()))){
                salida.setText(" ");
            }
        }
    }
}