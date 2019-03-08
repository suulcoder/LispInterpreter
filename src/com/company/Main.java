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

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Interpreter interprete = new Interpreter();

        interprete.getGlobal().put("pi", Math.PI);
        interprete.getGlobal().put("+", (BinaryOperator<Number>) Interpreter::add);//Agregamos estas funciones a nuestro map
        interprete.getGlobal().put("*", (BinaryOperator<Number>) Interpreter::multiply);
        interprete.getGlobal().put("abs", (UnaryOperator<Number>) Interpreter::abs);

        while (true) {
            System.out.print(">>>");
            String input = br.readLine();
            if (input == null || input.equals("quit")) break;
            try {
                System.out.println(Arrays.toString(Interpreter.reemplazar(input)));
                System.out.println(Interpreter.leer(input));
                System.out.println(Interpreter.evaluate(Interpreter.leer(input)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}