package com.event.representative;

import com.event.contact.Contact;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepresentativeControllerTest {
    private static final String ID = "3b62696f-096b-4cdf-ab42-80d67eb22fe8";

    @Test
    void getRepresentativeTest(){
        Representative representative = new Representative(7, "Tomasz", "Nowak", new Contact(), ID);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        RepresentativeController representativeController = new RepresentativeController(representativeService);
        Mockito.when(representativeService.getRepresentative(7)).thenReturn(representative);

        Representative newRepresentative = representativeController.getRepresentative(7);

        Mockito.verify(representativeService).getRepresentative(7);
        assertEquals("Tomasz", newRepresentative.getFirstName());
    }

    @Test
    void getAllRepresentativeTest(){
        Representative representative = new Representative(7, "Tomasz", "Nowak", new Contact(), ID);
        Representative representativeB = new Representative(6, "Mariusz", "Kowalski", new Contact(), ID);
        List<Representative> representatives = new ArrayList<>();
        representatives.add(representative);
        representatives.add(representativeB);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        RepresentativeController representativeController = new RepresentativeController(representativeService);
        Mockito.when(representativeService.getAllRepresentative()).thenReturn(representatives);

        List<Representative> allRepresentatives = representativeController.getAllRepresentative();

        Mockito.verify(representativeService).getAllRepresentative();
        assertEquals(2, allRepresentatives.size());
    }

    @Test
    void addRepresentativeTest(){
        Representative representative = new Representative(7, "Tomasz", "Nowak", new Contact(), ID);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        RepresentativeController representativeController = new RepresentativeController(representativeService);
        Mockito.when(representativeService.addRepresentative(representative)).thenReturn(representative);

        Representative newRepresentative = representativeController.addRepresentative(representative);

        Mockito.verify(representativeService).addRepresentative(representative);
        assertEquals("Tomasz", newRepresentative.getFirstName());
    }

    @Test
    void updateRepresentativeTest(){
        Representative representative = new Representative(7, "Tomasz", "Nowak", new Contact(), ID);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        RepresentativeController representativeController = new RepresentativeController(representativeService);
        Mockito.when(representativeService.updateRepresentative(7, representative)).thenReturn(representative);

        Representative newRepresentative = representativeController.updateRepresentative(7, representative);

        Mockito.verify(representativeService).updateRepresentative(7, representative);
        assertEquals("Tomasz", newRepresentative.getFirstName());
    }

    @Test
    void deleteRepresentativeTest(){
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        RepresentativeController representativeController = new RepresentativeController(representativeService);
        Mockito.when(representativeService.deleteRepresentative(7)).thenReturn("DELETED");

        String delete = representativeController.deleteRepresentative(7);

        Mockito.verify(representativeService).deleteRepresentative(7);
        assertEquals("DELETED", delete);
    }

    @Test
    void getAllRepresentativesForClientTest(){
        Representative representative = new Representative(7, "Tomasz", "Nowak", new Contact(), ID);
        Representative representativeB = new Representative(6, "Mariusz", "Kowalski", new Contact(), ID);
        List<Representative> representatives = new ArrayList<>();
        representatives.add(representative);
        representatives.add(representativeB);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        RepresentativeController representativeController = new RepresentativeController(representativeService);
        Mockito.when(representativeService.getAllRepresentativesForClient(ID)).thenReturn(representatives);

        List<Representative> clientRepresentatives = representativeController.getAllRepresentativesForClient(ID);

        Mockito.verify(representativeService).getAllRepresentativesForClient(ID);
        assertEquals(2, clientRepresentatives.size());
    }
}