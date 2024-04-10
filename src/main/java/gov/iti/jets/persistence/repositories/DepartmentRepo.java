package gov.iti.jets.persistence.repositories;

import gov.iti.jets.persistence.entities.Department;
import gov.iti.jets.persistence.entities.Employee;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class DepartmentRepo extends CrudRepo<Department,Integer>{
    public DepartmentRepo(EntityManager entityManager) {
        super(entityManager);
    }

    public Optional<Employee> getManager(int departmentId){
        return Optional.ofNullable(entityManager.find(Employee.class,departmentId));
    }

}
