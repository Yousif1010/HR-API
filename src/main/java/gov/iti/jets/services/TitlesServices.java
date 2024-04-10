package gov.iti.jets.services;

import gov.iti.jets.exceptions.ResourceException;
import gov.iti.jets.persistence.connection.JPAManager;
import gov.iti.jets.persistence.dtos.TitlesDto;
import gov.iti.jets.persistence.entities.Titles;
import gov.iti.jets.persistence.mappers.TitlesMapper;
import gov.iti.jets.persistence.repositories.TitlesRepo;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class TitlesServices {

    private final TitlesRepo titlesRepo;

    public TitlesServices() {
        EntityManager em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        titlesRepo = new TitlesRepo(em);
    }

    public List<TitlesDto> getAllTitles() {
        List<Titles> titlesList = titlesRepo.findAll(Titles.class).get();
        if(titlesList.isEmpty()){
            throw new ResourceException("titles list is empty", Response.Status.NOT_FOUND);
        }
        List<TitlesDto> titlesDtoList = TitlesMapper.INSTANCE.toTitlesDtoList(titlesList);
        return titlesDtoList;
    }
    public TitlesDto getTitleById(int id) {
        Titles title = titlesRepo.findById(Titles.class, id).get();
        if(title == null){
            throw new ResourceException("title not found", Response.Status.NOT_FOUND);
        }
        TitlesDto titleDto = TitlesMapper.INSTANCE.toTitlesDto(title);
        return titleDto;
    }
    public boolean addTitle(String titleName) {
        Titles title = new Titles(titleName);
        titlesRepo.save(title);
        return true;
    }
    public boolean deleteTitle(int id) throws Exception {
        titlesRepo.deleteById(Titles.class, id);
        return true;
    }
}
