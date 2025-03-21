package com.poo2.prjclientesocket_thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import javax.swing.*;

public class Interface extends javax.swing.JFrame {
    private Socket socket;
    private PrintStream saida;
    private BufferedReader entrada;
    private String meuNome;

    public Interface() {
        initComponents();
        conectarAoServidor();
    }

    private void conectarAoServidor() {
        try {
            socket = new Socket("127.0.0.1", 2222);
            saida = new PrintStream(socket.getOutputStream());
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            meuNome = JOptionPane.showInputDialog(this, "Digite seu nome:");
            if (meuNome == null || meuNome.trim().isEmpty()) {
                meuNome = "Anônimo";
            }
            saida.println(meuNome);

            new Thread(this::receberMensagens).start();
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao servidor!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void receberMensagens() {
        try {
            String linha;
            while ((linha = entrada.readLine()) != null) {
                txtChat.append(linha + "\n");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        enviarMensagem = new javax.swing.JTextField();
        btnEnviar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEnviar.setText("Enviar");
        btnEnviar.addActionListener(evt -> enviarMensagem());

        txtChat.setEditable(false);
        txtChat.setColumns(20);
        txtChat.setRows(10);
        jScrollPane1.setViewportView(txtChat);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(enviarMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enviarMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEnviar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    private void enviarMensagem() {
        String mensagem = enviarMensagem.getText().trim();
        if (!mensagem.isEmpty()) {
            saida.println(mensagem);
            enviarMensagem.setText("");
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Interface().setVisible(true));
    }

    private javax.swing.JButton btnEnviar;
    private javax.swing.JTextField enviarMensagem;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtChat;
}
