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

    public Representative getRepresentative(String representativeId) {
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

    public Representative updateRepresentative(String representativeId, Representative newRepresentative) {
        RepresentativeModel representativeFromDB = representativeRepository.findById(Integer.valueOf(representativeId)).get();
        representativeFromDB.setFirstName(newRepresentative.getFirstName());
        representativeFromDB.setLastName(newRepresentative.getLastName());
        representativeFromDB.setContactId(newRepresentative.getContact().getId());
        representativeFromDB.setClientId(newRepresentative.getClientId());
        representativeRepository.save(representativeFromDB);
        return newRepresentative;
    }

    public String deleteRepresentative(String representativeId) {
        representativeRepository.deleteById(Integer.valueOf(representativeId));
        return "DELETED";
    }

    private Representative createRepresentative(RepresentativeModel representativeModel){
        Contact contact = contactService.getContact(representativeModel.getContactId());
        return new Representative(representativeModel.getId(), representativeModel.getFirstName(),
                representativeModel.getLastName(), contact, representativeModel.getClientId());
    }
}
