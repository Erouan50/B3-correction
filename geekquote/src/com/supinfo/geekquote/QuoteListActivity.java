package com.supinfo.geekquote;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.supinfo.geekquote.adapter.QuoteListAdapter;
import com.supinfo.geekquote.dialog.EditQuoteDialog;
import com.supinfo.geekquote.model.Quote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuoteListActivity extends Activity {

    public static final String QUOTE_INTENT_PARAMETER = "quote";
    public static final String QUOTE_INDEX_INDENT_PARAMETER = "quote_index";
    private static final int QUOTE_ACTIVITY = 1;
    private List<Quote> quotes = new ArrayList<Quote>();
    private QuoteListAdapter quoteAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quote_list);

        initAdapter();
        initButton();
        initQuoteList();
    }

    private void initAdapter() {
        quoteAdapter = new QuoteListAdapter(this, android.R.layout.simple_list_item_1, quotes);
        ListView quoteList = (ListView) findViewById(R.id.quoteList);
        quoteList.setAdapter(quoteAdapter);
        quoteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(QuoteListActivity.this, QuoteActivity.class);
                Quote quote = quoteAdapter.getItem(i);
                intent.putExtra(QUOTE_INTENT_PARAMETER, quote);
                intent.putExtra(QUOTE_INDEX_INDENT_PARAMETER, i);
                startActivityForResult(intent, QUOTE_ACTIVITY);
            }
        });
        quoteList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Quote quote = quotes.get(i);
                DialogFragment dialogFragment = new EditQuoteDialog(quote, new EditQuoteDialog.EditQuoteListener() {
                    @Override
                    public void editedQuote() {
                        quoteAdapter.notifyDataSetChanged();
                    }
                });
                dialogFragment.show(getFragmentManager(), "dialog");
                return false;
            }
        });
    }

    private void initButton() {
        Button button = (Button) findViewById(R.id.quoteButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView textView = (TextView) findViewById(R.id.quoteText);
                addQuote(String.valueOf(textView.getText()));
                quoteAdapter.notifyDataSetChanged();
                textView.setText("");
            }
        });
    }

    private void initQuoteList() {
        String[] strQuotes = getResources().getStringArray(R.array.strQuotes);
        for (String strQuote : strQuotes) {
            addQuote(strQuote);
        }
    }

    private void addQuote(String strQuote) {
        Quote quote = new Quote();
        quote.setStrQuote(strQuote);
        quote.setRating(0);
        quote.setCreationDate(new Date());
        quotes.add(quote);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case QUOTE_ACTIVITY:
                computeQuoteActivityResult(resultCode, data);
                break;
            default:
                throw new RuntimeException("Bad request code");
        }
    }

    private void computeQuoteActivityResult(int resultCode, Intent data) {
        switch (resultCode) {
            case QuoteActivity.RESULT_OK:
                Bundle bundle = data.getExtras();
                Quote quote = (Quote) bundle.getSerializable(QUOTE_INTENT_PARAMETER);
                int quoteIndex = bundle.getInt(QUOTE_INDEX_INDENT_PARAMETER);
                quotes.set(quoteIndex, quote);
                break;
            case QuoteActivity.RESULT_CANCELED:
                Toast.makeText(this, R.string.cancel_message, 1000).show();
                break;
            default:
                throw new RuntimeException("Bad result code");
        }
    }

}
