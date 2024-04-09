package gov.iti.jets.services;

import gov.iti.jets.persistence.connection.JPAManager;
import gov.iti.jets.persistence.dtos.DepartmentDto;
import gov.iti.jets.persistence.entities.Department;
import gov.iti.jets.persistence.entities.Employee;
import gov.iti.jets.persistence.mappers.DepartmentMappper;
import gov.iti.jets.persistence.repositories.DepartmentRepo;
import gov.iti.jets.persistence.repositories.EmployeeRepo;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class DepartmentServices {
    private final DepartmentRepo departmentRepo;
    private static EntityManager em;
    public DepartmentServices() {
        em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        departmentRepo = new DepartmentRepo(em);
    }

    public List<DepartmentDto> getAllDepartments() {
        List<Department> departmentsList = departmentRepo.findAll(Department.class).get();
        List<DepartmentDto> departmentsDtoList = DepartmentMappper.INSTANCE.toDepartmentDtoList(departmentsList);
        return departmentsDtoList;
    }

    public boolean addDepartment(String departmentName,int managerId){
        EmployeeRepo  employeeRepo = new EmployeeRepo(em);
        Employee manager = employeeRepo.findById(Employee.class, managerId).get();
        if(manager == null)
        {
            System.out.println("Manager not found in the database");
            return false;
        }
        Department department = new Department(manager,departmentName);
        departmentRepo.save(department);
        return true;
    }
    public boolean addDepartmentWithoutManager(String departmentName){
        Department department = new Department(departmentName);
        departmentRepo.save(department);
        return true;
    }

    public boolean deleteDepartment(int id) {
        Optional<Department> optionalDepartment = departmentRepo.findById(Department.class, id);
        if (optionalDepartment.isEmpty()) {
            System.out.println("Department not found in the database");
            return false;
        }
        Department department = optionalDepartment.get();

        // Retrieve all employees of the department
        EmployeeRepo  employeeRepo = new EmployeeRepo(em);
        List<Employee> employees = employeeRepo.findEmployeesByDepartmentId(id);

        // Set isDeleted to true for all employees
        for (Employee employee : employees) {
            employee.markAsDeleted();
            employeeRepo.update(employee);
        }
        departmentRepo.delete(department);
        return true;
    }

}
