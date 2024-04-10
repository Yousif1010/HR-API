package gov.iti.jets.services;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.connection.JPAManager;
import gov.iti.jets.persistence.dtos.AttendanceDto;
import gov.iti.jets.persistence.entities.Attendance;
import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.enums.AttendanceStatus;
import gov.iti.jets.persistence.mappers.AttendanceMapper;
import gov.iti.jets.persistence.repositories.AttendanceRepo;
import gov.iti.jets.persistence.repositories.EmployeeRepo;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class AttendanceServices {
    private final AttendanceRepo attendanceRepo ;
    private static EntityManager em = null;

    public AttendanceServices() {
        em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        attendanceRepo = new AttendanceRepo(em);
    }
    public Date parseDate(int year,int month,int day) {
        int y = year-1900;
        int m = month-1;
        return new Date(y, m, day);
    }
    public boolean enterFingerprintForAttendanceRegistration(int empId){
        EmployeeRepo employeeRepo = new EmployeeRepo(em);
        Optional<Employee> employee = employeeRepo.findById(Employee.class, empId);

        if (!employee.isPresent()) {
            System.out.println("Employee not found in the database");
            throw new ResourceException("Employee not found in the database", Response.Status.NOT_FOUND);
        }

        AttendanceRepo attendanceRepo = new AttendanceRepo(em);


        // Check if attendance already exists for the employee on the current date
        Optional<Attendance> existingAttendance =
                attendanceRepo.findAttendanceByEmployeeAndDate(employee.get(), new Date());

        System.out.println(existingAttendance);

        if (existingAttendance.isPresent()) {
            System.out.println("Attendance already recorded for the employee on the current date");
            throw new ResourceException("Attendance already recorded for the employee on the current date", Response.Status.CONFLICT);

        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // Set calendar to current day

        // Check if the current time is after 4:00 PM
        if (calendar.get(Calendar.HOUR_OF_DAY) >= 16) {
            System.out.println("Fingerprint entered after 4:00 PM");
            throw new ResourceException("Fingerprint entered after 4:00 PM", Response.Status.CONFLICT);
        }

        Attendance attendance = new Attendance();
        attendance.setEmployee(employee.get());
        attendance.setDate(new Date());
        attendance.setCheckInTime(new Date()); // Time of entering the fingerprint

        // Set the time to 08:00:00
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // Compare check-in time with 08:00:00
        if (attendance.getCheckInTime().compareTo(calendar.getTime()) < 0) {
            System.out.println("Check-in time is before 8:00 AM");
            attendance.setStatus(AttendanceStatus.PRESENT);
        } else {
            System.out.println("Check-in time is after 8:00 AM");
            attendance.setStatus(AttendanceStatus.LATE);
        }

        attendanceRepo.update(attendance);

        return true;
    }
    public boolean enterFingerprintForExitingCompany(int empId) {
        EmployeeRepo employeeRepo = new EmployeeRepo(em);
        Optional<Employee> employee = employeeRepo.findById(Employee.class, empId);

        if (!employee.isPresent()) {
            System.out.println("Employee not found in the database");
            throw new ResourceException("Employee not found in the database", Response.Status.NOT_FOUND);
        }

        AttendanceRepo attendanceRepo = new AttendanceRepo(em);

        // Check if attendance already exists for the employee on the current date
        Optional<Attendance> existingAttendance =
                attendanceRepo.findAttendanceByEmployeeAndDate(employee.get(), new Date());

        if (!existingAttendance.isPresent()) {
            System.out.println("No attendance record found for the employee on the current date");
            throw new ResourceException("No attendance record found for the employee on the current date", Response.Status.NOT_FOUND);
        }

        Attendance attendance = existingAttendance.get();

        if (attendance.getStatus() == AttendanceStatus.LATE) {
            System.out.println("Employee is already marked as late");
            attendance.setCheckOutTime(new Date());
        } else {
            // Update checkOutTime with the current time
            attendance.setCheckOutTime(new Date());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date()); // Set calendar to current day

            // Set the time to 16:00:00
            calendar.set(Calendar.HOUR_OF_DAY, 16);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            // Compare check-out time with 16:00:00
            if (attendance.getCheckOutTime().compareTo(calendar.getTime()) < 0) {
                System.out.println("Check-out time is before 16:00:00");
                attendance.setStatus(AttendanceStatus.LATE);
            }
        }

        // Save the updated attendance record
        attendanceRepo.update(attendance);

        return true;
    }
    public List<AttendanceDto> getAllAttendances() {
        List<Attendance> attendanceList =attendanceRepo.findAll(Attendance.class).get();
        List<AttendanceDto> attendanceDtoList = AttendanceMapper.INSTANCE.toDtoList(attendanceList);
        return attendanceDtoList;
    }
    public List<AttendanceDto> findAllAttendanceByEmployeeId(int employeeId) {
        // Retrieve attendance records for the specified employee ID
        Optional<List<Attendance>> optionalAttendanceList = attendanceRepo.findAllAttendanceByEmployeeId(employeeId);

        // Map Attendance entities to AttendanceDto
        return optionalAttendanceList.map(attendanceList ->
                        attendanceList.stream()
                                .map(AttendanceMapper.INSTANCE::toDto)
                                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
    public List<AttendanceDto> findAllAttendanceInSpecificDay(int year,int month,int day) {
        Date date = parseDate(year, month, day);
        AttendanceRepo attendanceRepo = new AttendanceRepo(em);
        List<Attendance> attendanceList = attendanceRepo.findAllAttendanceInSpecificDay(date).get();
        List<AttendanceDto> attendanceDtoList = AttendanceMapper.INSTANCE.toDtoList(attendanceList);
        return attendanceDtoList;
    }
    public AttendanceDto getAttendnaceByEmployeeIdAndDate(int empId, int year,int month,int day) {
        Date date = parseDate(year, month, day);
        AttendanceRepo attendanceRepo = new AttendanceRepo(em);


        Optional<Attendance> attendance = attendanceRepo.findAttendanceByEmployeeIdAndDate(empId,date);
        if (attendance.isPresent()) {
            AttendanceDto attendanceDto = AttendanceMapper.INSTANCE.toDto(attendance.get());
            return attendanceDto;
        } else {
            System.out.println("No attendance record found for the employee on the given date");
            return null;
        }
    }
    public List<AttendanceDto> findAllAttendanceInSpecificMonth(int year, int month) {
        // Create a date representing the first day of the specified month
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1); // Month is 0-indexed, so subtract 1
        Date firstDayOfMonth = calendar.getTime();

        // Call the repository method to find attendance for the specific month
        Optional<List<Attendance>> optionalAttendanceList =
                attendanceRepo.findAllAttendanceInSpecificMonth(firstDayOfMonth);

        // If attendance is found, map it to DTOs
        return optionalAttendanceList.map(attendanceList ->
                        attendanceList.stream()
                                .map(AttendanceMapper.INSTANCE::toDto)
                                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
    public List<AttendanceDto> findAttendanceByEmployeeAndSpecificMonth(int year, int month, int empId) {
        // Create a date representing the first day of the specified month
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1); // Month is 0-indexed, so subtract 1
        Date firstDayOfMonth = calendar.getTime();

        // Call the repository method to find attendance for the specific employee and month
        Optional<List<Attendance>> optionalAttendanceList =
                attendanceRepo.findAttendanceByEmployeeAndSpecificMonth(empId, firstDayOfMonth);

        // If attendance is found, map it to DTOs
        return optionalAttendanceList.map(attendanceList ->
                        attendanceList.stream()
                                .map(AttendanceMapper.INSTANCE::toDto)
                                .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }
    public List<AttendanceDto> findAllAttendanceInSpecificYear(int year){
        AttendanceRepo  attendanceRepo = new AttendanceRepo(em);
        List<Attendance> attendanceList = attendanceRepo.findAllAttendanceInSpecificYear(year).get();
        List<AttendanceDto> attendanceDtoList = AttendanceMapper.INSTANCE.toDtoList(attendanceList);
        return  attendanceDtoList;
    }
    public List<AttendanceDto> findAllAttendanceInSpecificYearForEmployee(int year, int employeeId){
        AttendanceRepo  attendanceRepo = new AttendanceRepo(em);
        List<Attendance> attendanceList = attendanceRepo.findAllAttendanceInSpecificYearForEmployee(year,employeeId).get();
        List<AttendanceDto> attendanceDtoList = AttendanceMapper.INSTANCE.toDtoList(attendanceList);
        return  attendanceDtoList;
    }
    public boolean deleteAllRecords(){
         attendanceRepo.deleteAllRecords();
         return true;
    }
    public boolean deleteAllRecordsForSpecificEmployee(int employeeId){
        attendanceRepo.deleteAllRecordsForSpecificEmployee(employeeId);
        return true;
    }
    public boolean deleteAllRecordsInSpecificDay(int year,int month, int day){
        Date date = parseDate(year, month, day);
        attendanceRepo.deleteAllRecordsInSpecificDay(date);
        return true;
    }
    public boolean deleteAllRecordsInSpecificDayForSpecificEmployee(int employeeId,int year,int month, int day) {
        Date date = parseDate(year, month, day);
        attendanceRepo.deleteAllRecordsInSpecificDayForSpecificEmployee(employeeId, date);
        return true;
    }
    public boolean deleteRecordsInSpecificMonth(int year, int month){
        attendanceRepo.deleteRecordsInSpecificMonth(year,month);
        return true;
    }
    public boolean deleteRecordsInSpecificMonthForSpecificEmployee(int employeeId, int year, int month){
        attendanceRepo.deleteRecordsInSpecificMonthForSpecificEmployee(employeeId, year, month);
        return true;
    }
    public boolean deleteRecordsInSpecificYear(int year){
        attendanceRepo.deleteRecordsInSpecificYear(year);
        return true;
    }
    public boolean deleteRecordsInSpecificYearForSpecificEmployee(int employeeId, int year){
        attendanceRepo.deleteRecordsInSpecificYearForSpecificEmployee(employeeId, year);
        return true;
    }

    public boolean deleteAttendanceRecord(int attendanceId) throws Exception {
        attendanceRepo.deleteById(Attendance.class, attendanceId);
        return true;
    }

}
