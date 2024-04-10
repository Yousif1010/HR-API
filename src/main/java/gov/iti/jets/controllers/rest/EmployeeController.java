package gov.iti.jets.controllers.rest;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.EmployeeDto;
import gov.iti.jets.services.EmployeeServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/employees")
public class EmployeeController {

    EmployeeServices employeeServices = new EmployeeServices();

    @GET
    @Produces("application/json")
    public Response getAllEmployeesWithoutPagination() {
        System.out.println("getAllEmployeesWithoutPagination");
        try {
            List<EmployeeDto> employees = employeeServices.getAllEmployeesWithoutPagination();
            return Response.ok(employees).build();
        }catch(ResourceException e){
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve employees: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    // Get count of employee pages
    @GET
    @Path("/countOfPages")
    @Produces("application/json")
    public Response getCountOfEmployeesPages(@QueryParam("perPage") int employeePerPage) {
        try {
            long count = employeeServices.getCountOfEmployeesPages(employeePerPage);
            return Response.ok(count).build();
        }catch(ResourceException e){
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to get employee pages count: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    // Get employees with pagination
    @GET
    @Path("/pagination")
    @Produces("application/json")
    public Response getAllEmployeesWithPagination(@QueryParam("perPage") int employeesPerPage, @QueryParam("page") int page) {
        try {
            List<EmployeeDto> employees = employeeServices.getAllEmployeesWithPagination(employeesPerPage, page);
            return Response.ok(employees).build();
        }catch(ResourceException e){
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve employees with pagination: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    // Update an employee
    @PUT
    @Path("/{empId}")
    @Consumes("application/json")
    public Response updateEmployee(@PathParam("empId") int empId,EmployeeDto employeeDto) {
        try {
            boolean updated = employeeServices.updateEmployee(empId,employeeDto);
            if (updated) {
                String msg = "Employee updated successfully";
                return Response.ok(msg).build();
            } else {
                throw new ResourceException("Failed to update employee", Response.Status.INTERNAL_SERVER_ERROR);
            }
        }catch(ResourceException e){
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to update employee: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    @POST
    @Consumes("application/json")
    public Response addEmployee(EmployeeDto employeeDto) {

        try {
            boolean added = employeeServices.addEmployee(employeeDto);
            if (added) {
                String msg = "Employee added successfully";
                return Response.ok(msg).build();
            } else {
                throw new ResourceException("Failed to add employee", Response.Status.INTERNAL_SERVER_ERROR);
            }
        }catch(ResourceException e){
            throw e;
        } catch (Exception e) {
            throw new ResourceException("Failed to add employee: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}

