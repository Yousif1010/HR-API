package gov.iti.jets.persistence.repositories;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.EmployeeDto;
import gov.iti.jets.persistence.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.Date;
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
    public long getPagesCountForEmployee(int employeePerPage) {
        try {
            String jpql = "SELECT count(e.id) FROM Employee e";
            Query query = entityManager.createQuery(jpql);
            long countResult = (long) query.getSingleResult();
            long finalPage = countResult % employeePerPage > 0 ? 1 : 0;
            return (countResult / employeePerPage + finalPage);
        } catch (Exception e) {
            throw new ResourceException("Failed to get employee pages count: " , Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    public List<Employee> getEmployeesByPage(int page, int employeesPerPage){
        try {
        long pagesCount = getPagesCountForEmployee(employeesPerPage);
        if(page > pagesCount){
            System.out.println("page is not valid");
            return null;
        }
        String jpql = "SELECT e FROM Employee e";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        query.setFirstResult((page-1)*employeesPerPage);
        query.setMaxResults(employeesPerPage);
        return query.getResultList();
        } catch (Exception e) {
            throw new ResourceException("Failed to get employee per page " , Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    public boolean updateEmployeeFirstName(int employeeId, String firstName){
        String jpql = "UPDATE Employee e SET e.firstName = :firstName WHERE e.id = :employeeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("firstName", firstName);
        query.setParameter("employeeId", employeeId);
        return query.executeUpdate() > 0;
    }
    public boolean updateEmployeeLastName(int employeeId, String lastName){
        String jpql = "UPDATE Employee e SET e.lastName = :lastName WHERE e.id = :employeeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("lastName", lastName);
        query.setParameter("employeeId", employeeId);
        return query.executeUpdate() > 0;
    }
    public boolean updateEmployeeDob(int employeeId, Date dob){
        String jpql = "UPDATE Employee e SET e.dob = :dob WHERE e.id = :employeeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("dob", dob);
        query.setParameter("employeeId", employeeId);
        return query.executeUpdate() > 0;
    }
    public boolean updateEmployeeTitle(int employeeId, int titleId){
        String jpql = "UPDATE Employee e SET e.titleId = :titleId WHERE e.id = :employeeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("titleId", titleId);
        query.setParameter("employeeId", employeeId);
        return query.executeUpdate() > 0;
    }
    public boolean updateEmployeeEmail(int employeeId, String email){
        String jpql = "UPDATE Employee e SET e.email = :email WHERE e.id = :employeeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("email", email);
        query.setParameter("employeeId", employeeId);
        return query.executeUpdate() > 0;
    }
    public boolean updateEmployeeHireDate(int employeeId, Date hireDate){
        String jpql = "UPDATE Employee e SET e.hireDate = :hireDate WHERE e.id = :employeeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("hireDate", hireDate);
        query.setParameter("employeeId", employeeId);
        return query.executeUpdate() > 0;
    }
    public boolean updateEmployeeAddressZone(int employeeId, String addressZone){
        String jpql = "UPDATE Employee e SET e.addressZone = :addressZone WHERE e.id = :employeeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("addressZone", addressZone);
        query.setParameter("employeeId", employeeId);
        return query.executeUpdate() > 0;
    }
    public boolean updateEmployeeAddressCity(int employeeId, String addressCity){
        String jpql = "UPDATE Employee e SET e.addressCity = :addressCity WHERE e.id = :employeeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("addressCity", addressCity);
        query.setParameter("employeeId", employeeId);
        return query.executeUpdate() > 0;
    }
    public boolean updateEmployeeAddressCountry(int employeeId, String addressCountry){
        String jpql = "UPDATE Employee e SET e.addressCountry = :addressCountry WHERE e.id = :employeeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("addressCountry", addressCountry);
        query.setParameter("employeeId", employeeId);
        return query.executeUpdate() > 0;
    }
    public boolean updateEmployeeSalary(int employeeId, BigDecimal salary){
        String jpql = "UPDATE Employee e SET e.salary = :salary WHERE e.id = :employeeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("salary", salary);
        query.setParameter("employeeId", employeeId);
        return query.executeUpdate() > 0;
    }
    public boolean updateEmployeePhone(int employeeId, String phone){
        String jpql = "UPDATE Employee e SET e.phone = :phone WHERE e.id = :employeeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("phone", phone);
        query.setParameter("employeeId", employeeId);
        return query.executeUpdate() > 0;
    }
    public boolean updateEmployeeManager(int employeeId, int managerId){
        String jpql = "UPDATE Employee e SET e.managerId = :managerId WHERE e.id = :employeeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("managerId", managerId);
        query.setParameter("employeeId", employeeId);
        return query.executeUpdate() > 0;
    }
    public boolean updateEmployeeDepartment(int employeeId, int departmentId){
        String jpql = "UPDATE Employee e SET e.departmentId = :departmentId WHERE e.id = :employeeId";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("departmentId", departmentId);
        query.setParameter("employeeId", employeeId);
        return query.executeUpdate() > 0;
    }
    public Employee getEmployeeByEmail(String email){
        String jpql = "SELECT e FROM Employee e WHERE e.email = :email";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }
    public Employee getEmployeeByPhone(String phone){
        String jpql = "SELECT e FROM Employee e WHERE e.phone = :phone";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        query.setParameter("phone", phone);
        return query.getSingleResult();
    }
    public List<Employee> getEmployeesWithSameTitle(int titleId){
        String jpql = "SELECT e FROM Employee e WHERE e.titleId = :titleId";
        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        query.setParameter("titleId", titleId);
        List<Employee> employees = query.getResultList();

        if (employees.isEmpty()) {
            throw new ResourceException("No employees found with the given titleId", Response.Status.NOT_FOUND);
        }

        return employees;
    }


}
