package com.supinfo.geekquote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.supinfo.geekquote.model.Quote;

import java.util.List;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class QuoteListAdapter extends BaseAdapter {

    private List<Quote> quotes;
    private LayoutInflater inflater;
    private int resource;
    private Context context;

    public QuoteListAdapter(Context context, int textViewResourceId, List<Quote> quotes) {
        this.quotes = quotes;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.resource = textViewResourceId;
        this.context = context;
    }

    @Override
    public int getCount() {
        return quotes.size();
    }

    @Override
    public Quote getItem(int i) {
        return quotes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parentView) {
        View view;
        TextView textView;

        if (convertView == null) {
            view = inflater.inflate(resource, parentView, false);
        } else {
            view = convertView;
        }
        textView = (TextView) view;
        Quote quote = getItem(i);
        textView.setText(quote.getStrQuote());
        if (i % 2 == 0) {
            textView.setBackgroundColor(context.getResources().getColor(android.R.color.background_dark));
            textView.setTextColor(context.getResources().getColor(android.R.color.white));
        } else {
            textView.setBackgroundColor(context.getResources().getColor(android.R.color.background_light));
            textView.setTextColor(context.getResources().getColor(android.R.color.black));
        }
        return textView;
    }
}
