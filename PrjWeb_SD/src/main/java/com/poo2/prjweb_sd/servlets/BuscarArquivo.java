package com.poo2.prjweb_sd.servlets;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuscarArquivo {
    public List lerArq() throws IOException {
        Gson gson = new Gson();
        Pet p;
        List<Pet> lstPet = new ArrayList<Pet>();
        File f = new File("C:\\Users\\Lenovo\\Objeto2.txt");
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String linha = "";
        while(br.ready()) {
            linha = br.readLine();
            p = gson.fromJson(linha, Pet.class);
            lstPet.add(p);
            
        }
        return lstPet;
    } 
}
