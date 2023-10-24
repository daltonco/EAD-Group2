package com.adoptmeplus.enterprise.service;
import com.adoptmeplus.enterprise.dao.IAdoptionDAO;
import com.adoptmeplus.enterprise.dto.Adoption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * The `AdoptionServiceStub` class provides a stub implementation of the `IAdoptionService` interface
 * for managing adoption records in the AdoptMePlus enterprise system.
 *
 * This class is responsible for saving and fetching adoption records using an underlying `IAdoptionDAO`
 * data access object. It is marked as a Spring service to enable dependency injection.
 *
 * @author AdoptMePlusDevTeam
 * @version 1.0
 */
@Service
public class AdoptionServiceStub implements IAdoptionService{

    private final IAdoptionDAO adoptionDAO;

    /**
     * Constructs an `AdoptionServiceStub` with the specified `IAdoptionDAO`.
     *
     * @param adoptionDAO The data access object for managing adoption records.
     */
    @Autowired
    public AdoptionServiceStub(IAdoptionDAO adoptionDAO){
        this.adoptionDAO = adoptionDAO;
    }

    /**
     * Saves an adoption record to the underlying data source.
     *
     * @param adoption The adoption record to be saved.
     * @return The saved adoption record.
     */
    @Override
    public Adoption save(Adoption adoption) {

        return adoptionDAO.save(adoption);
    }

    /**
     * Retrieves a list of all adoption records from the underlying data source.
     *
     * @return A list of adoption records.
     */
    @Override
    public List<Adoption> fetchAll() {

        return adoptionDAO.fetchAll();
    }
}