package gov.iti.jets.services;

import gov.iti.jets.persistence.connection.JPAManager;
import gov.iti.jets.persistence.dtos.TitlesDto;
import gov.iti.jets.persistence.entities.Titles;
import gov.iti.jets.persistence.mappers.TitlesMapper;
import gov.iti.jets.persistence.repositories.TitlesRepo;
import jakarta.persistence.EntityManager;
import java.util.List;

public class TitlesServices {

    private final TitlesRepo titlesRepo;

    public TitlesServices() {
        EntityManager em = JPAManager.INSTANCE.getEntityManagerFactory().createEntityManager();
        titlesRepo = new TitlesRepo(em);
    }

    public List<TitlesDto> getAllTitles() {
        List<Titles> titlesList = titlesRepo.findAll(Titles.class).get();
        List<TitlesDto> titlesDtoList = TitlesMapper.INSTANCE.toTitlesDtoList(titlesList);
        return titlesDtoList;
    }
    public TitlesDto getTitleById(int id) {
        Titles title = titlesRepo.findById(Titles.class, id).get();
        TitlesDto titleDto = TitlesMapper.INSTANCE.toTitlesDto(title);
        return titleDto;
    }
    public boolean addTitle(TitlesDto titleDto) {
        Titles title = TitlesMapper.INSTANCE.toTitles(titleDto);
        titlesRepo.save(title);
        return true;
    }
    public boolean deleteTitle(int id) {
        titlesRepo.deleteById(Titles.class, id);
        return true;
    }
}
