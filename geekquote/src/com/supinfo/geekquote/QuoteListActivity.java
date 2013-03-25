package com.supinfo.geekquote;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.supinfo.geekquote.model.Quote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuoteListActivity extends Activity {
    private List<Quote> quotes = new ArrayList<Quote>();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String[] strQuotes = getResources().getStringArray(R.array.strQuotes);
        for (String strQuote : strQuotes) {
            addQuote(strQuote);
        }
        Log.d("QuoteListActivity", quotes.toString());
    }

    private void addQuote(String strQuote) {
        Quote quote = new Quote();
        quote.setStrQuote(strQuote);
        quote.setRating(0);
        quote.setCreationDate(new Date());
        quotes.add(quote);
    }
}
