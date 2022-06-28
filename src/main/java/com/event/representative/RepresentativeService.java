package com.event.representative;

import com.event.contact.Contact;
import com.event.contact.ContactService;
import com.event.representative.dao.RepresentativeModel;
import com.event.representative.dao.RepresentativeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RepresentativeService {

    private final RepresentativeRepository representativeRepository;
    private final ContactService contactService;

    public RepresentativeService(RepresentativeRepository representativeRepository, ContactService contactService) {
        this.representativeRepository = representativeRepository;
        this.contactService = contactService;
    }

    public Representative getRepresentative(int representativeId) {
        RepresentativeModel representativeModel = representativeRepository.findById(Integer.valueOf(representativeId)).get();
        return createRepresentative(representativeModel);
    }

    public List<Representative> getAllRepresentative() {
        List<Representative> representatives = new ArrayList<>();
        Iterable<RepresentativeModel> representativeModels = representativeRepository.findAll();
        for (RepresentativeModel model: representativeModels){
            representatives.add(createRepresentative(model));
        }
        return representatives;
    }

    public Representative addRepresentative(Representative representative) {
        RepresentativeModel representativeModel = new RepresentativeModel(representative.getFirstName(),
                representative.getLastName(), representative.getContact().getId(), representative.getClientId());
        representativeRepository.save(representativeModel);
        representative.setId(representativeModel.getId());
        return representative;
    }

    public Representative updateRepresentative(int representativeId, Representative newRepresentative) {
        RepresentativeModel representativeFromDB = representativeRepository.findById(representativeId).get();
        representativeFromDB.setFirstName(newRepresentative.getFirstName());
        representativeFromDB.setLastName(newRepresentative.getLastName());
        representativeFromDB.setContactId(newRepresentative.getContact().getId());
        representativeFromDB.setClientId(newRepresentative.getClientId());
        representativeRepository.save(representativeFromDB);
        return newRepresentative;
    }

    public String deleteRepresentative(int representativeId) {
        representativeRepository.deleteById(representativeId);
        return "DELETED";
    }

    private Representative createRepresentative(RepresentativeModel representativeModel){
        Contact contact = contactService.getContact(representativeModel.getContactId());
        return new Representative(representativeModel.getId(), representativeModel.getFirstName(),
                representativeModel.getLastName(), contact, representativeModel.getClientId());
    }

    private MiniRepresentative createMiniRepresentative(RepresentativeModel representativeModel){
        String name = representativeModel.getFirstName() + " " + representativeModel.getLastName();
        return new MiniRepresentative(representativeModel.getId(), name);
    }

    public List<Representative> getAllRepresentativesForClient(String clientId) {
        List<Representative> representatives = new ArrayList<>();
        Iterable<RepresentativeModel> representativeModels = representativeRepository.findAllByClientId(clientId);
        for (RepresentativeModel model: representativeModels){
            representatives.add(createRepresentative(model));
        }
        return representatives;
    }

    public List<MiniRepresentative> getAllMiniRepresentativesForClient(String clientId) {
        List<MiniRepresentative> representatives = new ArrayList<>();
        Iterable<RepresentativeModel> representativeModels = representativeRepository.findAllByClientId(clientId);
        for (RepresentativeModel model: representativeModels){
            representatives.add(createMiniRepresentative(model));
        }
        return representatives;
    }
}
