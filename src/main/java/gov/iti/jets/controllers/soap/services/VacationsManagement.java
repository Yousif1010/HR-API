package gov.iti.jets.controllers.soap.services;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.dtos.VacationsDto;
import gov.iti.jets.persistence.enums.VacationsStatus;
import gov.iti.jets.persistence.enums.VacationsType;
import gov.iti.jets.services.VacationsServices;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.ws.rs.core.Response;

import java.util.List;

@WebService
public class VacationsManagement {



        VacationsServices vacationsServices = new VacationsServices();

        @WebMethod
        @WebResult(name = "vacations")
        public List<VacationsDto> getAllVacations() {
            List<VacationsDto> vacationsDtoList = vacationsServices.getAllVacations();
            return vacationsDtoList;
        }

        @WebMethod
        @WebResult(name = "vacation")
        public VacationsDto getVacationById(@WebParam(name = "id") Integer id) {
            VacationsDto vacationsDto = vacationsServices.getVacationById(id);
            return vacationsDto;
        }

        @WebMethod
        public void addVacation(@WebParam(name = "vacationsDto") VacationsDto vacationsDto) {
            vacationsServices.addVacation(vacationsDto);
        }

        @WebMethod
        public VacationsDto updateVacation(@WebParam(name = "id") Integer id, @WebParam(name = "vacationsDto") VacationsDto vacationsDto) {
            vacationsDto.setVacationId(id);
            return vacationsServices.updateVacation(vacationsDto);
        }

        @WebMethod
        public void acceptVacation(@WebParam(name = "id") Integer id) {
            vacationsServices.acceptVacation(id);
        }

        @WebMethod
        public void refuseVacation(@WebParam(name = "id") Integer id) {
            vacationsServices.refuseVacation(id);
        }

        @WebMethod
        public void deleteVacation(@WebParam(name = "id") Integer id) throws Exception {
            vacationsServices.deleteVacation(id);
        }

        @WebMethod
        public List<VacationsDto> findFilteredVacationsForEmployee(
                @WebParam(name = "empId") Integer empId,
                @WebParam(name = "startYear") Integer startYear,
                @WebParam(name = "startMonth") Integer startMonth,
                @WebParam(name = "startDay") Integer startDay,
                @WebParam(name = "endYear") Integer endYear,
                @WebParam(name = "endMonth") Integer endMonth,
                @WebParam(name = "endDay") Integer endDay,
                @WebParam(name = "type") VacationsType type,
                @WebParam(name = "status") VacationsStatus status) {
            if (empId == null) {
                throw new ResourceException("you must enter empId", Response.Status.INTERNAL_SERVER_ERROR);
            }
            return vacationsServices.findFilteredVacationsForEmployee(
                    empId, startYear, startMonth, startDay, endYear, endMonth, endDay, type, status);
        }

        @WebMethod
        public List<VacationsDto> findFilteredVacationsForEmployeeFromForm(
                @WebParam(name = "empId") Integer empId,
                @WebParam(name = "startYear") Integer startYear,
                @WebParam(name = "startMonth") Integer startMonth,
                @WebParam(name = "startDay") Integer startDay,
                @WebParam(name = "endYear") Integer endYear,
                @WebParam(name = "endMonth") Integer endMonth,
                @WebParam(name = "endDay") Integer endDay,
                @WebParam(name = "type") VacationsType type,
                @WebParam(name = "status") VacationsStatus status) {
            if (empId == null) {
                throw new ResourceException("you must enter empId", Response.Status.INTERNAL_SERVER_ERROR);
            }
            return vacationsServices.findFilteredVacationsForEmployee(
                    empId, startYear, startMonth, startDay, endYear, endMonth, endDay, type, status);
        }
    }

