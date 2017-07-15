package com.test.valemobi.main;

import com.test.dbConnect.dbConnect;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import static java.lang.Boolean.parseBoolean;
import static java.lang.Integer.parseInt;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class main {

    public static void main(String[] args) {
        EntityManager em = dbConnect.getEm();
        EntityTransaction tx = em.getTransaction();
        lerArquivoCSV(em, tx);
        executarTeste(em);
        em.close();
        dbConnect.closeEm();
    }
    public static void lerArquivoCSV(EntityManager entity, EntityTransaction trans){
        BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream("dados.CSV"), "Cp1252"));
            String line;
            System.out.println("Importando registros. Aguarde...");
            try {
                while((line = br.readLine()) != null){
                    String[] row = line.split(";");
                    trans.begin();
                    Customer customer = new Customer();
                    customer.setCpf_cnpj(row[0]);
                    customer.setNm_customer(row[1]);
                    customer.setIs_active(parseBoolean(row[2]));
                    customer.setVl_total(parseInt(row[3]));
                    entity.persist(customer);
                    trans.commit();
                }
                
                System.out.println("Registros importados. Executando teste...");
            } catch (IOException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void executarTeste(EntityManager entity) {
        Query qry1 = entity.createQuery("Select c FROM Customer c WHERE c.vl_total > 560 AND c.id_customer BETWEEN 1500 AND 2700 ORDER BY c.vl_total DESC");
        Query qry2;
        qry2 = entity.createQuery("Select AVG(c.vl_total) FROM Customer c where c.vl_total > 560 AND c.id_customer BETWEEN 1500 AND 2700");
        List<Customer> lista = qry1.getResultList();
        Double media = (Double) qry2.getSingleResult();
        DecimalFormat fmt = new DecimalFormat("#.##");
        System.out.println("A média de VL_TOTAL para os campos solicitados no teste é: " + fmt.format(media));
        System.out.println("Total de campos utilizados para o cálculo da média: " + lista.size());
        System.out.println("Lista de Registros usados no cálculo:");
        for (Customer c : lista) {
            System.out.println("ID: " + c.getId() + " Nome: " + c.getNm_customer() + " CPF/CNPJ: " + c.getCpf_cnpj() + " Valor: " + c.getVl_total());
        }
    }
}
