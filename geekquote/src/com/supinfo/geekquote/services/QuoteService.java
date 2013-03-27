package com.supinfo.geekquote.services;

import com.supinfo.geekquote.dao.DaoFactory;
import com.supinfo.geekquote.dao.QuoteDao;
import com.supinfo.geekquote.model.Quote;

import java.util.List;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class QuoteService {

    public QuoteDao quoteDao;

    public QuoteService() {
        quoteDao = DaoFactory.getQuoteDao();
    }

    public void addQuote(Quote quote) {
        quoteDao.insertQuote(quote);
    }

    public void updateQuote(Quote quote) {
        quoteDao.updateQuote(quote);
    }

    public List<Quote> getAllQuotes() {
        return quoteDao.getAllQuotes();
    }
}
