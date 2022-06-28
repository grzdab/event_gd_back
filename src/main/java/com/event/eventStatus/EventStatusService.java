package com.event.eventStatus;

import com.event.eventStatus.dao.EventStatusModel;
import com.event.eventStatus.dao.EventStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventStatusService {

    EventStatusRepository eventStatusRepository;

    @Autowired
    public EventStatusService(EventStatusRepository eventStatusRepository){
        this.eventStatusRepository = eventStatusRepository;
    }
    public EventStatus addEventStatus(EventStatus eventStatus) {
        EventStatusModel model = new EventStatusModel(eventStatus.getEventStatus());
        eventStatusRepository.save(model);
        //opcional
        eventStatus.setId(model.getId());
        return eventStatus;
    }

    public EventStatus getEventStatus(int eventStatusId) {
        EventStatusModel model = eventStatusRepository.findById(eventStatusId).get();
        return createEventStatus(model);
    }

    public String deleteEventStatus(int eventStatusId) {
        eventStatusRepository.deleteById(eventStatusId);
        return "Deleted";
    }

    public EventStatus updateEventStatus(int eventStatusId, EventStatus newEventStatus) {
        EventStatusModel model = eventStatusRepository.findById(eventStatusId).get();
        model.setEventStatus(newEventStatus.getEventStatus());
        eventStatusRepository.save(model);
        return newEventStatus;
    }

    public List<EventStatus> getAllEventStatuses(){
        List<EventStatus> eventStatuses = new ArrayList<>();
        Iterable<EventStatusModel> eventStatusModels = eventStatusRepository.findAll();
        for (EventStatusModel model: eventStatusModels){
            //save
            eventStatuses.add(createEventStatus(model));
        }
        return eventStatuses;
    }

    private EventStatus createEventStatus(EventStatusModel eventStatusModel){
        return new EventStatus(eventStatusModel.getId(), eventStatusModel.getEventStatus());
    }

}

