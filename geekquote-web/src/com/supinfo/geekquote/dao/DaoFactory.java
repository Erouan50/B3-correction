package com.supinfo.geekquote.dao;

import com.supinfo.geekquote.dao.jpa.QuoteJpaDao;
import com.supinfo.geekquote.utils.PersistenceManager;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class DaoFactory {

    private DaoFactory() {
    }

    public static QuoteDao getQuoteDao() {
        return new QuoteJpaDao(PersistenceManager.getEmf());
    }
}
