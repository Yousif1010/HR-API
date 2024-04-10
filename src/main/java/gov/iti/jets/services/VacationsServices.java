package gov.iti.jets.services;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.connection.JPAManager;
import gov.iti.jets.persistence.dtos.VacationsDto;
import gov.iti.jets.persistence.entities.Vacations;
import gov.iti.jets.persistence.enums.VacationsStatus;
import gov.iti.jets.persistence.enums.VacationsType;
import gov.iti.jets.persistence.mappers.VacationsMapper;
import gov.iti.jets.persistence.repositories.VacationsRepo;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Response;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class VacationsServices {
    private final VacationsRepo vacationsRepo;

    public VacationsServices() {
        EntityManager em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        vacationsRepo = new VacationsRepo(em);
    }
    public Date parseDate(int year, int month, int day) {
        int y = year-1900;
        int m = month-1;
        return new Date(y, m, day);
    }
    public List<VacationsDto> getAllVacations() {
        List<Vacations> vacationsList = vacationsRepo.findAll(Vacations.class).get();
        List<VacationsDto> vacationsDtoList = VacationsMapper.INSTANCE.ToVacationsDtoList(vacationsList);
        return vacationsDtoList;
    }
    public List<VacationsDto> findFilteredVacationsForEmployee(Integer empId, Integer startYear, Integer startMonth
                                                               ,Integer startDay,
                                                               Integer endYear, Integer endMonth, Integer endDay,
                                                               VacationsType type, VacationsStatus status) {
        Date startDate;
        Date endDate;
        if(startYear==null||startMonth==null||startDay==null||endYear==null||endMonth==null||endDay==null)
        {
            startDate=null;
            endDate=null;
        }else{
            startDate = parseDate(startYear, startMonth, startDay);
            endDate =  parseDate(endYear, endMonth, endDay);
        }

        List<Vacations> vacationsList = vacationsRepo.findFilteredVacationsForEmployee
                (empId,startDate,endDate,type,status);
        if(vacationsList.isEmpty()){
            throw new ResourceException("vacations not found", Response.Status.NOT_FOUND);
        }
        List<VacationsDto> vacationsDtoList = VacationsMapper.INSTANCE.ToVacationsDtoList(vacationsList);
        return vacationsDtoList;
    }
    public VacationsDto getVacationById(Integer id) {
        Vacations vacations = null;
        try{
             vacations = vacationsRepo.findById(Vacations.class, id).get();
        }catch(Exception e ){
            throw new ResourceException("vacation not found", Response.Status.NOT_FOUND);
        }
        VacationsDto vacationsDto = VacationsMapper.INSTANCE.ToVacationsDto(vacations);
        return vacationsDto;
    }
    public void deleteVacation(Integer id) throws Exception {
        vacationsRepo.deleteById(Vacations.class, id);
    }
    public VacationsDto updateVacation(VacationsDto vacationsDto) {
        Optional<Vacations> optionalVacations = vacationsRepo.findById(Vacations.class,vacationsDto.getVacationId());
        if(optionalVacations.isEmpty()){
            throw new ResourceException("vacation not found", Response.Status.NOT_FOUND);
        }
        Vacations vacations = optionalVacations.get();
        Vacations updatedVacations = vacationsRepo.update(vacations).get();
        VacationsDto updatedVacationsDto = VacationsMapper.INSTANCE.ToVacationsDto(updatedVacations);
        return updatedVacationsDto;
    }
    public void addVacation(VacationsDto vacationsDto) {
        if(vacationsDto.getVacationId()!=null){
            throw new ResourceException("do not enter vacation id", Response.Status.CONFLICT);
        }
        Vacations vacations = VacationsMapper.INSTANCE.ToVacations(vacationsDto);
//        vacations.setStatus(VacationsStatus.PENDING);
        vacationsRepo.save(vacations);
    }

    public void acceptVacation(Integer id) {
        vacationsRepo.acceptVacation(id);
    }
    public void refuseVacation(Integer id) {
        vacationsRepo.refuseVacation(id);
    }
}
