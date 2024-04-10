package gov.iti.jets.controllers.rest;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.VacationsDto;
import gov.iti.jets.persistence.enums.VacationsStatus;
import gov.iti.jets.persistence.enums.VacationsType;
import gov.iti.jets.services.VacationsServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.Collections;
import java.util.List;

@Path("/vacations")
public class VacationsController {

    VacationsServices vacationsServices = new VacationsServices();

    // http://localhost:9090/HR_API/webapi/vacations
    @GET
    public List<VacationsDto> getAllVacations(){
        VacationsServices vacationsServices = new VacationsServices();
        List<VacationsDto> VacationsDtoList = vacationsServices.getAllVacations();
        for (VacationsDto vacationsDto : VacationsDtoList) {
            UriBuilder uriBuilder = UriBuilder.
                    fromUri("http://localhost:9090/HR_API/webapi/vacations/{id}");
            Link self = Link.fromUriBuilder(uriBuilder.
                    resolveTemplate("id", vacationsDto.getVacationId())).rel("self").build();
            vacationsDto.setListOfLinks(Collections.singletonList(self));
        }
        return VacationsDtoList;
    }
    @GET
    @Path("/{id}")
    public VacationsDto  getVacationById(@PathParam("id") Integer id,@Context UriInfo uriInfo){
        VacationsDto  vacationsDto = vacationsServices.getVacationById(id);
        Link self = Link.fromUriBuilder(uriInfo.getAbsolutePathBuilder()).rel("self").build();
        vacationsDto.setListOfLinks(Collections.singletonList(self));
        return vacationsDto;

    }
    // http://localhost:9090/HR_API/webapi/vacations
    @POST
    @Consumes(value = "application/json")
    public Response addVacation(VacationsDto vacationsDto, @Context UriInfo uriInfo) {
        vacationsServices.addVacation(vacationsDto);
        return Response.created(uriInfo.getAbsolutePathBuilder().path(String.valueOf(vacationsDto.getVacationId())).build()).build();
    }
    // http://localhost:9090/HR_API/webapi/vacations/1
    @PUT
    @Path("/{id}")
    @Consumes(value = "application/json")
    public VacationsDto updateVacation(@PathParam("id") Integer id, VacationsDto vacationsDto) {
        vacationsDto.setVacationId(id);
        return vacationsServices.updateVacation(vacationsDto);
    }
    @PUT
    @Path("/{id}/approve")
    @Consumes(value = "application/json")
    public Response acceptVacation(@PathParam("id") Integer id ,@Context UriInfo uriInfo) {
        vacationsServices.acceptVacation(id);
        String message = "Vacation with ID " + id + " has been approved.";
        return Response.accepted().entity(message).build();
    }
    @PUT
    @Path("/{id}/refuse")
    @Consumes(value = "application/json")
    public Response refuseVacation(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        vacationsServices.refuseVacation(id);
        String message = "Vacation with ID " + id + " has been refused.";
        return Response.accepted().entity(message).build();
    }
    // http://localhost:9090/HR_API/webapi/vacations/1
    @DELETE
    @Path("/{id}")
    public Response deleteVacation(@PathParam("id") Integer id) throws Exception {
        vacationsServices.deleteVacation(id);
        String message = "Vacation with ID " + id + " has been deleted.";
        return Response.accepted().entity(message).build();
    }

    @GET
    @Path("/filter")
    @Consumes(value = "application/json")
    public List<VacationsDto>
    findFilteredVacationsForEmployee(@QueryParam("empId") Integer empId,
                                                       @QueryParam("startYear") Integer startYear,
                                                       @QueryParam("startMonth") Integer startMonth,
                                                       @QueryParam("startDay") Integer startDay,
                                                       @QueryParam("endYear") Integer endYear,
                                                       @QueryParam("endMonth") Integer endMonth,
                                                       @QueryParam("endDay") Integer endDay,
                                                       @QueryParam("type") VacationsType type,
                                                       @QueryParam("status") VacationsStatus status,
                                                       @Context UriInfo uriInfo) {
        if (empId == null) {
            throw new ResourceException("you must enter empId", Response.Status.BAD_REQUEST);
        }
        VacationsServices vacationsServices = new VacationsServices();
        List<VacationsDto> filteredVacations = vacationsServices.
                findFilteredVacationsForEmployee(empId, startYear, startMonth, startDay,
                        endYear, endMonth, endDay, type, status);
        for (VacationsDto vacationsDto : filteredVacations) {
            UriBuilder uriBuilder = UriBuilder.
                    fromUri("http://localhost:9090/HR_API/webapi/vacations/{id}");
            Link self = Link.fromUriBuilder(uriBuilder.
                    resolveTemplate("id", vacationsDto.getVacationId())).rel("self").build();
            vacationsDto.setListOfLinks(Collections.singletonList(self));
        }
        return filteredVacations;
    }
    @POST
    @Path("/filter")
    @Consumes(value = "application/x-www-form-urlencoded")
    public List<VacationsDto> findFilteredVacationsForEmployeeFromForm(
            @FormParam("empId") Integer empId,
            @FormParam("startYear") Integer startYear,
            @FormParam("startMonth") Integer startMonth,
            @FormParam("startDay") Integer startDay,
            @FormParam("endYear") Integer endYear,
            @FormParam("endMonth") Integer endMonth,
            @FormParam("endDay") Integer endDay,
            @FormParam("type") VacationsType type,
            @FormParam("status") VacationsStatus status,
            @Context UriInfo uriInfo) {
        System.out.println("inside form");

        if (empId == null) {
            throw new ResourceException("you must enter empId", Response.Status.BAD_REQUEST);
        }

        VacationsServices vacationsServices = new VacationsServices();
        List<VacationsDto> filteredVacations = vacationsServices.findFilteredVacationsForEmployee(
                empId, startYear, startMonth, startDay, endYear, endMonth, endDay, type, status);

        for (VacationsDto vacationsDto : filteredVacations) {
            UriBuilder uriBuilder = UriBuilder.fromUri("http://localhost:9090/HR_API/webapi/vacations/{id}");
            Link self = Link.fromUriBuilder(uriBuilder.resolveTemplate("id", vacationsDto.getVacationId())).rel("self").build();
            vacationsDto.setListOfLinks(Collections.singletonList(self));
        }

        return filteredVacations;
    }
}

