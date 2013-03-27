package com.supinfo.geekquote.application;

import com.sun.jersey.api.core.PackagesResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
@ApplicationPath("/resources")
public class QuoteWebApplication extends PackagesResourceConfig {

    public QuoteWebApplication() {
        super("com.supinfo.geekquote.resources");
    }
}
