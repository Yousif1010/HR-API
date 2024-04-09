package gov.iti.jets.persistence.repositories;

import gov.iti.jets.persistence.entities.Attendance;
import gov.iti.jets.persistence.entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
  default work hours in the company from 8 am : 4 pm (16:00:00)
 */
public class AttendanceRepo extends CrudRepo<Attendance,Integer>{
    public AttendanceRepo(EntityManager entityManager) {
        super(entityManager);
    }
    //find all attendances is done
    public Optional<List<Attendance>> findAllAttendanceByEmployeeId(int employeeId) {
        String jpql = "SELECT a FROM Attendance a WHERE a.employee.empId = :employeeId";
        TypedQuery<Attendance> query = entityManager.createQuery(jpql, Attendance.class);
        query.setParameter("employeeId", employeeId);
        List<Attendance> resultList = query.getResultList();
        return Optional.ofNullable(resultList);
    }
    public Optional<List<Attendance>> findAllAttendanceInSpecificDay(Date date) {
        String jpql = "SELECT a FROM Attendance a WHERE a.date = :date";
        TypedQuery<Attendance> query = entityManager.createQuery(jpql, Attendance.class);
        query.setParameter("date", date);

        List<Attendance> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return Optional.empty(); // No attendance records found for the given date
        } else {
            return Optional.of(resultList); // Return the list of attendance records
        }
    }
    public Optional<Attendance> findAttendanceByEmployeeAndDate(Employee employee, Date date) {
        String jpql = "SELECT a FROM Attendance a WHERE a.employee = :employee AND a.date = :date";
        TypedQuery<Attendance> query = entityManager.createQuery(jpql, Attendance.class);
        query.setParameter("employee", employee);
        query.setParameter("date", date);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    public Optional<Attendance> findAttendanceByEmployeeIdAndDate(Integer employeeId, Date date) {
        String jpql = "SELECT a FROM Attendance a WHERE a.employee.empId = :employeeId AND a.date = :date";
        TypedQuery<Attendance> query = entityManager.createQuery(jpql, Attendance.class);
        query.setParameter("employeeId", employeeId);
        query.setParameter("date", date);
        try {
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    public Optional<List<Attendance>> findAllAttendanceInSpecificMonth(Date date) {
        // Create calendar instance and set it to the provided date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Set the calendar to the first day of the month
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        // Set the calendar to the last day of the month
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date endDate = calendar.getTime();

        // Define the JPQL query to retrieve attendance records for the specific month
        String jpql = "SELECT a FROM Attendance a WHERE a.date BETWEEN :startDate AND :endDate";
        TypedQuery<Attendance> query = entityManager.createQuery(jpql, Attendance.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        // Execute the query and return the result list wrapped in an Optional
        List<Attendance> resultList = query.getResultList();
        return Optional.ofNullable(resultList);
    }
    public Optional<List<Attendance>> findAttendanceByEmployeeAndSpecificMonth(int employeeId, Date date) {
        // Create calendar instance and set it to the provided date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // Set the calendar to the first day of the month
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        // Set the calendar to the last day of the month
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date endDate = calendar.getTime();

        // Define the JPQL query to retrieve attendance records for the specific employee and month
        String jpql = "SELECT a FROM Attendance a WHERE a.employee.empId = :employeeId " +
                "AND a.date BETWEEN :startDate AND :endDate";
        TypedQuery<Attendance> query = entityManager.createQuery(jpql, Attendance.class);
        query.setParameter("employeeId", employeeId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        // Execute the query and return the result list wrapped in an Optional
        List<Attendance> resultList = query.getResultList();
        return Optional.ofNullable(resultList);
    }
    public Optional<List<Attendance>> findAllAttendanceInSpecificYear(int year) {
        // Create calendar instance and set it to the first day of the specified year
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        // Set the calendar to the last day of the specified year
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        Date endDate = calendar.getTime();

        // Define the JPQL query to retrieve attendance records for the specific year
        String jpql = "SELECT a FROM Attendance a WHERE a.date BETWEEN :startDate AND :endDate";
        TypedQuery<Attendance> query = entityManager.createQuery(jpql, Attendance.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);

        // Execute the query and return the result list wrapped in an Optional
        List<Attendance> resultList = query.getResultList();
        return Optional.ofNullable(resultList);
    }
    public Optional<List<Attendance>> findAllAttendanceInSpecificYearForEmployee(int year, int employeeId) {
        // Create calendar instance and set it to the first day of the specified year
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date startDate = calendar.getTime();

        // Set the calendar to the last day of the specified year
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        Date endDate = calendar.getTime();

        // Define the JPQL query to retrieve attendance records for the specific year and employee
        String jpql = "SELECT a FROM Attendance a WHERE a.date BETWEEN :startDate AND :endDate AND a.employee.empId = :employeeId";
        TypedQuery<Attendance> query = entityManager.createQuery(jpql, Attendance.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        query.setParameter("employeeId", employeeId);

        // Execute the query and return the result list wrapped in an Optional
        List<Attendance> resultList = query.getResultList();
        return Optional.ofNullable(resultList);
    }
    public void deleteAllRecords() {
        entityManager.createQuery("DELETE FROM Attendance").executeUpdate();
    }
    public void deleteAllRecordsForSpecificEmployee(int employeeId) {
        entityManager.createQuery("DELETE FROM Attendance a WHERE a.employee.empId = :employeeId")
                .setParameter("employeeId", employeeId)
                .executeUpdate();
    }
    public void deleteAllRecordsInSpecificDay(Date date) {
        entityManager.createQuery("DELETE FROM Attendance a WHERE a.date = :date")
                .setParameter("date", date)
                .executeUpdate();
    }
    public void deleteAllRecordsInSpecificDayForSpecificEmployee(int employeeId, Date date) {
        entityManager.createQuery("DELETE FROM Attendance a WHERE a.employee.empId = :employeeId AND a.date = :date")
                .setParameter("employeeId", employeeId)
                .setParameter("date", date)
                .executeUpdate();
    }
    public void deleteRecordsInSpecificMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1); // Set calendar to the first day of the specified month
        Date startDate = calendar.getTime();

        calendar.add(Calendar.MONTH, 1); // Set calendar to the first day of the next month
        calendar.add(Calendar.DAY_OF_MONTH, -1); // Set calendar to the last day of the specified month
        Date endDate = calendar.getTime();

        entityManager.createQuery("DELETE FROM Attendance a WHERE a.date BETWEEN :startDate AND :endDate")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .executeUpdate();
    }
    public void deleteRecordsInSpecificMonthForSpecificEmployee(int employeeId, int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1); // Set calendar to the first day of the specified month
        Date startDate = calendar.getTime();

        calendar.add(Calendar.MONTH, 1); // Set calendar to the first day of the next month
        calendar.add(Calendar.DAY_OF_MONTH, -1); // Set calendar to the last day of the specified month
        Date endDate = calendar.getTime();

        entityManager.createQuery("DELETE FROM Attendance a WHERE a.employee.empId = :employeeId " +
                        "AND a.date BETWEEN :startDate AND :endDate")
                .setParameter("employeeId", employeeId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .executeUpdate();
    }
    public void deleteRecordsInSpecificYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.JANUARY, 1); // Set calendar to the first day of the specified year
        Date startDate = calendar.getTime();

        calendar.set(year, Calendar.DECEMBER, 31); // Set calendar to the last day of the specified year
        Date endDate = calendar.getTime();

        entityManager.createQuery("DELETE FROM Attendance a WHERE a.date BETWEEN :startDate AND :endDate")
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .executeUpdate();
    }
    public void deleteRecordsInSpecificYearForSpecificEmployee(int employeeId, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.JANUARY, 1); // Set calendar to the first day of the specified year
        Date startDate = calendar.getTime();

        calendar.set(year, Calendar.DECEMBER, 31); // Set calendar to the last day of the specified year
        Date endDate = calendar.getTime();

        entityManager.createQuery("DELETE FROM Attendance a WHERE a.employee.empId = :employeeId " +
                        "AND a.date BETWEEN :startDate AND :endDate")
                .setParameter("employeeId", employeeId)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .executeUpdate();
    }
}
