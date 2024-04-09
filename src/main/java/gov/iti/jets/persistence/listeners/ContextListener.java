package gov.iti.jets.persistence.listeners;

import gov.iti.jets.persistence.connection.JPAManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {
    private EntityManagerFactory entityManagerFactory;
    private boolean contextInitialized;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ContextListener.contextInitialized: Initializing database connection...");
        try {
            entityManagerFactory = JPAManager.INSTANCE.getEntityManagerFactory();
            contextInitialized = true;
            System.out.println("ContextListener.contextInitialized: Database connection initialized successfully.");
        } catch (Exception e) {
            System.err.println("ContextListener.contextInitialized: Failed to initialize database connection: " + e.getMessage());
            contextInitialized = false;
            // Optionally, you might want to throw ServletException to indicate a failure in initialization.
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
            System.out.println("ContextListener.contextDestroyed: Database connection closed.");
        }
    }

    public boolean isContextInitialized() {
        return contextInitialized;
    }
}
