package com.supinfo.geekquote.resources;

import com.supinfo.geekquote.dao.DaoFactory;
import com.supinfo.geekquote.dao.QuoteDao;
import com.supinfo.geekquote.model.Quote;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
@Path("/quotes")
public class QuoteResources {

    private QuoteDao quoteDao;

    public QuoteResources() {
        quoteDao = DaoFactory.getQuoteDao();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Quote> getAllQuotes() {
        return quoteDao.getAllQuotes();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertQuote(Quote quote) {
        quoteDao.insertQuote(quote);
        String uri = "/" + quote.getId();
        return Response.created(URI.create(uri)).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateQuote(Quote quote) {
        quoteDao.updateQuote(quote);
    }
}
