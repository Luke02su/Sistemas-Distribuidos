package com.poo2.prjweb_sd.servlets;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GravarArquivo {
    
    public void escreverArq(Pet pet) {
        File f = new File("C:\\Users\\Lenovo\\Objeto2.txt");
        Gson gson = new Gson();
        String msg = gson.toJson(pet);
        
        try {
            FileWriter fw = new FileWriter(f, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(msg);
            fw.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
