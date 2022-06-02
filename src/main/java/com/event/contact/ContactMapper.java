package com.event.contact;

import com.event.contact.contactDao.ContactModel;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @BeanMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    void updateContactFromContact(com.event.contact.Contact contact, @MappingTarget Optional<ContactModel> entity);
}
