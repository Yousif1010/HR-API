package gov.iti.jets.controllers;

import gov.iti.jets.persistence.dtos.DeductionDto;
import gov.iti.jets.services.DeductionServices;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/deductions")
public class DeductionController {

    DeductionServices deductionServices = new DeductionServices();

    // http://localhost:9090/HR_API/webapi/deductions
    @GET
    public List<DeductionDto> getAllDeductions(){
        return deductionServices.getAllDeductions();
    }
}

