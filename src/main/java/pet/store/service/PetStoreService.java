package pet.store.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {

    private final PetStoreDao petStoreDao;

    @Autowired
    public PetStoreService(PetStoreDao petStoreDao) {
        this.petStoreDao = petStoreDao;
    }

    public PetStoreData savePetStore(PetStoreData petStoreData) {
        Long petStoreId = petStoreData.getPetStoreId();
        PetStore petStore = (petStoreId == null)
                ? findOrCreatePetStore()
                : findPetStoreById(petStoreId);
        copyPetStoreFields(petStore, petStoreData);
        PetStore savedPetStore = petStoreDao.save(petStore);
        return new PetStoreData(savedPetStore);
    }

    private PetStore findOrCreatePetStore() {
        return new PetStore();
    }

    private PetStore findPetStoreById(Long petStoreId) {
        return petStoreDao.findById(petStoreId)
                .orElseThrow(() -> new NoSuchElementException("Pet Store with ID " + petStoreId + " not found."));
    }

    private void copyPetStoreFields(PetStore petStore, PetStoreData petStoreData) {
        petStore.setPetStoreName(petStoreData.getPetStoreName());
        petStore.setPetStoreAddress(petStoreData.getPetStoreAddress());
        petStore.setPetStoreCity(petStoreData.getPetStoreCity());
        petStore.setPetStoreState(petStoreData.getPetStoreState());
        petStore.setPetStoreZip(petStoreData.getPetStoreZip());
        petStore.setPetStorePhone(petStoreData.getPetStorePhone());
    }
    
    public PetStoreData updatePetStore(Long storeId, PetStoreData petStoreData) {
        PetStore existingPetStore = findPetStoreById(storeId);
        copyPetStoreFields(existingPetStore, petStoreData);
        PetStore updatedPetStore = petStoreDao.save(existingPetStore);
        return new PetStoreData(updatedPetStore);
    }
}

