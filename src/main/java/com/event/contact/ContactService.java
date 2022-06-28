package com.event.contact;

import com.event.contact.contactDao.ContactModel;
import com.event.contact.contactDao.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactService {

    ContactRepository contactRepository;

    static ContactMapper contactMapper;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact addContact(Contact contact) {
        ContactModel model = new ContactModel(contact.getEmail(), contact.getPhone());
        contactRepository.save(model);
        contact.setId(model.getId());
        return contact;
    }

    public Contact getContact(int contactId) {
        ContactModel model = contactRepository.findById(contactId).get();
        return createContact(model);
    }
    public List<Contact> getAllContacts(){
        List<Contact> contacts = new ArrayList<>();
        Iterable<ContactModel> contactModels = contactRepository.findAll();
        for (ContactModel model: contactModels){
            contacts.add(createContact(model));
        }
        return contacts;
    }

    public String deleteContact(int contactId) {
        contactRepository.deleteById(contactId);
        return "Delete";
    }
//    public Contact updateContact(int contactId, Contact newContact){
//        Contact myContact = contactRepository.findById(contactId).get();
//        contactMapper.updateContactFromContact(newContact, Optional.of(myContact));
//        contactRepository.save(myContact);
//        return newContact;
//    }
    public Contact updateContact(int contactId, Contact newContact){
        ContactModel model = contactRepository.findById(contactId).get();
        model.setEmail(newContact.getEmail());
        model.setPhone(newContact.getPhone());
        contactRepository.save(model);
        return newContact;
    }
    private Contact createContact(ContactModel contactModel){
        return new Contact(contactModel.getId(), contactModel.getEmail(), contactModel.getPhone());
    }

}
