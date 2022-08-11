package com.event.representative;

import com.event.contact.Contact;
import com.event.contact.ContactService;
import com.event.representative.dao.RepresentativeModel;
import com.event.representative.dao.RepresentativeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RepresentativeServiceTest {

    private static final String ID = "3b62696f-096b-4cdf-ab42-80d67eb22fe8";

    @Test
    void  getRepresentativeTest(){
        RepresentativeModel representativeModel = new RepresentativeModel(7, "Tomasz", "Nowak", 6, ID);
        RepresentativeRepository representativeRepository = Mockito.mock(RepresentativeRepository.class);
        ContactService contactService = Mockito.mock(ContactService.class);
        RepresentativeService representativeService = new RepresentativeService(representativeRepository, contactService);
        Mockito.when(representativeRepository.findById(7)).thenReturn(Optional.of(representativeModel));
        Mockito.when(contactService.getContact(6)).thenReturn(new Contact());

        Representative newRepresentative = representativeService.getRepresentative(7);

        Mockito.verify(representativeRepository).findById(7);
        Mockito.verify(contactService).getContact(6);
        assertEquals("Nowak", newRepresentative.getLastName());
    }

    @Test
    void getAllRepresentativeTest(){
        RepresentativeModel representativeModel = new RepresentativeModel(7, "Tomasz", "Nowak", 6, ID);
        RepresentativeModel representativeModel2 = new RepresentativeModel(6, "Wiktoria", "Kowalska", 7, ID);
        List<RepresentativeModel> representativeModelList = new ArrayList<>();
        representativeModelList.add(representativeModel);
        representativeModelList.add(representativeModel2);
        RepresentativeRepository representativeRepository = Mockito.mock(RepresentativeRepository.class);
        ContactService contactService = Mockito.mock(ContactService.class);
        RepresentativeService representativeService = new RepresentativeService(representativeRepository, contactService);
        Mockito.when(representativeRepository.findAll()).thenReturn(representativeModelList);
        Mockito.when(contactService.getContact(6)).thenReturn(new Contact());
        Mockito.when(contactService.getContact(7)).thenReturn(new Contact());

        List<Representative> allRepresentative = representativeService.getAllRepresentative();

        Mockito.verify(representativeRepository).findAll();
        Mockito.verify(contactService).getContact(6);
        Mockito.verify(contactService).getContact(7);
        assertEquals(2, allRepresentative.size());
    }

    @Test
    void addRepresentativeTest(){
        Representative representative = new Representative(7, "Tomasz", "Nowak", new Contact(), ID);
        RepresentativeRepository representativeRepository = Mockito.mock(RepresentativeRepository.class);
        ContactService contactService = Mockito.mock(ContactService.class);
        RepresentativeService representativeService = new RepresentativeService(representativeRepository, contactService);

        Representative newRepresentative = representativeService.addRepresentative(representative);

        Mockito.verify(representativeRepository).save(ArgumentMatchers.any(RepresentativeModel.class));
        assertEquals("Tomasz", newRepresentative.getFirstName());
    }

    @Test
    void updateRepresentativeTest(){
        Representative representative = new Representative(7, "Tomasz", "Nowak", new Contact(), ID);
        RepresentativeModel representativeModel = new RepresentativeModel(7, "Tomasz", "Nowak", 6, ID);
        RepresentativeRepository representativeRepository = Mockito.mock(RepresentativeRepository.class);
        ContactService contactService = Mockito.mock(ContactService.class);
        RepresentativeService representativeService = new RepresentativeService(representativeRepository, contactService);
        Mockito.when(representativeRepository.findById(7)).thenReturn(Optional.of(representativeModel));

        Representative newRepresentative = representativeService.updateRepresentative(7, representative);

        Mockito.verify(representativeRepository).findById(7);
        Mockito.verify(representativeRepository).save(representativeModel);
        assertEquals("Nowak", newRepresentative.getLastName());
    }

    @Test
    void deleteRepresentativeTest(){
        RepresentativeRepository representativeRepository = Mockito.mock(RepresentativeRepository.class);
        ContactService contactService = Mockito.mock(ContactService.class);
        RepresentativeService representativeService = new RepresentativeService(representativeRepository, contactService);

        String delete = representativeService.deleteRepresentative(7);

        Mockito.verify(representativeRepository).deleteById(7);
        assertEquals("DELETED", delete);
    }

    @Test
    void getAllRepresentativesForClientTest(){
        RepresentativeModel representativeModel = new RepresentativeModel(7, "Tomasz", "Nowak", 6, ID);
        RepresentativeModel representativeModel2 = new RepresentativeModel(6, "Monika", "Kowalska", 8, ID);
        List<RepresentativeModel> representatives = new ArrayList<>();
        representatives.add(representativeModel);
        representatives.add(representativeModel2);
        RepresentativeRepository representativeRepository = Mockito.mock(RepresentativeRepository.class);
        ContactService contactService = Mockito.mock(ContactService.class);
        RepresentativeService representativeService = new RepresentativeService(representativeRepository, contactService);
        Mockito.when(representativeRepository.findAllByClientId(ID)).thenReturn(representatives);
        Mockito.when(contactService.getContact(6)).thenReturn(new Contact());
        Mockito.when(contactService.getContact(8)).thenReturn(new Contact());

        List<Representative> allRepresentative = representativeService.getAllRepresentativesForClient(ID);

        Mockito.verify(representativeRepository).findAllByClientId(ID);
        Mockito.verify(contactService).getContact(6);
        Mockito.verify(contactService).getContact(8);
        assertEquals(2, allRepresentative.size());
    }

    @Test
    void getAllMiniRepresentativesForClientTest(){
        RepresentativeModel representativeModel = new RepresentativeModel(7, "Tomasz", "Nowak", 6, ID);
        RepresentativeModel representativeModel2 = new RepresentativeModel(6, "Monika", "Kowalska", 8, ID);
        List<RepresentativeModel> representatives = new ArrayList<>();
        representatives.add(representativeModel);
        representatives.add(representativeModel2);
        RepresentativeRepository representativeRepository = Mockito.mock(RepresentativeRepository.class);
        ContactService contactService = Mockito.mock(ContactService.class);
        RepresentativeService representativeService = new RepresentativeService(representativeRepository, contactService);
        Mockito.when(representativeRepository.findAllByClientId(ID)).thenReturn(representatives);

        List<MiniRepresentative> miniRepresentatives = representativeService.getAllMiniRepresentativesForClient(ID);

        Mockito.verify(representativeRepository).findAllByClientId(ID);
        assertEquals(2, miniRepresentatives.size());
    }
}
