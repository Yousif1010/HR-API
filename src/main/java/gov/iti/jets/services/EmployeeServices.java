package gov.iti.jets.services;

import gov.iti.jets.persistence.connection.JPAManager;
import gov.iti.jets.persistence.dtos.EmployeeDto;
import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.mappers.EmployeeMapper;
import gov.iti.jets.persistence.repositories.EmployeeRepo;
import jakarta.persistence.EntityManager;
import java.util.List;

public class EmployeeServices {

    private final EmployeeRepo employeeRepo;

    public EmployeeServices() {
        EntityManager em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        employeeRepo = new EmployeeRepo(em);
    }

        public List<EmployeeDto> getAllEmployees() {
        List<Employee> employeesList = employeeRepo.findAll(Employee.class).get();
        List<EmployeeDto> employeesDtoList = EmployeeMapper.INSTANCE.toEmployeeDtoList(employeesList);
        return employeesDtoList;
    }

}
