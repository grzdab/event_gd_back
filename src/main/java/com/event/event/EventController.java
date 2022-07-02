package com.event.event;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/event/{eventId}")
    public Event getEvent(@PathVariable String eventId) {
        return eventService.getEvent(eventId);
    }

    @GetMapping("/event")
    public List<MiniEvent> getAllEvents() {
        return eventService.getAllEvents();
    }

    @PostMapping("/event")
    public Event addEvent(@RequestBody Event event) {
        return eventService.addEvent(event);
    }

    @PutMapping("/event/{eventId}")
    public Event updateEvent(@PathVariable String eventId, @RequestBody Event newEvent) {
        return eventService.updateEvent(eventId, newEvent);
    }

    @DeleteMapping("/event/{eventId}")
    public String deleteEvent(@PathVariable String eventId) {
        return eventService.deleteEvent(eventId);
    }

    @PutMapping("/event/{eventId}/equipment/{equipmentId}/add")
    public void addEquipmentToEvent(@PathVariable String eventId, @PathVariable Integer equipmentId) {
        eventService.addEquipmentToEvent(eventId, equipmentId);
    }

    @PutMapping("/event/{eventId}/equipment/{equipmentId}/delete")
    public void deleteEquipmentFromEvent(@PathVariable String eventId, @PathVariable Integer equipmentId) {
        eventService.deleteEquipmentFromEvent(eventId, equipmentId);
    }
}
