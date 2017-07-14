package com.test.dbConnect;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class dbConnect {
    private static EntityManagerFactory emf;
    static {
        emf = Persistence.createEntityManagerFactory("batchTesteBackPU");
    }
    public EntityManager getEm(){
        return emf.createEntityManager();
    }
    public static void closeEm(){
        emf.close();
    }
}
