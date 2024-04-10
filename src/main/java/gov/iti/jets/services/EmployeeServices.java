package gov.iti.jets.services;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.connection.JPAManager;
import gov.iti.jets.persistence.dtos.EmployeeDto;
import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.mappers.EmployeeMapper;
import gov.iti.jets.persistence.repositories.EmployeeRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

public class EmployeeServices {

    private final EmployeeRepo employeeRepo;

    private static EntityManager em;

    public EmployeeServices() {
        em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        employeeRepo = new EmployeeRepo(em);
    }

    public List<EmployeeDto> getAllEmployeesWithoutPagination() {
        List<Employee> employeesList = employeeRepo.findAll(Employee.class).get();
        List<EmployeeDto> employeesDtoList = EmployeeMapper.INSTANCE.toEmployeeDtoList(employeesList);
        return employeesDtoList;
    }
    public long getCountOfEmployeesPages(int employeePerPage){
        long count = employeeRepo.getPagesCountForEmployee(employeePerPage);
        return count;
    }
    public List<EmployeeDto> getAllEmployeesWithPagination(int employeesPerPage, int page){
        List<Employee> employeesList = employeeRepo.getEmployeesByPage(page,employeesPerPage);
        if(employeesList.isEmpty()){
            System.out.println("page is not valid");
            throw new ResourceException("page is not valid", Response.Status.NOT_FOUND);
        }
        List<EmployeeDto> employeesDtoList = EmployeeMapper.INSTANCE.toEmployeeDtoList(employeesList);
        return employeesDtoList;
    }

    public boolean updateEmployee(int empId,EmployeeDto employeeDto){

        Optional<Employee> optionalEmployee=employeeRepo.findById(Employee.class,empId);
        if(!optionalEmployee.isPresent()){
            throw new ResourceException("employee not found", Response.Status.NOT_FOUND);
        }
        Employee updatedEmployee = EmployeeMapper.INSTANCE.toEmployee(employeeDto);
        EmployeeRepo employeeRepo = new EmployeeRepo(em);
        Employee employee = optionalEmployee.get();
        employee.updateEmployee(updatedEmployee);
        employeeRepo.update(employee);
        return true;
    }

    public boolean addEmployee(EmployeeDto employeeDto){
        if(employeeDto.getEmpId()!=null){
            throw new RuntimeException("employee id must be null");
        }
//        if(employeeDto.getHireDate()!=null){
//            throw new RuntimeException("hire date must be null");
//        }
        Employee employee = EmployeeMapper.INSTANCE.toEmployee(employeeDto);
        EmployeeRepo employeeRepo = new EmployeeRepo(em);
        employeeRepo.save(employee);
        return true;
    }

    public boolean deleteEmployee(int empId){
        EmployeeRepo employeeRepo = new EmployeeRepo(em);
        Optional<Employee> optionalEmployee=employeeRepo.findById(Employee.class, empId);
        if(!optionalEmployee.isPresent()){
            throw new RuntimeException("employee not found");
        }
        Employee employee = optionalEmployee.get();
        employeeRepo.delete(employee);
        return true;
    }
    public List<EmployeeDto> getEmployeesWithSameTitle(int titleId){
        EmployeeRepo  employeeRepo = new EmployeeRepo(em);
        List<Employee> employees = employeeRepo.getEmployeesWithSameTitle(titleId);
        List<EmployeeDto> employeesDto = EmployeeMapper.INSTANCE.toEmployeeDtoList(employees);
        return employeesDto;
    }

}
