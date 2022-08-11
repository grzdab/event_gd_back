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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final UserService userService;
    private final RepresentativeService representativeService;
    private final EventStatusService eventStatusService;
    private final LogisticService logisticService;
    private final EquipmentService equipmentService;
    private final ClientService clientService;

    public EventService(EventRepository eventRepository, UserService userService, RepresentativeService representativeService,
                        EventStatusService eventStatusService, LogisticService logisticService, EquipmentService equipmentService, ClientService clientService) {
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.representativeService = representativeService;
        this.eventStatusService = eventStatusService;
        this.logisticService = logisticService;
        this.equipmentService = equipmentService;
        this.clientService = clientService;
    }

    public Event getEvent(String eventId) {
        EventModel eventModel = eventRepository.findById(UUID.fromString(eventId)).get();
        return createEvent(eventModel);
    }

    public List<MiniEvent> getAllEvents() {
        List<MiniEvent> events = new ArrayList<>();
        Iterable<EventModel> eventModels = eventRepository.findAll();
        for (EventModel model : eventModels) {
            events.add(createMiniEvent(model));
        }
        return events;
    }

    public Event addEvent(Event event) {
        EventModel eventModel = new EventModel(event.getId(), event.getName(), event.getCreationDate(), event.getEventStart(),
                event.getEventEnd(), event.getEventPlace(), event.getNotes(), event.getEventStatus().getId(),
                event.getLogistic().getId(), event.getUser().getId(), event.getRepresentative().getId());
        eventRepository.save(eventModel);
        event.setId(eventModel.getId());
        return event;
    }

    public Event updateEvent(String eventId, Event newEvent) {
        EventModel eventModelFromDb = eventRepository.findById(UUID.fromString(eventId)).get();
        eventModelFromDb.setName(newEvent.getName());
        eventModelFromDb.setCreationDate(newEvent.getCreationDate());
        eventModelFromDb.setEventStartDate(newEvent.getEventStart());
        eventModelFromDb.setEventEndDate(newEvent.getEventEnd());
        eventModelFromDb.setEventPlace(newEvent.getEventPlace());
        eventModelFromDb.setDescription(newEvent.getNotes());
        eventModelFromDb.setEventStatusId(newEvent.getEventStatus().getId());
        eventModelFromDb.setLogisticsId(newEvent.getLogistic().getId());
        eventModelFromDb.setUserId(newEvent.getUser().getId());
        eventModelFromDb.setRepresentativeId(newEvent.getRepresentative().getId());
        eventRepository.save(eventModelFromDb);
        return newEvent;
    }

    public String deleteEvent(String eventId) {
        eventRepository.deleteById(UUID.fromString(eventId));
        return "DELETED";
    }

    private Event createEvent(EventModel eventModel){
        User user = userService.getUser(UUID.fromString(eventModel.getUserId()));
        MiniUser miniUser = new MiniUser(user.getId().toString(), user.getLogin(), user.getFirstName(), user.getLastName(), user.getUserRoles());
        Representative representative = representativeService.getRepresentative(eventModel.getRepresentativeId());
        EventStatus eventStatus = eventStatusService.getEventStatus(eventModel.getEventStatusId());
        List<Equipment> equipments = loadEquipments(eventModel.getEquipments());
        Logistic logistic = logisticService.getLogistic(eventModel.getLogisticsId());
        return new Event(eventModel.getId(), eventModel.getName(), miniUser, representative, eventModel.getCreationDate(),
                eventModel.getEventStartDate(), eventModel.getEventEndDate(), eventModel.getEventPlace(), eventModel.getDescription(),
                eventStatus, equipments, logistic);

    }

    private MiniEvent createMiniEvent(EventModel eventModel){
        User eventUser = userService.getUser(UUID.fromString(eventModel.getUserId()));
        String eventUserName = eventUser.getFirstName() + " " + eventUser.getLastName();
        Representative representative = representativeService.getRepresentative(eventModel.getRepresentativeId());
        String representativeName = representative.getFirstName() + " " + representative.getLastName();
        Client client = clientService.getClient(representative.getClientId());
        User clientUser = userService.getUser(UUID.fromString(client.getAppUserId()));
        String clientUserName = clientUser.getFirstName() + " " + clientUser.getLastName();
        EventStatus eventStatus = eventStatusService.getEventStatus(eventModel.getEventStatusId());
        return new MiniEvent(eventModel.getId(), eventUser.getId().toString(), eventUserName, representative.getId(), representativeName,
                client.getId().toString(), client.getShortName(), clientUser.getId().toString(), clientUserName, eventModel.getCreationDate(),
                eventStatus, loadEquipments(eventModel.getEquipments()).size());
    }

    private List<Equipment> loadEquipments(List<Integer> equipmentIsd){
        List<Equipment> equipments = new ArrayList<>();
        for (int id: equipmentIsd){
            equipments.add(equipmentService.getEquipmentById(id));
        }
        return equipments;
    }

    public void addEquipmentToEvent(String eventId, Integer equipmentId) {
        EventModel eventModelFromDB = eventRepository.findById(UUID.fromString(eventId)).get();
        eventModelFromDB.getEquipments().add(equipmentId);
        eventRepository.save(eventModelFromDB);
    }

    public void deleteEquipmentFromEvent(String eventId, Integer equipmentId) {
        EventModel eventModelFromDB = eventRepository.findById(UUID.fromString(eventId)).get();
        eventModelFromDB.getEquipments().remove(equipmentId);
        eventRepository.save(eventModelFromDB);
    }
}
