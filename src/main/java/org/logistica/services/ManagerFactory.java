package org.logistica.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;
import org.eclipse.persistence.jpa.JpaEntityManager;

public class ManagerFactory {

    private static final ManagerFactory managerFactory = new ManagerFactory();
    private final EntityManagerFactory Factory;

    private ManagerFactory() {
        Factory = Persistence.createEntityManagerFactory("muni101");
    }

    public static ManagerFactory getFactoryManager() {
        return managerFactory;
    }

    public EntityManagerFactory getFactory() {
        return Factory;
    }

    public Connection getConnection() throws SQLException {
        EntityManager em = null;
        Connection connection = null;
        em = Factory.createEntityManager();
        if (em instanceof JpaEntityManager jpaEntityManager) {
            connection = jpaEntityManager.getServerSession().getAccessor().getConnection();
        }
        return connection;
    }

}
