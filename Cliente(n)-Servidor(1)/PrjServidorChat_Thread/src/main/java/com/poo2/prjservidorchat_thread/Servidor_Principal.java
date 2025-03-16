package com.poo2.prjservidorchat_thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

public class Servidor_Principal extends Thread {

    private static Vector clientes;
    private Socket conexao;
    private String meuNome;
    
    
    public static void main(String[] args) throws IOException {
      // instancia do vetor de clientes conectados
      clientes = new Vector();
      
      try {
          
          //instancia do vector de clientes conectados na porta 2222
          
          ServerSocket ss = new ServerSocket(2222);
          
          while(true) {
              /*Aguarda algum cliente se onectar. O método "accpet()" segura 
              execução até o cliente se conectar.
              */
              
              System.out.println("Esperando um cliente realizar a conexão...");
              Socket con = ss.accept();
              System.out.println("Conexão realizada!!!");
              //Cria uma nova thread para tratar a conexão
              
              Thread t = new Servidor_Principal(con); 
              
              t.start();
              
              //loop esperando um novo cliente se conectar.
          }
       }
      catch(IOException ex) {
          ex.printStackTrace();
      }
    }
    
    public Servidor_Principal(Socket ss) {
        conexao = ss;
    }
    
    //execucao da thread
    public void run() {
        try {
            //objetos que permitem controlar fluxo de comunicação
            BufferedReader entrada = new BufferedReader(
                new InputStreamReader(conexao.getInputStream())); // espera caractesres 
            PrintStream saida = new PrintStream(conexao.getOutputStream());
            
            meuNome = entrada.readLine();
            
            if(meuNome == null) {
                return;
            }
            
            clientes.add(saida);
            
            String linha = entrada.readLine(); //extrair linha que for escrita pelo cliente
            
            while(linha != null && !(linha.trim().equals(""))) {
                enviarParaTodos(saida, " falou: ", linha);
                
                linha = entrada.readLine();
            }
            
            enviarParaTodos(saida, " saiu ", " do chat");
        
            clientes.remove(saida); 
            conexao.close();
        } catch(IOException ex) {
          ex.printStackTrace();
        }
    }
    
    public void enviarParaTodos(PrintStream saida, String acao, String linha) {
        Enumeration e = clientes.elements();
        
        while(e.hasMoreElements()) {
            PrintStream chat = (PrintStream) e.nextElement();
            
            if(chat != saida) { // se nao for o mesmo que mandou mensagem, manda
                chat.println(meuNome+acao+linha);
            }
        }
        
    }
}
