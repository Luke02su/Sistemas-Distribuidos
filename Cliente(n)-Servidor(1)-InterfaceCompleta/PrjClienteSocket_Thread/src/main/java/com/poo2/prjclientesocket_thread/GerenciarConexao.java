
package com.poo2.prjclientesocket_thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class GerenciarConexao extends Thread {
    FormClienteChat form;
    Socket conexao;

    public GerenciarConexao(FormClienteChat form, Socket conexao) {
        this.form = form;
        this.conexao = conexao;
    }
    
    @Override
    public void run() { // administra mensagens recebidas
        try {
            BufferedReader entrada = new BufferedReader(
            new InputStreamReader(conexao.getInputStream()));
            String linha = "";
            while(true) {
                linha = linha + entrada.readLine();
                
                if(linha == null) {
                    System.out.println("Conexão Encerrada!!!");
                    break;
                }
                linha = linha + "\n";
                form.getTxaMsgRecebidas().setText(linha);
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
