package com.poo2.prjservidorsocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PrjServidorSocket {

    public static void main(String[] args) {
        try {
            ServerSocket s = new ServerSocket(2222);
            
            while(true) {
                Socket con = s.accept();
                System.out.println("Conectou!");
                
                BufferedReader entrada = new BufferedReader  // guarda os dados
        (new InputStreamReader(con.getInputStream()));
                PrintStream saida = new PrintStream(con.getOutputStream());
                
                String linha = entrada.readLine();
                
                while(linha != null && !(linha.trim().equals(""))) {
                    System.out.println("Msg do Cliente: " + linha);
                    saida.println("Saída: " + linha);
                    linha = entrada.readLine();
            }
                con.close();
             }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}


// não suporta duas conexões, pois é sequencial