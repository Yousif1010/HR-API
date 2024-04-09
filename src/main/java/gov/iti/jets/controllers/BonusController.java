package gov.iti.jets.controllers;

import gov.iti.jets.persistence.dtos.AttendanceDto;
import gov.iti.jets.persistence.dtos.BonusDto;
import gov.iti.jets.services.AttendanceServices;
import gov.iti.jets.services.BonusServices;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/bonuses")
public class BonusController {

    BonusServices bonusServices = new BonusServices();

    //http://localhost:9090/HR_API/webapi/bonuses
    @GET
    public List<BonusDto> getAllBonuses(){
        return bonusServices.getAllBonuses();
    }
}
