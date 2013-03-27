package com.supinfo.geekquote.dao.webservice;

import android.util.Log;
import com.supinfo.geekquote.R;
import com.supinfo.geekquote.application.QuoteApplication;
import com.supinfo.geekquote.dao.QuoteDao;
import com.supinfo.geekquote.model.Quote;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static com.supinfo.geekquote.dao.sql.QuoteSQLHelper.QUOTE_CREATE_DATE_COLUMN;
import static com.supinfo.geekquote.dao.sql.QuoteSQLHelper.QUOTE_ID_COLUMN;
import static com.supinfo.geekquote.dao.sql.QuoteSQLHelper.QUOTE_RATING_COLUMN;
import static com.supinfo.geekquote.dao.sql.QuoteSQLHelper.QUOTE_STR_QUOTE_COLUMN;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class QuoteWebServiceDao implements QuoteDao {

    private final String url = QuoteApplication.getApplicationResources().getString(R.string.web_service_url);
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private static final String LOG_TAG = "GeekQuote";
    private DateFormat dateFormat;


    public QuoteWebServiceDao() {
        dateFormat = new SimpleDateFormat(DATE_FORMAT);
    }

    public void insertQuote(Quote quote) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.setEntity(new StringEntity(convertQuoteIntoJson(quote).toString()));
            post.setHeader("Content-type", "application/json");
            HttpResponse response = client.execute(post);

            String location = response.getFirstHeader("Location").getValue();
            String id = location.split("/")[location.split("/").length - 1];
            quote.setId(Long.parseLong(id));
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse quote in Json", e);
        }
    }

    public void updateQuote(Quote quote) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpPut put = new HttpPut(url);
            put.setHeader("Content-type", "application/json");
            put.setEntity(new StringEntity(convertQuoteIntoJson(quote).toString()));
            client.execute(put);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse quote in Json", e);
        } catch (UnsupportedEncodingException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (ClientProtocolException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        }
    }

    public List<Quote> getAllQuotes() {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            get.setHeader("Accept", "application/json");
            HttpResponse response = client.execute(get);
            String responseStr = EntityUtils.toString(response.getEntity());

            List<Quote> quotes = new ArrayList<Quote>();
            if (responseStr != null && !responseStr.isEmpty() && !responseStr.equals("null")) {
                JSONObject quotesObject = new JSONObject(responseStr);
                if (quotesObject.get("quote") instanceof JSONArray) {
                    computeJsonArray(quotes, quotesObject);
                } else if (quotesObject.get("quote") instanceof JSONObject) {
                    computeJsonObject(quotes, quotesObject.getJSONObject("quote"));
                } else {
                    Log.e(LOG_TAG, "Unable to parse Json");
                }
            }
            return quotes;
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to execute request", e);
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Unable to parse Json", e);
        } catch (ParseException e) {
            Log.e(LOG_TAG, "Unable to parse Date", e);
        }
        return new ArrayList<Quote>();
    }

    private void computeJsonObject(List<Quote> quotes, JSONObject quotesObject) throws JSONException, ParseException {
        Quote quote = convertJsonIntoString(quotesObject);
        quotes.add(quote);
    }

    private void computeJsonArray(List<Quote> quotes, JSONObject quotesObject) throws JSONException, ParseException {
        JSONArray quotesArray = quotesObject.getJSONArray("quote");
        for (int i = 0; i < quotesArray.length(); i++) {
            computeJsonObject(quotes, quotesArray.getJSONObject(i));
        }
    }

    private Quote convertJsonIntoString(JSONObject singleQuoteJson) throws JSONException, ParseException {
        Quote quote = new Quote();
        quote.setId(singleQuoteJson.getLong(QUOTE_ID_COLUMN));
        quote.setStrQuote(singleQuoteJson.getString(QUOTE_STR_QUOTE_COLUMN));
        quote.setRating(singleQuoteJson.getInt(QUOTE_RATING_COLUMN));
        quote.setCreationDate(dateFormat.parse(singleQuoteJson.getString(QUOTE_CREATE_DATE_COLUMN)));
        return quote;
    }

    private JSONObject convertQuoteIntoJson(Quote quote) throws JSONException {
        JSONObject quoteJson = new JSONObject();
        quoteJson.put(QUOTE_ID_COLUMN, quote.getId());
        quoteJson.put(QUOTE_STR_QUOTE_COLUMN, quote.getStrQuote());
        quoteJson.put(QUOTE_RATING_COLUMN, quote.getRating());
        quoteJson.put(QUOTE_CREATE_DATE_COLUMN, dateFormat.format(quote.getCreationDate()));
        return quoteJson;
    }
}
