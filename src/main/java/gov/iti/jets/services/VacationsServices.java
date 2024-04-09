package gov.iti.jets.services;

import gov.iti.jets.persistence.connection.JPAManager;
import gov.iti.jets.persistence.dtos.VacationsDto;
import gov.iti.jets.persistence.entities.Vacations;
import gov.iti.jets.persistence.enums.VacationsStatus;
import gov.iti.jets.persistence.enums.VacationsType;
import gov.iti.jets.persistence.mappers.VacationsMapper;
import gov.iti.jets.persistence.repositories.VacationsRepo;
import jakarta.persistence.EntityManager;

import java.util.Date;
import java.util.List;

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
        List<VacationsDto> vacationsDtoList = VacationsMapper.INSTANCE.ToVacationsDtoList(vacationsList);
        return vacationsDtoList;
    }
}
