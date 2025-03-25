package com.poo2.prjclientesocket_thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente_Principal extends Thread {
    
    private static boolean done = false;
    private Socket conexao;
    
    public static void main(String[] args) throws IOException {
        try {
            // Para se conectar a algum servidor, é necessário criar
            // um objeto da classe Socket. O primeiro parãmetro é o IP ou
            // o endereço da máquina à qual se quer conectar e o
            // segundo parâmetro é a porta da aplicação.

            Socket con = new Socket("127.0.0.1", 2222);

            // objetos que permitem controlar o fluxo de comunicação

            PrintStream saida = new PrintStream(con.getOutputStream());
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            //Informação no prompt
            System.out.println("Digite o seu nome: ");
            String meuNome = teclado.readLine();
            saida.println(meuNome);

            Thread t = new Cliente_Principal(con);

            t.start();

            String linha;
            while(true) {
                System.out.println("> ");
                linha = teclado.readLine();

                if(done) {
                    break;
                }
                saida.println(linha);
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public Cliente_Principal(Socket ss) {
        conexao = ss;
    }
    
    @Override
    public void run() {
        try {
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(conexao.getInputStream()));
            String linha;
            while(true) {
                linha = entrada.readLine();
                
                if(linha == null) {
                    System.out.println("Conexão encerrada!!!");
                    break;
                }
                System.out.println();
                System.out.println(linha);
                System.out.println("...> ");
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        done = true;
    }
}