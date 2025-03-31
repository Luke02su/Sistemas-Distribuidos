package com.poo2.prjservidorchat_thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

public class GerenciarMensagensClientes extends Thread {
    FormServidorChat2 form;
    Socket conexao;
    Vector clientes;
    String meuNome;
    String palavras[];

    public GerenciarMensagensClientes(FormServidorChat2 form, Socket conexao, Vector clientes, String palavras[]) {
        this.form = form;
        this.conexao = conexao;
        this.clientes = clientes;
        this.palavras = palavras;
    }
    
    @Override
    public void run() {
        //System.out.println(palavras[0]);
        try {
            // objetos que permitem controlar fluxo de comunicação
            BufferedReader entrada = new BufferedReader(
                new InputStreamReader(conexao.getInputStream()));
            PrintStream saida = new PrintStream(
                conexao.getOutputStream());
        
            meuNome = entrada.readLine();
            
            if(meuNome == null) {
                return;
            }
            
            clientes.add(saida); // gravando as mensganes no vector
            
            String linha = entrada.readLine();
            
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
        ManipulaArquivo ma = new ManipulaArquivo();
        Enumeration e = clientes.elements();
        
        //System.out.println(linha.toLowerCase().contains(palavras[0].toLowerCase()));
        
        for (String palavra : palavras) {
            if(linha.toLowerCase().contains(palavra.toLowerCase())) {
                ma.escreverArq(meuNome + acao + linha, "ListaMensagens");
            }
        }
        
        form.getTxaMensagens().setText(
            form.getTxaMensagens().getText()+"\n"+meuNome + acao + linha); // vai para caixa de mensagens
            
        while(e.hasMoreElements()) {
            PrintStream chat = (PrintStream) e.nextElement();
            
            if(chat != saida) {
                chat.println(meuNome+acao+linha);
            } else{
                chat.println("Você"+acao+linha);
            }
        }
    }
}
