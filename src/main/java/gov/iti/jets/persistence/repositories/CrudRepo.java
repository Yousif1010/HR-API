package gov.iti.jets.persistence.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public abstract class CrudRepo<T,ID> {
    protected EntityManager entityManager;
    protected CrudRepo(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    public Optional<T> save(T entity) {
        try {
            System.out.println("saving..");
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
            entityManager.refresh(entity); // ensure the data is synchronized with the database
            System.out.println(Optional.of(entity));
            return Optional.of(entity);
        } catch (Exception e) {
            // Log the exception or handle it appropriately
            System.out.println(e.getMessage());
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<List<T>> findAll(Class<T> objClass){
        String jpql = "SELECT p FROM "+ objClass.getSimpleName()+ " p";
        TypedQuery<T> findQuery = entityManager.createQuery(jpql, objClass);
        return Optional.ofNullable(findQuery.getResultList());
    }

    public Optional<T> findById(Class<T> objClass, ID id){
        return Optional.ofNullable(entityManager.find(objClass, id));
    }

    public Optional<T> update(T entity){
        System.out.println("updating..");
        entityManager.getTransaction().begin();
        T updatedEntity = entityManager.merge(entity); // from application to database
        entityManager.getTransaction().commit();

        // Ensure the updated entity is synchronized with the database
        entityManager.refresh(updatedEntity);

        return Optional.of(updatedEntity);
    }

    public void delete(T entity){
        entityManager.getTransaction().begin();
        if (!entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        }

        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    public T deleteById(Class<T> objClass, ID id) throws RuntimeException {
        try {
            entityManager.getTransaction().begin();

            T entity = entityManager.find(objClass, id);
            if (entity != null) {
                entityManager.remove(entity);
                entityManager.getTransaction().commit();
                return entity; // Return the deleted entity
            } else {
                entityManager.getTransaction().rollback(); // Rollback transaction if entity not found
                return null; // Entity not found, return null
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback(); // Rollback transaction in case of exception
            throw new RuntimeException("Failed to delete the item with ID: " + id, e); // Provide more informative error message
        }
    }

}
