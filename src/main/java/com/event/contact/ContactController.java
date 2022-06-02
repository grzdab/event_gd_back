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

    @PostMapping("/contact")
    public Contact addContact(@RequestBody Contact contact) {
        return service.addContact(contact);
    }

    @PutMapping("/contact/{contactId}")
    public Contact updateContact(@PathVariable int contactId, @RequestBody Contact newContact) {
        return service.updateContact(contactId, newContact);
    }

    @GetMapping("/contact/{contactId}")
    public Contact getContact(@PathVariable int contactId) {
        return service.getContact(contactId);
    }

    @GetMapping("/contact")
    public List<Contact> getAllContacts() {
        return service.getAllContacts();
    }

    @DeleteMapping("contact/{contactId}")
    public String deleteContact(@PathVariable int contactId) {
        return service.deleteContact(contactId);
    }
}
