package com.event.event;

import com.event.client.Client;
import com.event.client.ClientService;
import com.event.equipment.Equipment;
import com.event.equipment.EquipmentService;
import com.event.event.dao.EventModel;
import com.event.event.dao.EventRepository;
import com.event.eventStatus.EventStatus;
import com.event.eventStatus.EventStatusService;
import com.event.logistic.Logistic;
import com.event.logistic.LogisticService;
import com.event.representative.Representative;
import com.event.representative.RepresentativeService;
import com.event.user.MiniUser;
import com.event.user.User;
import com.event.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {

    private static final String ID = "3b62696f-096b-4cdf-ab42-80d67eb22fe8";
    private static final String ID2 = "ddc94bdf-5eae-4705-876a-23e709e3dc07";
    private static final String ID3 = "e6c3fece-d445-4433-a8ad-f8d3e33db439";
    private static final String ID4 = "bedafbf5-adf2-4e09-939f-9d88648bcd3d";

    @Test
    void addEventTest(){
        Event event = new Event(UUID.fromString(ID), "party", new MiniUser(), new Representative(), LocalDateTime.now(), LocalDateTime.now(),
                LocalDateTime.now(), "Krakow", "notes", new EventStatus(), new ArrayList<>(), new Logistic());
        EventRepository eventRepository = Mockito.mock(EventRepository.class);
        EventService eventService = new EventService(eventRepository, null, null, null, null, null, null);

        Event addEvent = eventService.addEvent(event);

        Mockito.verify(eventRepository).save(ArgumentMatchers.any(EventModel.class));
        assertEquals("party", addEvent.getName());
    }

    @Test
    void deleteEventTest(){
        EventRepository eventRepository = Mockito.mock(EventRepository.class);
        EventService eventService = new EventService(eventRepository, null, null, null, null, null, null);

        String deleted = eventService.deleteEvent(ID);

        Mockito.verify(eventRepository).deleteById(UUID.fromString(ID));
        assertEquals("DELETED", deleted);
    }

    @Test
    void updateEventTest(){
        Event event = new Event(UUID.fromString(ID), "party", new MiniUser(), new Representative(), LocalDateTime.now(), LocalDateTime.now(),
                LocalDateTime.now(), "Krakow", "notes", new EventStatus(), new ArrayList<>(), new Logistic());
        EventRepository eventRepository = Mockito.mock(EventRepository.class);
        EventService eventService = new EventService(eventRepository, null, null, null, null, null, null);
        EventModel model = new EventModel();
        Mockito.when(eventRepository.findById(UUID.fromString(ID))).thenReturn(Optional.of(model));

        Event updateEvent = eventService.updateEvent(ID, event);

        Mockito.verify(eventRepository).save(model);
        Mockito.verify(eventRepository).findById(UUID.fromString(ID));
        assertEquals("notes", updateEvent.getNotes());
    }

    @Test
    void getEventTest(){
        EventRepository eventRepository = Mockito.mock(EventRepository.class);
        EventModel model = new EventModel();
        model.setId(UUID.fromString(ID));
        model.setUserId(ID2);
        model.setRepresentativeId(4);
        model.setEventStatusId(8);
        model.setLogisticsId(9);
        model.setEquipments(List.of(12));
        Mockito.when(eventRepository.findById(UUID.fromString(ID))).thenReturn(Optional.of(model));
        UserService userService = Mockito.mock(UserService.class);
        User user = new User();
        user.setId(UUID.fromString(ID2));
        Mockito.when(userService.getUser(UUID.fromString(ID2))).thenReturn(user);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        Representative representative = new Representative();
        Mockito.when(representativeService.getRepresentative(4)).thenReturn(representative);
        EventStatusService eventStatusService = Mockito.mock(EventStatusService.class);
        EventStatus eventStatus = new EventStatus();
        Mockito.when(eventStatusService.getEventStatus(8)).thenReturn(eventStatus);
        LogisticService logisticService = Mockito.mock(LogisticService.class);
        Logistic logistic = new Logistic();
        Mockito.when(logisticService.getLogistic(9)).thenReturn(logistic);
        EquipmentService equipmentService = Mockito.mock(EquipmentService.class);
        Equipment equipment = new Equipment();
        Mockito.when(equipmentService.getEquipmentById(12)).thenReturn(equipment);
        ClientService clientService = Mockito.mock(ClientService.class);
        EventService eventService = new EventService(eventRepository, userService, representativeService,
                eventStatusService, logisticService, equipmentService, clientService);

        Event eventFromDB = eventService.getEvent(ID);

        Mockito.verify(eventRepository).findById(UUID.fromString(ID));
        Mockito.verify(userService).getUser(UUID.fromString(ID2));
        Mockito.verify(representativeService).getRepresentative(4);
        Mockito.verify(eventStatusService).getEventStatus(8);
        Mockito.verify(logisticService).getLogistic(9);
        Mockito.verify(equipmentService).getEquipmentById(12);
        assertEquals(UUID.fromString(ID), eventFromDB.getId());
    }

    @Test
    void getAllEventTest(){
        List<EventModel> eventModels = new ArrayList<>();
        EventModel model = new EventModel();
        model.setUserId(ID);
        model.setRepresentativeId(6);
        model.setEventStatusId(3);
        eventModels.add(model);
        EventRepository eventRepository = Mockito.mock(EventRepository.class);
        Mockito.when(eventRepository.findAll()).thenReturn(eventModels);
        UserService userService = Mockito.mock(UserService.class);
        User eventUser = new User();
        eventUser.setId(UUID.fromString(ID));
        Mockito.when(userService.getUser(UUID.fromString(ID))).thenReturn(eventUser);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        Representative representative = new Representative();
        representative.setClientId(ID2);
        Mockito.when(representativeService.getRepresentative(6)).thenReturn(representative);
        EventStatusService eventStatusService = Mockito.mock(EventStatusService.class);
        EventStatus eventStatus = new EventStatus();
        Mockito.when(eventStatusService.getEventStatus(3)).thenReturn(eventStatus);
        LogisticService logisticService = Mockito.mock(LogisticService.class);
        EquipmentService equipmentService = Mockito.mock(EquipmentService.class);
        ClientService clientService = Mockito.mock(ClientService.class);
        Client client = new Client();
        client.setId(UUID.fromString(ID));
        client.setAppUserId(ID);
        Mockito.when(clientService.getClient(ID2)).thenReturn(client);
        EventService eventService = new EventService(eventRepository, userService, representativeService,
                eventStatusService, logisticService, equipmentService, clientService);

        List<MiniEvent> events = eventService.getAllEvents();

        Mockito.verify(eventRepository).findAll();
        Mockito.verify(userService, Mockito.times(2)).getUser(UUID.fromString(ID));
        Mockito.verify(representativeService).getRepresentative(6);
        Mockito.verify(eventStatusService).getEventStatus(3);
        Mockito.verify(clientService).getClient(ID2);
        assertEquals(1, events.size());
    }

    @Test
    void addEquipmentToEventTest(){
        EventModel model = new EventModel();
        EventRepository eventRepository = Mockito.mock(EventRepository.class);
        EventService eventService = new EventService(eventRepository, null, null, null, null, null, null);
        Mockito.when(eventRepository.findById(UUID.fromString(ID))).thenReturn(Optional.of(model));

        eventService.addEquipmentToEvent(ID, 7);

        Mockito.verify(eventRepository).findById(UUID.fromString(ID));
        Mockito.verify(eventRepository).save(model);
    }

    @Test
    void deleteEquipmentFromEventTest(){
        EventModel model = new EventModel();
        EventRepository eventRepository = Mockito.mock(EventRepository.class);
        EventService eventService = new EventService(eventRepository, null, null, null, null, null, null);
        Mockito.when(eventRepository.findById(UUID.fromString(ID))).thenReturn(Optional.of(model));

        eventService.deleteEquipmentFromEvent(ID, 7);

        Mockito.verify(eventRepository).findById(UUID.fromString(ID));
        Mockito.verify(eventRepository).save(model);
    }
}