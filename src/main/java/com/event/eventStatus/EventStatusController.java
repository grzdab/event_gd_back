package com.event.eventStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventStatusController {
    private final EventStatusService service;

    @Autowired
    public EventStatusController(EventStatusService service) {
        this.service = service;
    }

    @PostMapping("/admin/eventStatus")
    public EventStatus addEventStatus(@RequestBody EventStatus eventStatus){
        return service.addEventStatus(eventStatus);
    }

    @PutMapping("/admin/eventStatus/{eventStatusId}")
    public ResponseEntity<Object> updateEventStatus(@PathVariable String eventStatusId, @RequestBody EventStatus newEventStatus) {
        EventStatus updateEventStatus = service.updateEventStatus(eventStatusId, newEventStatus);
        if (updateEventStatus == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updateEventStatus);
        }
    }

    @GetMapping("/admin/eventStatus/{eventStatusId}")
    public EventStatus getEventStatus(@PathVariable String eventStatusId){
        return service.getEventStatus(eventStatusId);
    }

    @GetMapping("/admin/eventStatus")
    public List<EventStatus> getAllEventStatuses(){
        return service.getAllEventStatuses();
    }

    @DeleteMapping("/admin/eventStatus/{eventStatusId}")
    public String deleteEventStatus(@PathVariable String eventStatusId){
        return service.deleteEventStatus(eventStatusId);
    }
}
