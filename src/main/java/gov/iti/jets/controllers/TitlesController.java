package gov.iti.jets.controllers;

import gov.iti.jets.persistence.dtos.TitlesDto;
import gov.iti.jets.services.TitlesServices;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.List;

@Path("/titles")
public class TitlesController {

    TitlesServices titlesServices = new TitlesServices();

    // http://localhost:9090/HR_API/webapi/titles
    @GET
    public List<TitlesDto> getAllTitles(){
        return titlesServices.getAllTitles();
    }
}

