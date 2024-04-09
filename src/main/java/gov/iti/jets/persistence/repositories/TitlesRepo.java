package gov.iti.jets.persistence.repositories;

import gov.iti.jets.persistence.entities.Titles;
import jakarta.persistence.EntityManager;

public class TitlesRepo extends CrudRepo<Titles,Integer>{
    public TitlesRepo(EntityManager entityManager) {
        super(entityManager);
    }
}
