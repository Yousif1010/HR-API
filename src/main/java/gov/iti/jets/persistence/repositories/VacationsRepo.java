package gov.iti.jets.persistence.repositories;

import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.entities.Vacations;
import gov.iti.jets.persistence.enums.VacationsStatus;
import gov.iti.jets.persistence.enums.VacationsType;
import jakarta.persistence.EntityManager;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

import java.util.*;
import java.util.stream.Collectors;

public class VacationsRepo extends CrudRepo<Vacations,Integer>{
    public VacationsRepo(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Vacations> save(Vacations entity) {
        entity.setStatus(VacationsStatus.PENDING);
        return super.save(entity);
    }
    public Optional<Vacations> acceptVacation(int vacationId){
        String jpql = "SELECT v FROM Vacations v WHERE v.vacationId = :vacationId";
        TypedQuery<Vacations> query = entityManager.createQuery(jpql, Vacations.class);
        query.setParameter("vacationId", vacationId);
        Vacations result = query.getSingleResult();
        result.setStatus(VacationsStatus.APPROVED);
        entityManager.getTransaction().begin();
        result = entityManager.merge(result); // from application to database
        entityManager.getTransaction().commit();
        // Ensure the updated entity is synchronized with the database
        entityManager.refresh(result);
        return Optional.ofNullable(result);
    }
    public Optional<Vacations> refuseVacation(int vacationId){
        String jpql = "SELECT v FROM Vacations v WHERE v.vacationId = :vacationId";
        TypedQuery<Vacations> query = entityManager.createQuery(jpql, Vacations.class);
        query.setParameter("vacationId", vacationId);
        Vacations result = query.getSingleResult();
        result.setStatus(VacationsStatus.REFUSED);
        entityManager.getTransaction().begin();
        result = entityManager.merge(result); // from application to database
        entityManager.getTransaction().commit();
        // Ensure the updated entity is synchronized with the database
        entityManager.refresh(result);
        return Optional.ofNullable(result);
    }
    public List<Vacations> findFilteredVacationsForEmployee(int employeeId, Date startDate, Date endDate,
                                                            VacationsType type, VacationsStatus status) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Vacations> criteriaQuery = criteriaBuilder.createQuery(Vacations.class);
        Root<Vacations> root = criteriaQuery.from(Vacations.class);

        // Join with Employee entity
        Join<Vacations, Employee> employeeJoin = root.join("employee", JoinType.INNER);

        // Create predicates based on the provided parameters
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(criteriaBuilder.equal(employeeJoin.get("empId"), employeeId));
        predicates.add(startDate != null ? criteriaBuilder.greaterThanOrEqualTo(root.get("startDate"), startDate) : null);
        predicates.add(endDate != null ? criteriaBuilder.lessThanOrEqualTo(root.get("endDate"), endDate) : null);
        predicates.add(type != null ? criteriaBuilder.equal(root.get("type"), type) : null);
        predicates.add(status != null ? criteriaBuilder.equal(root.get("status"), status) : null);

        // Combine predicates into a list
        List<Predicate> nonNullPredicates = predicates.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // Combine non-null predicates into a conjunction
        Predicate conjunction = criteriaBuilder.and(nonNullPredicates.toArray(new Predicate[0]));

        // Apply conjunction to the WHERE clause
        criteriaQuery.where(conjunction);

        // Execute the query
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

}
