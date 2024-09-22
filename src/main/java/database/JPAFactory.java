package database;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.Properties;

public class JPAFactory {
    private static final EntityManagerFactory factory;

    static {
        try {
            Properties info = new Properties();
            info.load(JPAFactory.class.getClassLoader().getResourceAsStream("/db.cfg"));
            factory = Persistence.createEntityManagerFactory("default", info);
        } catch (Throwable ex) {
            System.err.println("Something went wrong during initializing EclipseLink: " + ex);
            throw new ExceptionInInitializerError();
        }
    }
    public static EntityManagerFactory getFactory() {
        return factory;
    }
}