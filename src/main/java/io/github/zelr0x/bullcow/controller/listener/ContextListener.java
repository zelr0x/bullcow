package io.github.zelr0x.bullcow.controller.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * ContextListener deregisters JDBC resources.
 *
 * Tomcat likes to issue warnings like that:
 * The web application [] registered the JDBC driver [org.postgresql.Driver]
 * but failed to deregister it when the web application was stopped.
 * To prevent a memory leak, the JDBC Driver has been forcibly deregistered.
 *
 * @see <a href="https://stackoverflow.com/a/23912257">https://stackoverflow.com/a/23912257</a>
 */
// Uncomment to enable.
// Warning disappeared so I decided to comment it out since it's incomplete.
//@WebListener
public final class ContextListener implements ServletContextListener {
    private static final Logger LOG = LoggerFactory.getLogger(ContextListener.class);

    @SuppressWarnings("checkstyle:JavadocMethod")
    @Override
    public void contextInitialized(final ServletContextEvent servletContextEvent) {

    }

    /**
     * Should perform all the steps necessary to free JDBC-related resources.
     * Those steps are:
     * TODO: 1. Close any background tasks which may be using the DB...
     * TODO: 2. Close any DB connection pools...
     * 3. Deregister JDBC drivers in this context's ClassLoader
     *
     * @param sce a servlet context
     */
    public void contextDestroyed(final ServletContextEvent sce) {

        final ClassLoader cl = Thread.currentThread().getContextClassLoader();
        final Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            final Driver driver = drivers.nextElement();
            // Identity check is intentional.
            if (driver.getClass().getClassLoader() == cl) {
                // Driver was registered by the webapp's ClassLoader. Deregister:
                try {
                    LOG.info("Deregistering JDBC driver {}", driver);
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException ex) {
                    LOG.error("Error deregistering JDBC driver {}", driver, ex);
                }
            } else {
                // Driver was not registered by the webapp's ClassLoader
                // and may be in use elsewhere.
                LOG.trace("Not deregistering JDBC driver {} "
                        + "(does not belong to this webapp's ClassLoader)",
                        driver);
            }
        }
    }
}
