package gov.iti.jets.controllers.soap.services;


import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.EmployeeDto;
import gov.iti.jets.services.EmployeeServices;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.Response;


import java.util.List;

@WebService
public class EmployeeManagement {

    EmployeeServices employeeServices = new EmployeeServices();

    @WebMethod
    @WebResult(name = "employees")
    public List<EmployeeDto> getAllEmployeesWithoutPagination() {
        System.out.println("getAllEmployeesWithoutPagination");
        try {
            return employeeServices.getAllEmployeesWithoutPagination();
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve employees: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @WebMethod
    @WebResult(name = "count")
    public long getCountOfEmployeesPages(@WebParam(name = "perPage") int employeePerPage) {
        try {
            return employeeServices.getCountOfEmployeesPages(employeePerPage);
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve employees: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @WebMethod
    @WebResult(name = "employees")
    public List<EmployeeDto> getAllEmployeesWithPagination(
            @WebParam(name = "perPage") int employeesPerPage,
            @WebParam(name = "page") int page) {
        try {
            return employeeServices.getAllEmployeesWithPagination(employeesPerPage, page);
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve employees: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @WebMethod
    public String updateEmployee(
            @WebParam(name = "empId") int empId,
            @WebParam(name = "employeeDto") EmployeeDto employeeDto) {
        try {
            boolean updated = employeeServices.updateEmployee(empId, employeeDto);
            if (updated) {
                return "Employee updated successfully";
            } else {
                throw new ResourceException("Failed to retrieve employees: ", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve employees: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @WebMethod
    public String addEmployee(@WebParam(name = "employeeDto") EmployeeDto employeeDto) {
        try {
            boolean added = employeeServices.addEmployee(employeeDto);
            if (added) {
                return "Employee added successfully";
            } else {
                throw new ResourceException("Failed to retrieve employees: ", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (ResourceException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve employees: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
