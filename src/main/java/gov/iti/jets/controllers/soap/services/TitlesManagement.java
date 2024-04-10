package gov.iti.jets.controllers.soap.services;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.TitlesDto;
import gov.iti.jets.services.TitlesServices;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.Response;
import jakarta.xml.ws.BindingType;
import jakarta.xml.ws.soap.SOAPBinding;

import java.util.List;

@WebService
public class TitlesManagement {

    TitlesServices titlesServices = new TitlesServices();

    @WebMethod
    @WebResult(name = "titles")
    public List<TitlesDto> getAllTitles() {
        List<TitlesDto> titlesDtoList = titlesServices.getAllTitles();
        for (TitlesDto titlesDto : titlesDtoList) {
            // Assuming we don't need to add links for SOAP, this part is removed.
        }
        return titlesDtoList;
    }

    @WebMethod
    @WebResult(name = "title")
    public TitlesDto getTitleById(@WebParam(name = "id") int id) {
        TitlesDto titleDto = titlesServices.getTitleById(id);
        return titleDto;
    }

    @WebMethod
    @WebResult(name = "result")
    public String addTitle(@WebParam(name = "titleName") String titleName) {
        boolean added = titlesServices.addTitle(titleName);
        if (added) {
            return "Title '" + titleName + "' added successfully";
        } else {
            throw new ResourceException("Failed to add title", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }

    @WebMethod
    @WebResult(name = "result")
    public String deleteTitle(@WebParam(name = "id") int id) {
        try {
            boolean deleted = titlesServices.deleteTitle(id);
            if (deleted) {
                return "Title deleted successfully";
            } else {
                throw new ResourceException("Title not found", Response.Status.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            throw new ResourceException("Failed to delete title", Response.Status.INTERNAL_SERVER_ERROR);
        }
    }
}
