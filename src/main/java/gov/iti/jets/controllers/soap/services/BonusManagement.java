package gov.iti.jets.controllers.soap.services;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.BonusDto;
import gov.iti.jets.services.BonusServices;
import jakarta.jws.WebResult;
import jakarta.ws.rs.core.Response;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.math.BigDecimal;
import java.util.List;

@WebService
public class BonusManagement {

    BonusServices bonusServices = new BonusServices();

    @WebMethod
    @WebResult(name = "bonuses")
    public List<BonusDto> getAllBonuses() {
        try {
            return bonusServices.getAllBonuses();
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve all bonuses: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @WebMethod
    public BigDecimal getAllBonusesAmount() {
        try {
            return bonusServices.getAllBonusesAmount();
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve all bonuses amount: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @WebMethod
    public String addBonusToEmployee(@WebParam(name = "request") BonusDto request) {
        try {
            boolean added = bonusServices.addBonusToEmployee(request.getEmpId(), request.getAmount(), request.getReason(), request.getDate());
            if (added) {
                return "Bonus added successfully";
            } else {
                throw new ResourceException("Failed to add bonus", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            throw new ResourceException("Failed to add bonus: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @WebMethod
    @WebResult(name = "bonuses")
    public List<BonusDto> retrieveAllBonusesForEmployee(@WebParam(name = "employeeId") int employeeId) {
        try {
            return bonusServices.retrieveAllBonusesForEmployee(employeeId);
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve bonuses for employee: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @WebMethod
    public BigDecimal retrieveAllBonusesAmountForEmployee(@WebParam(name = "employeeId") int employeeId) {
        try {
            return bonusServices.retrieveAllBonusesAmountForEmployee(employeeId);
        } catch (Exception e) {
            throw new ResourceException("Failed to retrieve bonuses amount for employee: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @WebMethod
    public String deleteBonusesInMonthAndYear(
            @WebParam(name = "month") int month,
            @WebParam(name = "year") int year) {
        try {
            boolean deleted = bonusServices.deleteBonusesInMonthAndYear(month, year);
            if (deleted) {
                return "Bonuses deleted successfully";
            } else {
                throw new ResourceException("Failed to delete bonuses for the specified month and year", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            throw new ResourceException("Failed to delete bonuses for the specified month and year: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @WebMethod
    public String deleteBonusesForEmployeeInMonthAndYear(
            @WebParam(name = "employeeId") int employeeId,
            @WebParam(name = "month") int month,
            @WebParam(name = "year") int year) {
        try {
            boolean deleted = bonusServices.deleteBonusesForEmployeeInMonthAndYear(employeeId, month, year);
            if (deleted) {
                return "Bonuses deleted successfully";
            } else {
                throw new ResourceException("Failed to delete bonuses for the specified employee, month, and year", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            throw new ResourceException("Failed to delete bonuses for the specified employee, month, and year: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
