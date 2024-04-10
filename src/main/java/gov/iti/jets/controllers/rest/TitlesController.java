package gov.iti.jets.controllers.rest;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.TitlesDto;
import gov.iti.jets.services.TitlesServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Path("/titles")
public class TitlesController {

    TitlesServices titlesServices = new TitlesServices();

    // http://localhost:9090/HR_API/webapi/titles
    @GET
    public List<TitlesDto> getAllTitles(){
        List<TitlesDto> titlesDtoList = titlesServices.getAllTitles();
        for (TitlesDto titlesDto : titlesDtoList) {
            UriBuilder uriBuilder = UriBuilder.
                    fromUri("http://localhost:9090/HR_API/webapi/titles/{id}");
            Link self = Link.fromUriBuilder(uriBuilder.
                    resolveTemplate("id", titlesDto.getTitleId())).rel("self").build();
            titlesDto.setLink(Collections.singletonList(self));
        }
        return titlesDtoList;
    }

    // http://localhost:9090/HR_API/webapi/titles/{id}
    @GET
    @Path("/{id}")
    public TitlesDto getTitleById(@PathParam("id") int id) {
        TitlesDto titleDto = titlesServices.getTitleById(id);
        UriBuilder uriBuilder = UriBuilder.fromPath("http://localhost:9090/HR_API/webapi/titles/{id}");
        Link self = Link.fromUriBuilder(uriBuilder.resolveTemplate("id", id)).rel("self").build();
        titleDto.setLink(Collections.singletonList(self));
        return titleDto;
    }


    // http://localhost:9090/HR_API/webapi/titles/{titleName}
    @POST
    @Path("/{titleName}")
    @Produces("application/json")
    public Response addTitle(@PathParam("titleName") String titleName) {
        boolean added = titlesServices.addTitle(titleName);
        if (added) {
            return Response.ok().entity("Title '" + titleName + "' added successfully").build();
        } else {
            throw new ResourceException("Failed to add title", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    // http://localhost:9090/HR_API/webapi/titles/{id}
    @DELETE
    @Path("/{id}")
    public Response deleteTitle(@PathParam("id") int id) {
        try {
            boolean deleted = titlesServices.deleteTitle(id);
            if (deleted) {
                return Response.ok().entity("Title deleted successfully").build();
            } else {
                throw new ResourceException("Title not found", Response.Status.NOT_FOUND);
            }
        } catch (Exception e) {
            throw new ResourceException("Failed to delete title", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

}

