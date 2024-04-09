package gov.iti.jets.persistence.repositories;

import gov.iti.jets.persistence.entities.Deduction;
import gov.iti.jets.persistence.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class DeductionRepo extends CrudRepo<Deduction,Integer>{
    public DeductionRepo(EntityManager entityManager) {
        super(entityManager);
    }
    public void addDeductionToEmployee(int employeeId, BigDecimal amount, String reason, Date date) {
        Employee employee = entityManager.find(Employee.class, employeeId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found with id: " + employeeId);
        }

        // Set default values if reason or date are not provided
        if (reason == null) {
            reason = "No reason specified";
        }
        if (date == null) {
            date = new Date(); // Default date is today's date
        }

        Deduction deduction = new Deduction(employee, amount, reason, date);
        entityManager.getTransaction().begin();
        entityManager.persist(deduction);
        entityManager.getTransaction().commit();
    }
    public List<Deduction> retrieveAllDeductionsForEmployee(int employeeId) {
        TypedQuery<Deduction> query = entityManager.createQuery(
                "SELECT d FROM Deduction d WHERE d.employee.empId = :employeeId", Deduction.class);
        query.setParameter("employeeId", employeeId);
        return query.getResultList();
    }
    public List<Deduction> retrieveAllDeductionsForEmployeeInMonthAndYear(int employeeId, int month, int year) {
        TypedQuery<Deduction> query = entityManager.createQuery(
                "SELECT d FROM Deduction d WHERE d.employee.empId = :employeeId " +
                        "AND FUNCTION('MONTH', d.date) = :month " +
                        "AND FUNCTION('YEAR', d.date) = :year", Deduction.class);
        query.setParameter("employeeId", employeeId);
        query.setParameter("month", month);
        query.setParameter("year", year);
        return query.getResultList();
    }
    public List<Deduction> retrieveAllDeductionsForEmployeeInYear(int employeeId, int year) {
        TypedQuery<Deduction> query = entityManager.createQuery(
                "SELECT d FROM Deduction d WHERE d.employee.empId = :employeeId " +
                        "AND FUNCTION('YEAR', d.date) = :year", Deduction.class);
        query.setParameter("employeeId", employeeId);
        query.setParameter("year", year);
        return query.getResultList();
    }
    public void deleteDeductionsInMonthAndYear(int month, int year) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Deduction d WHERE FUNCTION('MONTH', d.date) = :month " +
                        "AND FUNCTION('YEAR', d.date) = :year")
                .setParameter("month", month)
                .setParameter("year", year)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
    public void deleteDeductionsInYear(int year) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Deduction d WHERE FUNCTION('YEAR', d.date) = :year")
                .setParameter("year", year)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
    public void deleteAllDeductions() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Deduction").executeUpdate();
        entityManager.getTransaction().commit();
    }
    public void deleteDeductionsForEmployeeInMonthAndYear(int employeeId, int month, int year) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Deduction d WHERE d.employee.empId = :employeeId " +
                        "AND FUNCTION('MONTH', d.date) = :month " +
                        "AND FUNCTION('YEAR', d.date) = :year")
                .setParameter("employeeId", employeeId)
                .setParameter("month", month)
                .setParameter("year", year)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
    public void deleteDeductionsForEmployeeInYear(int employeeId, int year) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Deduction d WHERE d.employee.empId = :employeeId " +
                        "AND FUNCTION('YEAR', d.date) = :year")
                .setParameter("employeeId", employeeId)
                .setParameter("year", year)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

}
