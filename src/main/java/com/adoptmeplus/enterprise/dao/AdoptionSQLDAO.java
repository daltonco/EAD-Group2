package com.adoptmeplus.enterprise.dao;
import com.adoptmeplus.enterprise.dto.Adoption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("dev")
public class AdoptionSQLDAO implements IAdoptionDAO{

    final AdoptionRepository adoptionRepository;

    @Autowired
    public AdoptionSQLDAO(AdoptionRepository adoptionRepository) {
        this.adoptionRepository = adoptionRepository;
    }


    public AdoptionSQLDAO(AdoptionRepository adoptionRepository) {
        this.adoptionRepository = adoptionRepository;
    }

    /**
     * Saves an Adoption record to the SQL database.
     *
     * @param adoption The Adoption object to be saved.
     * @return The saved Adoption object.
     */
    @Override
    public Adoption save(Adoption adoption) {
        return adoptionRepository.save(adoption);
    }

    /**
     * Fetches a list of all Adoption records from the SQL database.
     *
     * @return A List of Adoption objects representing all available Adoptions.
     */
    @Override
    public List<Adoption> findAll() {
        Iterable<Adoption> adoptions = adoptionRepository.findAll();
        List<Adoption> allAdoptions = new ArrayList<>();
        for (Adoption adoption: adoptions) {
            allAdoptions.add(adoption);
        }
        return allAdoptions;
    }

    /**
     * Deletes an Adoption record from the SQL database.
     *
     * @param adoption The Adoption object to be deleted.
     */
    @Override
    public void delete(Adoption adoption){
        adoptionRepository.delete(adoption);
    }

    /**
     * Fetches an adoption record by its unique identifier from the SQL database.
     *
     * @param adoptionId The unique identifier of the Dog to be fetched.
     * @return The Adoption object with the specified adoptionId.
     */
    @Override
    public Adoption fetchAdoption(int adoptionId) {
        return adoptionRepository.findById(adoptionId).get();
    }
}
