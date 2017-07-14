package com.test.valemobi.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {

    public static void main(String[] args) {
        lerArquivoCSV();
    }
    public static void lerArquivoCSV(){
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("/dados.csv"), "Cp1252"));
            String line;
            try {
                while((line = br.readLine()) != null){
                    String[] row = line.split(";");
                    System.out.println(row[0] + " - " + row[1] + " - " + row[2] + " - " + row[3]);
                }
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
