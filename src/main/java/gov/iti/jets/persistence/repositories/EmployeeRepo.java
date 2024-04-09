package gov.iti.jets.persistence.repositories;

import gov.iti.jets.persistence.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeeRepo extends CrudRepo<Employee,Integer>{
    public EmployeeRepo(EntityManager entityManager) {
        super(entityManager);
    }

    public List<Employee> findEmployeesByDepartmentId(int departmentId) {
        String jpql = "SELECT e FROM Employee e WHERE e.department.departmentNo = :departmentId";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        query.setParameter("departmentId", departmentId);
        return query.getResultList();
    }
}
