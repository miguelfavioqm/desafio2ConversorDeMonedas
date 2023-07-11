/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package desafio2conversordemonedas;

import javax.swing.JOptionPane;

/**
 *
 * @author migue
 */
public class Desafio2ConversorDeMonedas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] choices = { "CONVERSOR DE MONEDAS", "CONVERSOR DE TEMPERATURA"};
        String input = (String) JOptionPane.showInputDialog(null, "Que deseas hacer",
        "Desafio Alura", JOptionPane.QUESTION_MESSAGE, null, // Use // default// icon
                    choices, // Array of choices
                    choices[0]); // Initial choice
        System.out.println(input);
        
    }
    
}
