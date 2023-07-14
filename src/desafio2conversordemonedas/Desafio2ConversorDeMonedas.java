/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package desafio2conversordemonedas;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author migue
 */
public class Desafio2ConversorDeMonedas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean b=true;
        while(b){
            String[] choices = { "CONVERSOR DE MONEDAS", "CONVERSOR DE TEMPERATURA"};
            String input = (String) JOptionPane.showInputDialog(null, "Que deseas hacer",
            "Desafio Alura", JOptionPane.QUESTION_MESSAGE, null, // Use // default// icon
                        choices, // Array of choices
                        choices[0]); // Initial choice
            //System.out.println(input);
            if(input==null){
                b=false;
            }
            if("CONVERSOR DE MONEDAS".equals(input)){
                Double monto=0.0;
                try {
                    monto = Double.valueOf(JOptionPane.showInputDialog("Ingresa la cantidad de dinero que deseas convertir")); 
                    System.out.println(monto);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Ingresa un numero",
          "ERROR", JOptionPane.ERROR_MESSAGE);
                } catch (NullPointerException e){
                    b=false;
                }
                
                //Seleccion de tipo de cambio y formato para URL verificando que se haya ingresado un monto
                if(monto!=0){
                    String link = null;
                    String from = "";
                    String to = "";
                    try{
                        String[] tiposDeCambio = { "Bolivianos a Dolares", "Bolivianos a Euros", "Bolivianos a Libras", "Bolivianos a Yen",
                        "Boliviano a Sol Peruano", "Dolar a Bolivianos", "Euro a Boliviano", "Soles a Bolivianos"};
                        String input2 = (String) JOptionPane.showInputDialog(null, "Cual cambio quieres hacer",
                    "Desafio Alura", JOptionPane.QUESTION_MESSAGE, null, // Use // default// icon
                                tiposDeCambio, // Array of choices
                                choices[0]); // Initial choice
                        
                        switch (input2) {
                            case "Bolivianos a Dolares" -> {
                                System.out.println("BOB A USD");
                                from = "BOB";
                                to = "USD";
                            }
                            case "Bolivianos a Euros" -> {
                                from = "BOB";
                                to = "EUR";
                            }
                            case "Bolivianos a Libras" -> {
                                from = "BOB";
                                to = "GBP";
                            }
                            case "Bolivianos a Yen" -> {
                                from = "BOB";
                                to = "JPY";
                            }
                            case "Boliviano a Sol Peruano" -> {
                                System.out.println("BOL TO PEN");
                                from = "BOB";
                                to = "PEN";
                            }
                            case "Dolar a Bolivianos" -> {
                                from = "USD";
                                to = "BOB";
                            }
                            case "Euro a Bolivian" -> {
                                from = "EUR";
                                to = "BOB";
                            }
                            case "Soles a Bolivianos" -> {
                                from = "PEN";
                                to = "BOB";
                            }
                            default -> throw new AssertionError();
                        }
                        link = "https://api.getgeoapi.com/v2/currency/convert?api_key=d15bfb8dcc2a8af8522af233d24cee264924edd1&from="+from+"&to="+to+"&amount="+monto+"&format=json";
                        System.out.println(link);
                    }catch(NullPointerException e){
                        b=false;
                    }
                    try{
                    URL url = new URL(link);
                    HttpURLConnection conn  = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    int respondeCode = conn.getResponseCode();
                    if(respondeCode != 200){
                        throw new RuntimeException("OCURRIO UN ERROR "+ respondeCode);
                    }
                    else{
                        StringBuilder informationString = new StringBuilder();
                        Scanner scanner = new Scanner(url.openStream());
                        while(scanner.hasNext()){
                            informationString.append(scanner.nextLine());
                        }
                        scanner.close();
                        System.out.println(informationString.indexOf("rate_for_amount"));
                        System.out.println(informationString);
                        String responseString = informationString.toString();
                        JSONObject jsonResponse = new JSONObject(responseString);
                        String rateForAmount = jsonResponse.getJSONObject("rates")
                        .getJSONObject(to)
                        .getString("rate_for_amount");
                        JOptionPane.showMessageDialog(null, "El monto es: "+rateForAmount+" "+ to);
                        int a = JOptionPane.showConfirmDialog(null, "Desea Continuar??");
                        //0 = YES
                        //1 = NO
                        if(a==1){
                            b=false;
                        }
                        System.out.println(a);
                        System.out.println("Rate for amount: " + rateForAmount);
                    }
                }catch(Exception e){
                    //e.printStackTrace();
                    b=false;
                }
                }
                
            }
            if(input == "CONVERSOR DE TEMPERATURA"){
                String[] temp = { "°C a °F", "°F a °C"};
                String input2 = (String) JOptionPane.showInputDialog(null, "Cual cambio quieres hacer",
                    "Desafio Alura", JOptionPane.QUESTION_MESSAGE, null, // Use // default// icon
                                temp, // Array of choices
                                choices[0]);
                double multiplicador = 0;
                double suma=0;
                String dimension="";
                try{
                        switch (input2) {
                                case "°C a °F" -> {
                                    System.out.println("°C a °F");
                                    multiplicador = 1.8;
                                    suma=32;
                                    dimension = "°F";
                                }
                                case "°F a °C" -> {
                                    System.out.println("°F a °C");
                                    multiplicador = 1/1.8;
                                    suma=-32/1.8;
                                    dimension = "°C";
                                }
                                default -> throw new AssertionError();
                            } 
                    double valor = Double.parseDouble(JOptionPane.showInputDialog("Ingresa el valor de temperatura a convertir"));
                    double salida = valor*multiplicador+suma;
                    System.out.println(salida);
                    JOptionPane.showMessageDialog(null, salida+" "+dimension);
                    int a = JOptionPane.showConfirmDialog(null, "Desea Continuar??");
                            //0 = YES
                            //1 = NO
                            if(a==1){
                                b=false;
                            }
                }catch(NullPointerException e){
                    b=false;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "PROGRAMA TERMINADO");
    }
    
}
