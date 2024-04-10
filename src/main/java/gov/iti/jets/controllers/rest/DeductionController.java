package gov.iti.jets.controllers.rest;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.DeductionDto;
import gov.iti.jets.services.DeductionServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;

@Path("/deductions")
public class DeductionController {

    DeductionServices deductionServices = new DeductionServices();

    // http://localhost:9090/HR_API/webapi/deductions
    @GET
    @Produces("application/json")
    public Response getAllDeductions() {
        try {
            List<DeductionDto> deductionDtoList = deductionServices.getAllDeductions();
            return Response.ok(deductionDtoList).build();
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve all deductions: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:9090/HR_API/webapi/deductions/amount
    @GET
    @Path("/amount")
    @Produces("application/json")
    public Response getAllDeductionsAmount() {
        try {
            BigDecimal amount = deductionServices.getAllDeductionsAmount();
            return Response.ok(amount).build();
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve all deductions amount: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:9090/HR_API/webapi/deductions/add
    @POST
    @Consumes("application/json")
    public Response addDeductionToEmployee(DeductionDto request) {
        try {
            boolean added = deductionServices.addDeductionToEmployee(request.getEmpId(), request.getAmount(), request.getReason(), request.getDate());
            if (added) {
                String msg = "deduction added successfully";
                return Response.ok(msg).build();
            } else {
                throw new ResourceException("Failed to add deduction", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            throw new ResourceException("Failed to add deduction: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:9090/HR_API/webapi/deductions/employee/{employeeId}
    @GET
    @Path("/{employeeId}")
    @Produces("application/json")
    public Response retrieveAllDeductionsForEmployee(@PathParam("employeeId") int employeeId) {
        try {
            List<DeductionDto> deductionDtoList = deductionServices.retrieveAllDeductionsForEmployee(employeeId);
            return Response.ok(deductionDtoList).build();
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve deductions for employee: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    @GET
    @Path("/amount/{employeeId}")
    @Produces("application/json")
    public Response retrieveAllDeductionsAmountForEmployee(@PathParam("employeeId") int employeeId) {
        try {
            BigDecimal amount = deductionServices.retrieveAllDeductionsAmountForEmployee(employeeId);
            return Response.ok(amount).build();
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve deductions amount for employee: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
    @DELETE
    @Path("/delete/month/{month}/year/{year}")
    public Response deleteDeductionsInMonthAndYear(@PathParam("month") int month, @PathParam("year") int year) {
        try {
            boolean deleted = deductionServices.deleteDeductionsInMonthAndYear(month, year);
            if (deleted) {
                String msg = "deductions deleted successfully";
                return Response.ok(msg).build();
            } else {
                throw new ResourceException("Failed to delete deductions for the specified month and year", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            throw new ResourceException("Failed to delete deductions for the specified month and year: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("/delete/employee/{employeeId}/month/{month}/year/{year}")
    public Response deleteDeductionsForEmployeeInMonthAndYear(@PathParam("employeeId") int employeeId, @PathParam("month") int month, @PathParam("year") int year) {
        try {
            boolean deleted = deductionServices.deleteDeductionsForEmployeeInMonthAndYear(employeeId, month, year);
            if (deleted) {
                String msg = "deductions deleted successfully";
                return Response.ok(msg).build();
            } else {
                throw new ResourceException("Failed to delete deductions for the specified employee, month, and year", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            throw new ResourceException("Failed to delete deductions for the specified employee, month, and year: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}

