package gov.iti.jets.controllers.soap.services;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.DeductionDto;
import gov.iti.jets.services.DeductionServices;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.Response;

import java.math.BigDecimal;
import java.util.List;

@WebService
public class DeductionManagement {

    DeductionServices deductionServices = new DeductionServices();

    @WebMethod
    @WebResult(name = "deductions")
    public List<DeductionDto> getAllDeductions() {
        try {
            return deductionServices.getAllDeductions();
        } catch (Exception e) {
            throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);        }
    }

    @WebMethod
    public BigDecimal getAllDeductionsAmount() {
        try {
            return deductionServices.getAllDeductionsAmount();
        } catch (Exception e) {
            throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);        }
    }

    @WebMethod
    public String addDeductionToEmployee(@WebParam(name = "request") DeductionDto request) {
        try {
            boolean added = deductionServices.addDeductionToEmployee(request.getEmpId(), request.getAmount(), request.getReason(), request.getDate());
            if (added) {
                return "deduction added successfully";
            } else {
                throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);            }
        } catch (Exception e) {
            throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);        }
    }

    @WebMethod
    @WebResult(name = "deductions")
    public List<DeductionDto> retrieveAllDeductionsForEmployee(@WebParam(name = "employeeId") int employeeId) {
        try {
            return deductionServices.retrieveAllDeductionsForEmployee(employeeId);
        } catch (Exception e) {
            throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);        }
    }

    @WebMethod
    public BigDecimal retrieveAllDeductionsAmountForEmployee(@WebParam(name = "employeeId") int employeeId) {
        try {
            return deductionServices.retrieveAllDeductionsAmountForEmployee(employeeId);
        } catch (Exception e) {
            throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);        }
    }

    @WebMethod
    public String deleteDeductionsInMonthAndYear(
            @WebParam(name = "month") int month,
            @WebParam(name = "year") int year) {
        try {
            boolean deleted = deductionServices.deleteDeductionsInMonthAndYear(month, year);
            if (deleted) {
                return "deductions deleted successfully";
            } else {
                throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);            }
        } catch (Exception e) {
            throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);        }
    }

    @WebMethod
    public String deleteDeductionsForEmployeeInMonthAndYear(
            @WebParam(name = "employeeId") int employeeId,
            @WebParam(name = "month") int month,
            @WebParam(name = "year") int year) {
        try {
            boolean deleted = deductionServices.deleteDeductionsForEmployeeInMonthAndYear(employeeId, month, year);
            if (deleted) {
                return "deductions deleted successfully";
            } else {
                throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);            }
        } catch (Exception e) {
            throw new ResourceException("Failed to add attendance record", Response.Status.INTERNAL_SERVER_ERROR);        }
    }
}
