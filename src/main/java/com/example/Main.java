package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println("client avviato!");
        Socket s = new Socket("localhost", 3000);
        System.out.println("client collegato!");

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        DataOutputStream out = new DataOutputStream(s.getOutputStream());
        String tentativo = new String();

        do {
            //creazione scanner per input
            Scanner scan = new Scanner(System.in);
            System.out.println("inserire un numero per provare ad indovinare");
            String stringaNum = scan.nextLine();
            System.out.println("hai inserito " + stringaNum);
            tentativo = stringaNum;
            out.writeBytes(tentativo + "\n");
            String risultato = in.readLine();
            if (risultato.equals("=")) {
                String r = in.readLine();
                int tentativi = Integer.parseInt(r);
                System.out.println("HAI INDOVINATO IN " + tentativi + " TENTATIVI");    
                break;
            }


            switch (risultato) {
                case "<":
                    System.out.println("numero troppo piccolo");    
                    break;

                case ">":
                    System.out.println("numero troppo grande");    
                    break;
            
                case"!":
                    System.out.println("valore inserito non valido");
                    break;
            }





        } while (true);

        s.close();
    }
}