package com.event.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ContactController {

    private final ContactService service;

        @Autowired
        public ContactController(ContactService service) {
            this.service = service;
        }

        @PostMapping("/role/{userId}/contact")
        public Contact addContact(@PathVariable UUID userId, @RequestBody Contact contact) {
            return service.addContact(contact);
        }

        @PutMapping("/role/{userId}/contact/{contactId}")
        public Contact updateContact(@PathVariable UUID userId, @PathVariable int contactId, @RequestBody Contact newContact) {
            return service.updateContact(contactId, newContact);
        }

        @GetMapping("/role/{userId}/contact/{contactId}")
        public Contact getContact(@PathVariable UUID userId, @PathVariable int contactId) {
            return service.getContact(contactId);
        }
        @GetMapping("/role/{userId}/contact")
        public List<Contact> getAllContacts(@PathVariable UUID userId){return service.getAllContacts();}

        @DeleteMapping("/role/{userId}/contact/{contactId}")
        public String deleteContact(@PathVariable UUID userId, @PathVariable int contactId) {
            return service.deleteContact(contactId);
        }
    }
