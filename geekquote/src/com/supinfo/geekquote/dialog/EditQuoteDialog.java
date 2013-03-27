package com.supinfo.geekquote.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.supinfo.geekquote.R;
import com.supinfo.geekquote.model.Quote;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class EditQuoteDialog extends DialogFragment {

    private Quote quote;

    public EditQuoteListener editQuoteListener;

    public EditQuoteDialog(Quote quote, EditQuoteListener editQuoteListener) {
        this.quote = quote;
        this.editQuoteListener = editQuoteListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        return new AlertDialog.Builder(getActivity())
                .setTitle(R.string.title_quote_edit)
                .setView(inflater.inflate(R.layout.quote_edit_dialog, null, false))
                .setPositiveButton(R.string.ok_quote_edit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        TextView quoteEditText = (TextView) EditQuoteDialog.this.getDialog().findViewById(R.id.quoteEditText);
                        quote.setStrQuote(String.valueOf(quoteEditText.getText()));
                        editQuoteListener.editedQuote(quote);
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel_quote_edit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).create();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().show();
        TextView quoteEditText = (TextView) getDialog().findViewById(R.id.quoteEditText);
        quoteEditText.setText(quote.getStrQuote());
    }

    public interface EditQuoteListener {
        void editedQuote(Quote quote);
    }
}
