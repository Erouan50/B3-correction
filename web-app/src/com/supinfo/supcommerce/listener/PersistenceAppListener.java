package com.supinfo.supcommerce.listener;

import com.supinfo.supcommerce.utils.PersistenceManager;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Antoine Rouaze <antoine.rouaze@zenika.com>
 */
@WebListener
public class PersistenceAppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        PersistenceManager.closeEntityManagerFactory();
    }
}
