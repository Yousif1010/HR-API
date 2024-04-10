package gov.iti.jets.persistence.repositories;

import gov.iti.jets.exceptions.ResourceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.core.Response;

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
            throw new ResourceException("Error in inserting", Response.Status.INTERNAL_SERVER_ERROR);
        }

    }

    public Optional<List<T>> findAll(Class<T> objClass) {
        try {
            String jpql = "SELECT p FROM " + objClass.getSimpleName() + " p";
            TypedQuery<T> findQuery = entityManager.createQuery(jpql, objClass);
            return Optional.ofNullable(findQuery.getResultList());
        } catch (Exception e) {
            // Handle the exception appropriately
            e.printStackTrace(); // Example: Print the stack trace
            throw new ResourceException("Error in finding all", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public Optional<T> findById(Class<T> objClass, ID id){
        T entity = entityManager.find(objClass, id);
        if (entity == null) {
            throw new ResourceException("Not Found " + id, Response.Status.NOT_FOUND);
        }
        return Optional.ofNullable(entity);
    }

    public Optional<T> update(T entity){
        try {
            System.out.println("updating..");
            entityManager.getTransaction().begin();
            T updatedEntity = entityManager.merge(entity); // from application to database
            entityManager.getTransaction().commit();

            // Ensure the updated entity is synchronized with the database
            entityManager.refresh(updatedEntity);

            return Optional.of(updatedEntity);
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return Optional.empty();
        }
    }


    public void delete(T entity){
        entityManager.getTransaction().begin();
        if (!entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        }

        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    public T deleteById(Class<T> objClass, ID id) throws Exception {
        try {
            entityManager.getTransaction().begin();
            T entity = entityManager.find(objClass, id);
            if (entity != null) {
                entityManager.remove(entity);
                entityManager.getTransaction().commit();
                return entity; // Return the deleted entity
            } else {
                entityManager.getTransaction().rollback(); // Rollback transaction if entity not found
                throw new ResourceException("Not Found " , Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            entityManager.getTransaction().rollback(); // Rollback transaction in case of exception
//            throw new RuntimeException("Failed to delete the item with ID: " + id, e);
//             throw new ResourceException("Failed to delete the item with ID: "+id , Response.Status.EXPECTATION_FAILED);
            throw new ResourceException("no record with this id" , Response.Status.NOT_FOUND);
        }
    }

}
