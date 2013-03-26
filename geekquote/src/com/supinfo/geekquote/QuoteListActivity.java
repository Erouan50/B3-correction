package com.supinfo.geekquote;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.supinfo.geekquote.model.Quote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuoteListActivity extends Activity {

    private List<Quote> quotes = new ArrayList<Quote>();
    private ListView quoteList;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button button = (Button) findViewById(R.id.quoteButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView) findViewById(R.id.quoteText);
                addQuote(String.valueOf(textView.getText()));
                textView.setText("");
            }
        });

        ListAdapter adapter = new QuoteListAdapter(this, android.R.layout.simple_list_item_1, quotes);
        quoteList = (ListView) findViewById(R.id.quoteList);
        quoteList.setAdapter(adapter);
//
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
