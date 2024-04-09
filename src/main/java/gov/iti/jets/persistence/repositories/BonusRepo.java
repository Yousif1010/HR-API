package gov.iti.jets.persistence.repositories;

import gov.iti.jets.persistence.entities.Bonus;
import gov.iti.jets.persistence.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class BonusRepo extends CrudRepo<Bonus,Integer>{
    public BonusRepo(EntityManager entityManager) {
        super(entityManager);
    }
    public void addBonusToEmployee(int employeeId, BigDecimal amount, String reason, Date date) {
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

        Bonus bonus = new Bonus(employee, amount, reason, date);
        entityManager.getTransaction().begin();
        entityManager.persist(bonus);
        entityManager.getTransaction().commit();
    }
    public List<Bonus> retrieveAllBonusesForEmployee(int employeeId) {
        TypedQuery<Bonus> query = entityManager.createQuery(
                "SELECT b FROM Bonus b WHERE b.employee.empId = :employeeId", Bonus.class);
        query.setParameter("employeeId", employeeId);
        return query.getResultList();
    }
    public List<Bonus> retrieveAllBonusesForEmployeeInMonthAndYear(int employeeId, int month, int year) {
        TypedQuery<Bonus> query = entityManager.createQuery(
                "SELECT b FROM Bonus b WHERE b.employee.empId = :employeeId " +
                        "AND FUNCTION('MONTH', b.date) = :month " +
                        "AND FUNCTION('YEAR', b.date) = :year", Bonus.class);
        query.setParameter("employeeId", employeeId);
        query.setParameter("month", month);
        query.setParameter("year", year);
        return query.getResultList();
    }
    public List<Bonus> retrieveAllBonusesForEmployeeInYear(int employeeId, int year) {
        TypedQuery<Bonus> query = entityManager.createQuery(
                "SELECT b FROM Bonus b WHERE b.employee.empId = :employeeId " +
                        "AND FUNCTION('YEAR', b.date) = :year", Bonus.class);
        query.setParameter("employeeId", employeeId);
        query.setParameter("year", year);
        return query.getResultList();
    }
    public void deleteBonusesInMonthAndYear(int month, int year) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Bonus b WHERE FUNCTION('MONTH', b.date) = :month " +
                        "AND FUNCTION('YEAR', b.date) = :year")
                .setParameter("month", month)
                .setParameter("year", year)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
    public void deleteBonusesInYear(int year) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Bonus b WHERE FUNCTION('YEAR', b.date) = :year")
                .setParameter("year", year)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
    public void deleteAllBonuses() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Bonus").executeUpdate();
        entityManager.getTransaction().commit();
    }
    public void deleteBonusesForEmployeeInMonthAndYear(int employeeId, int month, int year) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Bonus b WHERE b.employee.empId = :employeeId " +
                        "AND FUNCTION('MONTH', b.date) = :month " +
                        "AND FUNCTION('YEAR', b.date) = :year")
                .setParameter("employeeId", employeeId)
                .setParameter("month", month)
                .setParameter("year", year)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }
    public void deleteBonusesForEmployeeInYear(int employeeId, int year) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Bonus b WHERE b.employee.empId = :employeeId " +
                        "AND FUNCTION('YEAR', b.date) = :year")
                .setParameter("employeeId", employeeId)
                .setParameter("year", year)
                .executeUpdate();
        entityManager.getTransaction().commit();
    }

}
