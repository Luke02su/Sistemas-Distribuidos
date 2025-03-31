package com.poo2.prjservidorchat_thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class GerenciarConexao extends Thread {
    FormServidorChat2 form;
    Vector clientes;
    int porta;
    String palavras[];

    public GerenciarConexao(FormServidorChat2 form, Vector clientes, int porta, String palavras[]) {
        this.form = form;
        this.clientes = clientes;
        this.porta = porta;
        this.palavras = palavras;
    }
    
    @Override
    public void run() {
        clientes = new Vector();
        
        try {
            ServerSocket ss = new ServerSocket(porta);
            
            int contaClientes = 0;
            while(true) {
                form.getLblSituacao().setText("Aguardando conexão!!");
                Socket con = ss.accept();
                contaClientes += 1;
                form.getTxtNroClientesConectados().setText(String.valueOf(contaClientes));
                Thread t = new GerenciarMensagensClientes(form, con, clientes, palavras);
                
                t.start();
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }   
}
