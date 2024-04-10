package gov.iti.jets.controllers.rest;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.AttendanceDto;
import gov.iti.jets.persistence.dtos.BonusDto;
import gov.iti.jets.services.AttendanceServices;
import gov.iti.jets.services.BonusServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;

@Path("/bonuses")
public class BonusController {

    BonusServices bonusServices = new BonusServices();

    // http://localhost:9090/HR_API/webapi/bonuses
    @GET
    @Produces("application/json")
    public Response getAllBonuses() {
        try {
            List<BonusDto> bonusDtoList = bonusServices.getAllBonuses();
            return Response.ok(bonusDtoList).build();
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve all bonuses: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:9090/HR_API/webapi/bonuses/amount
    @GET
    @Path("/amount")
    @Produces("application/json")
    public Response getAllBonusesAmount() {
        try {
            BigDecimal amount = bonusServices.getAllBonusesAmount();
            return Response.ok(amount).build();
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve all bonuses amount: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:9090/HR_API/webapi/bonuses/add
    @POST
    @Consumes("application/json")
    public Response addBonusToEmployee(BonusDto request) {
        try {
            boolean added = bonusServices.addBonusToEmployee(request.getEmpId(), request.getAmount(), request.getReason(), request.getDate());
            if (added) {
                String msg= "bonus added successfully";
                return Response.ok(msg).build();
            } else {
                throw new ResourceException("Failed to add bonus", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            throw new ResourceException("Failed to add bonus: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:9090/HR_API/webapi/bonuses/employee/{employeeId}
    @GET
    @Path("/{employeeId}")
    @Produces("application/json")
    public Response retrieveAllBonusesForEmployee(@PathParam("employeeId") int employeeId) {
        try {
            List<BonusDto> bonusDtoList = bonusServices.retrieveAllBonusesForEmployee(employeeId);
            return Response.ok(bonusDtoList).build();
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve bonuses for employee: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @GET
    @Path("/amount/{employeeId}")
    @Produces("application/json")
    public Response retrieveAllBonusesAmountForEmployee(@PathParam("employeeId") int employeeId) {
        try {
            BigDecimal amount = bonusServices.retrieveAllBonusesAmountForEmployee(employeeId);
            return Response.ok(amount).build();
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve bonuses amount for employee: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }


    @DELETE
    @Path("/delete/month/{month}/year/{year}")
    public Response deleteBonusesInMonthAndYear(@PathParam("month") int month, @PathParam("year") int year) {
        try {
            boolean deleted = bonusServices.deleteBonusesInMonthAndYear(month, year);
            if (deleted) {
                String msg= "bonuses deleted successfully";
                return Response.ok(msg).build();
            } else {
                throw new ResourceException("Failed to delete bonuses for the specified month and year", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            throw new ResourceException("Failed to delete bonuses for the specified month and year: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @DELETE
    @Path("/delete/employee/{employeeId}/month/{month}/year/{year}")
    public Response deleteBonusesForEmployeeInMonthAndYear(@PathParam("employeeId") int employeeId, @PathParam("month") int month, @PathParam("year") int year) {
        try {
            boolean deleted = bonusServices.deleteBonusesForEmployeeInMonthAndYear(employeeId, month, year);
            if (deleted) {
                String msg= "bonuses deleted successfully";
                return Response.ok(msg).build();
            } else {
                throw new ResourceException("Failed to delete bonuses for the specified employee, month, and year", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            throw new ResourceException("Failed to delete bonuses for the specified employee, month, and year: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
