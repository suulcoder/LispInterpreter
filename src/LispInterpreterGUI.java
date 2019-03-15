import com.sun.istack.internal.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class LispInterpreterGUI extends JFrame  {
    public JPanel panelMain;
    private JTextArea textPathDirectory;
    private JButton interpreterButton;
    private JTextArea outPutInterpreter;
    private JButton loadPathButton;
    private String path = "";
    String retorno = " ";

    public LispInterpreterGUI() {
        //when the button is clicked the program would star reading the lisp code on the JText area


        loadPathButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                path = textPathDirectory.getText();

            }
        });

        //Button to start the interpretation
        interpreterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
                            Paths.get(path),
                            StandardCharsets.UTF_8
                    );
                    lines.forEach(s -> {
                        System.out.println(s);
                        if (s != null || s.equals("quit"))
                            try {
                                s = s.toLowerCase();
                                retorno = retorno + Interpreter.evaluate(Interpreter.leer(s)) + "\n";
                                outPutInterpreter.setText(retorno);
                                System.out.println(retorno);
                                lineCounter[0] += 1;

                            }
                            catch (java.lang.NullPointerException k){
                                outPutInterpreter.setText("@<<<All Copyrights reserved for Saul Contreras, Roberto Figueroa and Michele Benvenuto>>>");
                            }
                            catch (Exception f) {
                                System.out.println(retorno + "Error en linea:" + lineCounter[0]);
                            }

                    });
                } catch (IOException exception) {
                    outPutInterpreter.setText("Error");
                }


                if ("RESET".equals((e.getActionCommand()))) {
                    outPutInterpreter.setText(" ");
                }

            }

        });
    }
    //method for read the code an write it on a local text file
    private void readTheRawCode() throws IOException {
        String path = textPathDirectory.getText();
        BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\rober\\OneDrive\\Documentos\\GitHub\\LispInterpreter\\src\\program_txt"));
        writer.write(path);
        writer.close();
    }



}
