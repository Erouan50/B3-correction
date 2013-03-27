package com.supinfo.geekquote.dao;

import com.supinfo.geekquote.model.Quote;

import java.util.List;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public interface QuoteDao {

    void insertQuote(Quote quote);

    void updateQuote(Quote quote);

    List<Quote> getAllQuotes();
}
