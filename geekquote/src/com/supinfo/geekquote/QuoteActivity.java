package com.supinfo.geekquote;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import com.supinfo.geekquote.model.Quote;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class QuoteActivity extends Activity {

    private TextView quoteText;
    private TextView quoteDate;
    private RatingBar quoteRatingBar;
    private Button quoteCancel;
    private Button quoteOk;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quote);

        Bundle bundle = getIntent().getExtras();
        final Quote quote = (Quote) bundle.getSerializable(QuoteListActivity.QUOTE_INTENT_PARAMETER);

        initQuoteText(quote);
        initQuoteDate(quote);
        initQuoteRatingBar(quote);
        initQuoteCancel();
        initQuoteOk(quote);
    }

    private void initQuoteText(Quote quote) {
        quoteText = (TextView) findViewById(R.id.quoteText);
        quoteText.setText(quote.getStrQuote());
    }

    private void initQuoteDate(Quote quote) {
        quoteDate = (TextView) findViewById(R.id.quoteDate);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        quoteDate.setText(dateFormat.format(quote.getCreationDate()));
    }

    private void initQuoteRatingBar(Quote quote) {
        quoteRatingBar = (RatingBar) findViewById(R.id.quoteRatingBar);
        quoteRatingBar.setRating(quote.getRating());
    }

    private void initQuoteCancel() {
        quoteCancel = (Button) findViewById(R.id.quoteCancel);
        quoteCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private void initQuoteOk(final Quote quote) {
        quoteOk = (Button) findViewById(R.id.quoteOk);
        quoteOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                quote.setRating((int) quoteRatingBar.getRating());
                intent.putExtra(QuoteListActivity.QUOTE_INTENT_PARAMETER, quote);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}