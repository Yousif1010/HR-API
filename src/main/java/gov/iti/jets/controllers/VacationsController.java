package gov.iti.jets.controllers;

import gov.iti.jets.persistence.dtos.VacationsDto;
import gov.iti.jets.services.VacationsServices;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.List;

@Path("/vacations")
public class VacationsController {

    VacationsServices vacationsServices = new VacationsServices();

    // http://localhost:9090/HR_API/webapi/vacations
    @GET
    public List<VacationsDto> getAllVacations(){
        return vacationsServices.getAllVacations();
    }
}

