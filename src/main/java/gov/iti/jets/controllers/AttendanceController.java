package gov.iti.jets.controllers;

import gov.iti.jets.persistence.dtos.AttendanceDto;
import gov.iti.jets.services.AttendanceServices;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.List;

@Path("/attendances")
public class AttendanceController {
    AttendanceServices attendanceServices = new AttendanceServices();

    //http://localhost:9090/HR_API/webapi/attendances
    @GET
    public List<AttendanceDto> getAllAttendances(){
       return attendanceServices.getAllAttendances();
    }

}
