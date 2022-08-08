package com.event.event;

import com.event.eventStatus.EventStatus;
import com.event.logistic.Logistic;
import com.event.representative.Representative;
import com.event.user.MiniUser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EventControllerTest {

    private static final String ID = "3b62696f-096b-4cdf-ab42-80d67eb22fe8";
    private static final String ID2 = "ddc94bdf-5eae-4705-876a-23e709e3dc07";
    private static final String ID3 = "e6c3fece-d445-4433-a8ad-f8d3e33db439";
    private static final String ID4 = "bedafbf5-adf2-4e09-939f-9d88648bcd3d";

    @Test
    void getEventTest(){
        Event event = new Event(UUID.fromString(ID), "party", new MiniUser(), new Representative(), LocalDateTime.now(), LocalDateTime.now(),
                LocalDateTime.now(), "Krakow", "notes", new EventStatus(), new ArrayList<>(), new Logistic());
        EventService eventService = Mockito.mock(EventService.class);
        EventController eventController = new EventController(eventService);
        Mockito.when(eventService.getEvent(ID)).thenReturn(event);

        Event eventFromDb = eventController.getEvent(ID);

        Mockito.verify(eventService).getEvent(ID);
        assertEquals("Krakow", eventFromDb.getEventPlace());
    }

    @Test
    void getAllEventsTest(){
        List<MiniEvent> events = new ArrayList<>();
        MiniEvent miniEvent = new MiniEvent(UUID.fromString(ID), ID2, "Tomasz", 3, "Milosz", ID3, "Wldek", ID4,
                "Kinga", LocalDateTime.now(), new EventStatus(), 2);
        MiniEvent miniEvent2 = new MiniEvent(UUID.fromString(ID2), ID, "TZuzanna", 3, "Marek", ID4, "Kacper", ID2,
                "Zofia", LocalDateTime.now(), new EventStatus(), 1);
        events.add(miniEvent);
        events.add(miniEvent2);
        EventService eventService = Mockito.mock(EventService.class);
        EventController eventController = new EventController(eventService);
        Mockito.when(eventController.getAllEvents()).thenReturn(events);

        List<MiniEvent> eventsFromDb = eventController.getAllEvents();

        Mockito.verify(eventService).getAllEvents();
        assertEquals(2, eventsFromDb.size());
    }

    @Test
    void addEventTest(){
        Event event = new Event(UUID.fromString(ID), "party", new MiniUser(), new Representative(), LocalDateTime.now(), LocalDateTime.now(),
                LocalDateTime.now(), "Krakow", "notes", new EventStatus(), new ArrayList<>(), new Logistic());
        EventService eventService = Mockito.mock(EventService.class);
        EventController eventController = new EventController(eventService);
        Mockito.when(eventService.addEvent(event)).thenReturn(event);

        Event addEvent = eventController.addEvent(event);

        Mockito.verify(eventService).addEvent(event);
        assertEquals(ID, addEvent.getId().toString());
    }

    @Test
    void updateEvent(){
        Event event = new Event(UUID.fromString(ID), "party", new MiniUser(), new Representative(), LocalDateTime.now(), LocalDateTime.now(),
                LocalDateTime.now(), "Krakow", "notes", new EventStatus(), new ArrayList<>(), new Logistic());
        EventService eventService = Mockito.mock(EventService.class);
        EventController eventController = new EventController(eventService);
        Mockito.when(eventService.updateEvent(ID, event)).thenReturn(event);

        Event updateEvent = eventController.updateEvent(ID, event);

        Mockito.verify(eventService).updateEvent(ID, event);
        assertEquals(ID, updateEvent.getId().toString());
    }

    @Test
    void deleteEventTest(){
        EventService eventService = Mockito.mock(EventService.class);
        EventController eventController = new EventController(eventService);
        Mockito.when(eventService.deleteEvent(ID)).thenReturn("DELETED");

        String returned = eventController.deleteEvent(ID);

        Mockito.verify(eventService).deleteEvent(ID);
        assertEquals("DELETED", returned);
    }

    @Test
    void addEquipmentToEventTest(){
        EventService eventService = Mockito.mock(EventService.class);
        EventController eventController = new EventController(eventService);

        eventController.addEquipmentToEvent(ID, 2);

        Mockito.verify(eventService).addEquipmentToEvent(ID, 2);
    }

    @Test
    void deleteEquipmentFromEventTest(){
        EventService eventService = Mockito.mock(EventService.class);
        EventController eventController = new EventController(eventService);

        eventController.deleteEquipmentFromEvent(ID, 2);

        Mockito.verify(eventService).deleteEquipmentFromEvent(ID, 2);
    }
}