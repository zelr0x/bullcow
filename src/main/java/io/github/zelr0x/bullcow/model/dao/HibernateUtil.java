package io.github.zelr0x.bullcow.model.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * HibernateUtil is a utility class storing Hibernate SessionFactory.
 */
final class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            final Configuration config = new Configuration();
            sessionFactory = config.configure().buildSessionFactory();
        } catch (Throwable e) {
            System.err.println("Error in creating SessionFactory object."
                    + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    /**
     * Get SessionFactory object.
     *
     * @return a SessionFactory object.
     */
    static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Prevents instantiation.
     */
    private HibernateUtil() {
        throw new AssertionError();
    }
}
