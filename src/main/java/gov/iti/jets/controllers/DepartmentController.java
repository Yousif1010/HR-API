package gov.iti.jets.controllers;

import gov.iti.jets.persistence.dtos.DepartmentDto;
import gov.iti.jets.services.DepartmentServices;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.List;

@Path("/departments")
public class DepartmentController {

    DepartmentServices departmentServices = new DepartmentServices();

    // http://localhost:9090/HR_API/webapi/departments
    @GET
    public List<DepartmentDto> getAllDepartments(){
        return departmentServices.getAllDepartments();
    }
}

