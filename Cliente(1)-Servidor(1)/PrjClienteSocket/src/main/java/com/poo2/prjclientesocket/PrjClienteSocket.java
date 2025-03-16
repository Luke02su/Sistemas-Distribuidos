package com.poo2.prjclientesocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class PrjClienteSocket {

    public static void main(String[] args) throws IOException {
        try {
            Socket con = new Socket("192.168.54.52", 2222); //Conectando no IP local (local host). Cliente se conecta na porta 1234
            
            BufferedReader entrada = new BufferedReader
        (new InputStreamReader(con.getInputStream()));
            
            PrintStream saida = new PrintStream(con.getOutputStream()); // guardar o que digita no prompt de comando
            
            String linha;
            
            BufferedReader entradaTeclado = new // guarda os dados caracteres
        BufferedReader(new InputStreamReader(System.in));
            
            while(true) {
                System.out.println("> ");
                linha = entradaTeclado.readLine();
                
                saida.println(linha);
                
                linha = entrada.readLine();
                
                if(linha == null) {
                    System.out.println("Conexão encerrada!");
                    break;
                }
                System.out.println(linha);
            } 
        } 
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
