package gov.iti.jets.controllers;

import gov.iti.jets.persistence.dtos.EmployeeDto;
import gov.iti.jets.services.EmployeeServices;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.List;

@Path("/employees")
public class EmployeeController {

    EmployeeServices employeeServices = new EmployeeServices();

    // http://localhost:9090/HR_API/webapi/employees
    @GET
    public List<EmployeeDto> getAllEmployees(){
        return employeeServices.getAllEmployees();
    }
}

