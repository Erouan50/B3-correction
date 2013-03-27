package com.supinfo.geekquote.application;

import android.app.Application;
import android.content.res.Resources;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class QuoteApplication extends Application {

    private static QuoteApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Resources getApplicationResources() {
        return instance.getResources();
    }
}
