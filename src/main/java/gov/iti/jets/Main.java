package gov.iti.jets;

import gov.iti.jets.persistence.connection.JPAManager;
import gov.iti.jets.persistence.dtos.AttendanceDto;
import gov.iti.jets.persistence.dtos.BonusDto;
import gov.iti.jets.persistence.dtos.EmployeeDto;
import gov.iti.jets.persistence.entities.Attendance;
import gov.iti.jets.persistence.entities.Bonus;
import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.entities.Vacations;
import gov.iti.jets.persistence.enums.VacationsStatus;
import gov.iti.jets.persistence.enums.VacationsType;
import gov.iti.jets.persistence.mappers.AttendanceMapper;
import gov.iti.jets.persistence.mappers.BonusMapper;
import gov.iti.jets.persistence.repositories.AttendanceRepo;
import gov.iti.jets.persistence.repositories.BonusRepo;
import gov.iti.jets.persistence.repositories.EmployeeRepo;
import gov.iti.jets.persistence.repositories.VacationsRepo;
import gov.iti.jets.services.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        EntityManagerFactory emf = JPAManager.INSTANCE.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

//        AttendanceRepo  attendanceRepo = new AttendanceRepo(em);
//
//        List<AttendanceDto> attendanceDtoList = AttendanceMapper.INSTANCE.toAttendanceDtoList(attendanceRepo.findAll(Attendance.class).get());
//        System.out.println(attendanceDtoList);
//
//        BonusRepo bonusRepo = new BonusRepo(em);
//
//        BonusDto bonusDtoList = BonusMapper.INSTANCE.toBonusDto(bonusRepo.findById(Bonus.class,1).get());
//        System.out.println(bonusDtoList);

//        EmployeeRepo employeeRepo = new EmployeeRepo(em);
//        Optional<Employee> employee = employeeRepo.findById(Employee.class, 3);
//        System.out.println(employee);



//        System.out.println(attendanceRepo.enterFingerprintForAttendanceRegistration(1));
//        System.out.println(attendanceRepo.enterFingerprintForExitingCompany(1));

//        attendanceServices.enterFingerprintForAttendanceRegistration(1);
//        attendanceServices.enterFingerprintForExitingCompany(1);

//        System.out.println(attendanceRepo.findAttendanceByEmployeeIdAndDate(1, new Date(2024, 4, 1)));
//        AttendanceRepo  attendanceRepo = new AttendanceRepo(em);
//          AttendanceServices  attendanceServices = new AttendanceServices();
//        System.out.println(attendanceServices.getAttendnaceByEmployeeIdAndDate(3, 2024,4,2));
//        System.out.println(attendanceServices.findAllAttendanceInSpecificDay(2024,4,1));
//        System.out.println(attendanceServices.findAllAttendanceInSpecificMonth(2024, 4));
//        System.out.println(attendanceServices.getAllAttendances());
//        System.out.println(attendanceServices.findAttendanceByEmployeeAndSpecificMonth(2024,4,3));
//        System.out.println(attendanceServices.findAllAttendanceInSpecificYear(2024));
//        System.out.println(attendanceServices.findAllAttendanceByEmployeeId(3));
//        System.out.println(attendanceServices.findAllAttendanceInSpecificYearForEmployee(2024,1));
//        BonusRepo  bonusRepo = new BonusRepo(em);

//        System.out.println(bonusRepo.retrieveAllBonusesForEmployeeInYear(1,  2024));
//        BonusServices  bonusServices = new BonusServices();
//        System.out.println(bonusServices.getAllBonusesAmount());
//          VacationsRepo   vacationsRepo = new VacationsRepo(em);
//        EmployeeRepo  employeeRepo = new EmployeeRepo(em);
//        Employee employee = employeeRepo.findById(Employee.class, 1).get();
//        Vacations  vacations = new Vacations(employee, VacationsType.ANNUAL,new Date(),new Date(2024,4,15));
//
//        vacationsRepo.save(vacations);
//        vacationsRepo.acceptVacation(1);
//        System.out.println(vacationsRepo.findById(Vacations.class, 1).get());
//        VacationsServices   vacationsServices = new VacationsServices();
//        System.out.println(vacationsServices.findFilteredVacationsForEmployee(1,null,null,null,null,null,null,null, null));
//        DepartmentServices departmentServices = new DepartmentServices();
//
//        System.out.println(departmentServices.updateDepartmentManager(1,9));

        EmployeeServices  employeeServices = new EmployeeServices();
        System.out.println(employeeServices.getAllEmployeesWithoutPagination());



    }
}
