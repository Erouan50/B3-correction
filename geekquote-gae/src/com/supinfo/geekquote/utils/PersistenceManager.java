package com.supinfo.geekquote.utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class PersistenceManager {

    private static EntityManagerFactory emf;

    private PersistenceManager() {
    }

    public static EntityManagerFactory getEmf() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("quote-pu");
        }
        return emf;
    }

    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
