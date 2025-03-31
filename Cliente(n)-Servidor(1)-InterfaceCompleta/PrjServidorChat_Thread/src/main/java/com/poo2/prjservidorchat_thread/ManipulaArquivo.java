package com.poo2.prjservidorchat_thread;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ManipulaArquivo {
    public void escreverArq(String texto, String nomeArq) {
        try {
            File f = new File("c:"+nomeArq+".txt");
   
            FileWriter fw = new FileWriter(f, false);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(texto);
            fw.close();
        } catch(IOException ex) {
           ex.printStackTrace();
        }
    }
    
    public BufferedReader lerArq(String nomeArq) {
        BufferedReader br = null;
        try {
            File f = new File("c:"+nomeArq+".txt");
            if(f.exists()) {
                FileReader fr = new FileReader(f);
                br = new BufferedReader(fr);
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return br;
        }
    }
