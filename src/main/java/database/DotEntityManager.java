package database;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import server.Dot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped

public class DotEntityManager implements Serializable {
    private final EntityManagerFactory entityManagerFactory = JPAFactory.getFactory();
    @Produces
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void addDot(Dot dot) {
        try{
            entityManager.getTransaction().begin();
            entityManager.merge(dot);
            entityManager.getTransaction().commit();
        } catch (Exception e){
            entityManager.getTransaction().rollback();
        }
    }
    public ArrayList<Dot> getDotsList(){
        try{
            entityManager.getTransaction().begin();
            List dotList = entityManager.createQuery("SELECT dote FROM Dot dote")
                    .setMaxResults(50)
                    .getResultList();
            entityManager.getTransaction().commit();
            return (ArrayList<Dot>) dotList;
        } catch(Exception e){
            return new ArrayList<Dot>();
        }
    }

    public void clear(){
        try{
            entityManager.getTransaction().begin();
            entityManager.createQuery("DELETE FROM Dot dote").executeUpdate();
            entityManager.getTransaction().commit();
        } catch(Exception e){
            entityManager.getTransaction().rollback();
        }
    }
}
