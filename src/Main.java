
// Esta clase se utilizó para pruebas ahora la clase Driver es LispInterpreterGUI ya que contiene la interfaz grafica
import javafx.scene.AmbientLight;

import javax.swing.*;
public class Main{
public static void main(String[]args){

        //inicialize the environment of the GUI for display all the buttons and texts fields
        SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                        JFrame frame=new JFrame("panelMain");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setContentPane(new LispInterpreterGUI().panelMain);
                        frame.setSize(600,600);
                        frame.pack();
                        frame.setVisible(true);

                }
        });

        }
        }