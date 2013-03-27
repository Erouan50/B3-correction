package com.supinfo.geekquote.listener;

import com.supinfo.geekquote.utils.PersistenceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
public class PersistenceListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        PersistenceManager.getEmf();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        PersistenceManager.close();
    }
}
