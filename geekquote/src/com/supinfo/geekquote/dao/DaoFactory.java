package com.supinfo.geekquote.dao;

import com.supinfo.geekquote.dao.webservice.QuoteWebServiceDao;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class DaoFactory {

    private DaoFactory() {
    }

    public static QuoteDao getQuoteDao() {
        return new QuoteWebServiceDao();
    }


}
