package com.event.client;

import com.event.address.Address;
import com.event.address.AddressService;
import com.event.businessBranch.BusinessBranch;
import com.event.businessBranch.BusinessBranchService;
import com.event.businessCategory.BusinessCategory;
import com.event.businessCategory.BusinessCategoryService;
import com.event.client.dao.ClientModel;
import com.event.client.dao.ClientRepository;
import com.event.clientType.ClientType;
import com.event.clientType.ClientTypeService;
import com.event.contact.Contact;
import com.event.contact.ContactService;
import com.event.representative.Representative;
import com.event.representative.RepresentativeService;
import com.event.taxInfo.TaxInfo;
import com.event.taxInfo.TaxInfoService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import javax.annotation.meta.When;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClientServiceTest {

    private static final String ID = "3b62696f-096b-4cdf-ab42-80d67eb22fe8";

    @Test
    void addClientTest(){
        Client client = new Client(UUID.fromString(ID), "Tomasz",
                "Tom", new ArrayList<>(), new Contact(), false, new ClientType(), new TaxInfo(),
                new ArrayList<>(), new ArrayList<>(), "notes", new ArrayList<>(), "userId");
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        ClientService clientService = new ClientService(clientRepository, null, null, null, null, null, null, null);

        Client newClient = clientService.addClient(client);

        Mockito.verify(clientRepository).save(ArgumentMatchers.any(ClientModel.class));
        assertEquals(client.getFullName(), newClient.getFullName());

    }

    @Test
    void updateClientTest(){
        Client client = new Client(UUID.fromString(ID), "Tomasz",
                "Tom", new ArrayList<>(), new Contact(), false, new ClientType(), new TaxInfo(),
                new ArrayList<>(), new ArrayList<>(), "notes", new ArrayList<>(), "userId");
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        ClientModel model = new ClientModel();
        Mockito.when(clientRepository.findById(UUID.fromString(ID))).thenReturn(Optional.of(model));
        ClientService clientService = new ClientService(clientRepository, null, null, null, null, null, null, null);

        Client newClient = clientService.updateClient(ID, client);

        Mockito.verify(clientRepository).findById(UUID.fromString(ID));
        Mockito.verify(clientRepository).save(ArgumentMatchers.any(ClientModel.class));
        assertEquals(client.getFullName(), newClient.getFullName());

    }

    @Test
    void getClientTest(){
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        ClientModel model = new ClientModel();
        model.setId(UUID.fromString(ID));
        model.setContactId(1);
        model.setClientTypeId(3);
        model.setTaxInfoId(2);
        model.setBusinessBranchesId(List.of(5));
        model.setBusinessCategoriesId(List.of(2));
        Mockito.when(clientRepository.findById(UUID.fromString(ID))).thenReturn(Optional.of(model));
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        Representative representative = new Representative();
        Mockito.when(representativeService.getAllRepresentativesForClient(ID)).thenReturn(List.of(representative));
        AddressService addressService = Mockito.mock(AddressService.class);
        List<Address> addresses = new ArrayList<>();
        Mockito.when(addressService.getAllAddressForClient(ID)).thenReturn(addresses);
        ContactService contactService = Mockito.mock(ContactService.class);
        Contact contact = new Contact();
        Mockito.when(contactService.getContact(1)).thenReturn(contact);
        ClientTypeService clientTypeService = Mockito.mock(ClientTypeService.class);
        ClientType clientType = new ClientType();
        Mockito.when(clientTypeService.getClientType(3)).thenReturn(clientType);
        BusinessBranchService businessBranchService = Mockito.mock(BusinessBranchService.class);
        BusinessBranch businessBranch = new BusinessBranch();
        Mockito.when(businessBranchService.getBusinessBranch(5)).thenReturn(businessBranch);
        BusinessCategoryService businessCategoryService = Mockito.mock(BusinessCategoryService.class);
        BusinessCategory businessCategory = new BusinessCategory();
        Mockito.when(businessCategoryService.getBusinessCategory(2)).thenReturn(businessCategory);
        TaxInfoService taxInfoService = Mockito.mock(TaxInfoService.class);
        TaxInfo taxInfo = new TaxInfo();
        Mockito.when(taxInfoService.getTaxInfo(2)).thenReturn(taxInfo);
        ClientService clientService = new ClientService(clientRepository, representativeService,
                addressService, contactService, clientTypeService, businessBranchService, businessCategoryService,
                taxInfoService);

        Client client = clientService.getClient(ID);

        Mockito.verify(clientRepository).findById(UUID.fromString(ID));
        Mockito.verify(representativeService).getAllRepresentativesForClient(ID);
        Mockito.verify(addressService).getAllAddressForClient(ID);
        Mockito.verify(contactService).getContact(1);
        Mockito.verify(clientTypeService).getClientType(3);
        Mockito.verify(businessBranchService).getBusinessBranch(5);
        Mockito.verify(businessCategoryService).getBusinessCategory(2);
        Mockito.verify(taxInfoService).getTaxInfo(2);
        assertEquals(model.getId(), client.getId());
    }

    @Test
    void deleteClientTest(){
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        ClientService clientService = new ClientService(clientRepository, null, null, null, null, null, null, null);

        String deleteClient = clientService.deleteClient(ID);

        Mockito.verify(clientRepository).deleteById(UUID.fromString(ID));
        assertEquals("DELETED", deleteClient);
    }

    @Test
    void getAllClientsTest(){
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        Representative representative = new Representative();
        Mockito.when(representativeService.getAllRepresentativesForClient(ID)).thenReturn(List.of(representative));
        AddressService addressService = Mockito.mock(AddressService.class);
        List<Address> addresses = new ArrayList<>();
        Mockito.when(addressService.getAllAddressForClient(ID)).thenReturn(addresses);
        ContactService contactService = Mockito.mock(ContactService.class);
        Contact contact = new Contact();
        Mockito.when(contactService.getContact(1)).thenReturn(contact);
        ClientService clientService = new ClientService(clientRepository, representativeService, addressService,
                contactService, null, null, null, null);
        List<ClientModel> clientModels = new ArrayList<>();
        ClientModel model = new ClientModel();
        model.setId(UUID.fromString(ID));
        model.setContactId(1);
        model.setClientTypeId(3);
        model.setTaxInfoId(2);
        model.setBusinessBranchesId(List.of(5));
        model.setBusinessCategoriesId(List.of(2));
        clientModels.add(model);
        Mockito.when(clientRepository.findAll()).thenReturn(clientModels);

        List<MiniClient> clients = clientService.getAllClients();

        Mockito.verify(clientRepository).findAll();
        Mockito.verify(representativeService).getAllMiniRepresentativesForClient(ID);
        Mockito.verify(addressService).getAllAddressForClient(ID);
        assertEquals(1, clients.size());
    }

    @Test
    void addBusinessCategoryToClientTest(){
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        ClientService clientService = new ClientService(clientRepository, null, null, null, null, null, null, null);
        ClientModel model = new ClientModel();
        model.setId(UUID.fromString(ID));
        model.setContactId(1);
        model.setClientTypeId(3);
        model.setTaxInfoId(2);
        List<Integer> listOfBusinessCategoriesId = new ArrayList<>();
        listOfBusinessCategoriesId.add(5);
        model.setBusinessBranchesId(List.of(2));
        model.setBusinessCategoriesId(listOfBusinessCategoriesId);
        Mockito.when(clientRepository.findById(UUID.fromString(ID))).thenReturn(Optional.of(model));

        clientService.addBusinessCategoryToClient(ID, 1);

        Mockito.verify(clientRepository).findById(UUID.fromString(ID));
        Mockito.verify(clientRepository).save(model);
        assertEquals(2, model.getBusinessCategoriesId().size());
    }

    @Test
    void deleteBusinessCategoryToClientTest(){
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        ClientService clientService = new ClientService(clientRepository, null, null, null, null, null, null, null);
        ClientModel model = new ClientModel();
        model.setId(UUID.fromString(ID));
        model.setContactId(1);
        model.setClientTypeId(3);
        model.setTaxInfoId(2);
        List<Integer> listOfBusinessCategoriesId = new ArrayList<>();
        listOfBusinessCategoriesId.add(5);
        listOfBusinessCategoriesId.add(6);
        model.setBusinessBranchesId(List.of(2));
        model.setBusinessCategoriesId(listOfBusinessCategoriesId);
        Mockito.when(clientRepository.findById(UUID.fromString(ID))).thenReturn(Optional.of(model));

        clientService.deleteBusinessCategoryFromClient(ID, 5);

        Mockito.verify(clientRepository).findById(UUID.fromString(ID));
        Mockito.verify(clientRepository).save(model);
        assertEquals(1, model.getBusinessCategoriesId().size());
    }

    @Test
    void addBusinessBranchToClientTest(){
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        ClientService clientService = new ClientService(clientRepository, null, null, null, null, null, null, null);
        ClientModel model = new ClientModel();
        model.setId(UUID.fromString(ID));
        model.setContactId(1);
        model.setClientTypeId(3);
        model.setTaxInfoId(2);
        List<Integer> listOfBusinessBranchesId = new ArrayList<>();
        listOfBusinessBranchesId.add(5);
        model.setBusinessBranchesId(listOfBusinessBranchesId);
        model.setBusinessCategoriesId(List.of(2));
        Mockito.when(clientRepository.findById(UUID.fromString(ID))).thenReturn(Optional.of(model));

        clientService.addBusinessBranchToClient(ID, 1);

        Mockito.verify(clientRepository).findById(UUID.fromString(ID));
        Mockito.verify(clientRepository).save(model);
        assertEquals(2, model.getBusinessBranchesId().size());
    }

    @Test
    void deleteBusinessBranchToClientTest(){
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        ClientService clientService = new ClientService(clientRepository, null, null, null, null, null, null, null);
        ClientModel model = new ClientModel();
        model.setId(UUID.fromString(ID));
        model.setContactId(1);
        model.setClientTypeId(3);
        model.setTaxInfoId(2);
        List<Integer> listOfBusinessBranchesId = new ArrayList<>();
        listOfBusinessBranchesId.add(5);
        listOfBusinessBranchesId.add(7);
        model.setBusinessBranchesId(listOfBusinessBranchesId);
        model.setBusinessCategoriesId(List.of(2));
        Mockito.when(clientRepository.findById(UUID.fromString(ID))).thenReturn(Optional.of(model));

        clientService.deleteBusinessBranchFromClient(ID, 5);

        Mockito.verify(clientRepository).findById(UUID.fromString(ID));
        Mockito.verify(clientRepository).save(model);
        assertEquals(1, model.getBusinessBranchesId().size());
    }

    @Test
    void getAllClientsForBusinessBranchTest(){
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        ClientModel model = new ClientModel();
        model.setId(UUID.fromString(ID));
        model.setContactId(1);
        model.setClientTypeId(3);
        model.setTaxInfoId(2);
        model.setBusinessBranchesId(List.of(7));
        model.setBusinessCategoriesId(List.of(2));
        List<ClientModel> models = new ArrayList<>();
        models.add(model);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        Representative representative = new Representative();
        Mockito.when(representativeService.getAllRepresentativesForClient(ID)).thenReturn(List.of(representative));
        AddressService addressService = Mockito.mock(AddressService.class);
        List<Address> addresses = new ArrayList<>();
        Mockito.when(addressService.getAllAddressForClient(ID)).thenReturn(addresses);
        ContactService contactService = Mockito.mock(ContactService.class);
        Contact contact = new Contact();
        Mockito.when(contactService.getContact(1)).thenReturn(contact);
        ClientTypeService clientTypeService = Mockito.mock(ClientTypeService.class);
        ClientType clientType = new ClientType();
        Mockito.when(clientTypeService.getClientType(3)).thenReturn(clientType);
        BusinessBranchService businessBranchService = Mockito.mock(BusinessBranchService.class);
        BusinessBranch businessBranch = new BusinessBranch();
        Mockito.when(businessBranchService.getBusinessBranch(7)).thenReturn(businessBranch);
        BusinessCategoryService businessCategoryService = Mockito.mock(BusinessCategoryService.class);
        BusinessCategory businessCategory = new BusinessCategory();
        Mockito.when(businessCategoryService.getBusinessCategory(2)).thenReturn(businessCategory);
        TaxInfoService taxInfoService = Mockito.mock(TaxInfoService.class);
        TaxInfo taxInfo = new TaxInfo();
        Mockito.when(taxInfoService.getTaxInfo(2)).thenReturn(taxInfo);
        ClientService clientService = new ClientService(clientRepository, representativeService,
                addressService, contactService, clientTypeService, businessBranchService, businessCategoryService,
                taxInfoService);
        Mockito.when(clientRepository.findAll()).thenReturn(models);

        List<Client> clients = clientService.getAllClientsForBusinessBranch(7);

        Mockito.verify(representativeService).getAllRepresentativesForClient(ID);
        Mockito.verify(addressService).getAllAddressForClient(ID);
        Mockito.verify(contactService).getContact(1);
        Mockito.verify(clientTypeService).getClientType(3);
        Mockito.verify(businessBranchService).getBusinessBranch(7);
        Mockito.verify(businessCategoryService).getBusinessCategory(2);
        Mockito.verify(taxInfoService).getTaxInfo(2);
        Mockito.verify(clientRepository).findAll();
        assertEquals(1, clients.size());
    }

    @Test
    void getAllClientsForBusinessCategoryTest(){
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        ClientModel model = new ClientModel();
        model.setId(UUID.fromString(ID));
        model.setContactId(1);
        model.setClientTypeId(3);
        model.setTaxInfoId(2);
        model.setBusinessBranchesId(List.of(5));
        model.setBusinessCategoriesId(List.of(7));
        List<ClientModel> models = new ArrayList<>();
        models.add(model);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        Representative representative = new Representative();
        Mockito.when(representativeService.getAllRepresentativesForClient(ID)).thenReturn(List.of(representative));
        AddressService addressService = Mockito.mock(AddressService.class);
        List<Address> addresses = new ArrayList<>();
        Mockito.when(addressService.getAllAddressForClient(ID)).thenReturn(addresses);
        ContactService contactService = Mockito.mock(ContactService.class);
        Contact contact = new Contact();
        Mockito.when(contactService.getContact(1)).thenReturn(contact);
        ClientTypeService clientTypeService = Mockito.mock(ClientTypeService.class);
        ClientType clientType = new ClientType();
        Mockito.when(clientTypeService.getClientType(3)).thenReturn(clientType);
        BusinessBranchService businessBranchService = Mockito.mock(BusinessBranchService.class);
        BusinessBranch businessBranch = new BusinessBranch();
        Mockito.when(businessBranchService.getBusinessBranch(5)).thenReturn(businessBranch);
        BusinessCategoryService businessCategoryService = Mockito.mock(BusinessCategoryService.class);
        BusinessCategory businessCategory = new BusinessCategory();
        Mockito.when(businessCategoryService.getBusinessCategory(7)).thenReturn(businessCategory);
        TaxInfoService taxInfoService = Mockito.mock(TaxInfoService.class);
        TaxInfo taxInfo = new TaxInfo();
        Mockito.when(taxInfoService.getTaxInfo(2)).thenReturn(taxInfo);
        ClientService clientService = new ClientService(clientRepository, representativeService,
                addressService, contactService, clientTypeService, businessBranchService, businessCategoryService,
                taxInfoService);
        Mockito.when(clientRepository.findAll()).thenReturn(models);

        List<Client> clients = clientService.getAllClientsForBusinessCategory(7);

        Mockito.verify(representativeService).getAllRepresentativesForClient(ID);
        Mockito.verify(addressService).getAllAddressForClient(ID);
        Mockito.verify(contactService).getContact(1);
        Mockito.verify(clientTypeService).getClientType(3);
        Mockito.verify(businessBranchService).getBusinessBranch(5);
        Mockito.verify(businessCategoryService).getBusinessCategory(7);
        Mockito.verify(taxInfoService).getTaxInfo(2);
        Mockito.verify(clientRepository).findAll();
        assertEquals(1, clients.size());
    }

    @Test
    void getAllClientsForTaxInfoTest(){
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        ClientModel model = new ClientModel();
        model.setId(UUID.fromString(ID));
        model.setContactId(1);
        model.setClientTypeId(3);
        model.setTaxInfoId(7);
        model.setBusinessBranchesId(List.of(5));
        model.setBusinessCategoriesId(List.of(2));
        List<ClientModel> models = new ArrayList<>();
        models.add(model);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        Representative representative = new Representative();
        Mockito.when(representativeService.getAllRepresentativesForClient(ID)).thenReturn(List.of(representative));
        AddressService addressService = Mockito.mock(AddressService.class);
        List<Address> addresses = new ArrayList<>();
        Mockito.when(addressService.getAllAddressForClient(ID)).thenReturn(addresses);
        ContactService contactService = Mockito.mock(ContactService.class);
        Contact contact = new Contact();
        Mockito.when(contactService.getContact(1)).thenReturn(contact);
        ClientTypeService clientTypeService = Mockito.mock(ClientTypeService.class);
        ClientType clientType = new ClientType();
        Mockito.when(clientTypeService.getClientType(3)).thenReturn(clientType);
        BusinessBranchService businessBranchService = Mockito.mock(BusinessBranchService.class);
        BusinessBranch businessBranch = new BusinessBranch();
        Mockito.when(businessBranchService.getBusinessBranch(5)).thenReturn(businessBranch);
        BusinessCategoryService businessCategoryService = Mockito.mock(BusinessCategoryService.class);
        BusinessCategory businessCategory = new BusinessCategory();
        Mockito.when(businessCategoryService.getBusinessCategory(2)).thenReturn(businessCategory);
        TaxInfoService taxInfoService = Mockito.mock(TaxInfoService.class);
        TaxInfo taxInfo = new TaxInfo();
        Mockito.when(taxInfoService.getTaxInfo(7)).thenReturn(taxInfo);
        ClientService clientService = new ClientService(clientRepository, representativeService,
                addressService, contactService, clientTypeService, businessBranchService, businessCategoryService,
                taxInfoService);
        Mockito.when(clientRepository.findAllByTaxInfo(7)).thenReturn(models);

        List<Client> clients = clientService.getAllClientsForTaxInfo(7);

        Mockito.verify(representativeService).getAllRepresentativesForClient(ID);
        Mockito.verify(addressService).getAllAddressForClient(ID);
        Mockito.verify(contactService).getContact(1);
        Mockito.verify(clientTypeService).getClientType(3);
        Mockito.verify(businessBranchService).getBusinessBranch(5);
        Mockito.verify(businessCategoryService).getBusinessCategory(2);
        Mockito.verify(taxInfoService).getTaxInfo(7);
        Mockito.verify(clientRepository).findAllByTaxInfo(7);
        assertEquals(1, clients.size());
    }

    @Test
    void getAllClientsForClientTypeTest(){
        ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
        ClientModel model = new ClientModel();
        model.setId(UUID.fromString(ID));
        model.setContactId(1);
        model.setClientTypeId(7);
        model.setTaxInfoId(2);
        model.setBusinessBranchesId(List.of(5));
        model.setBusinessCategoriesId(List.of(2));
        List<ClientModel> models = new ArrayList<>();
        models.add(model);
        RepresentativeService representativeService = Mockito.mock(RepresentativeService.class);
        Representative representative = new Representative();
        Mockito.when(representativeService.getAllRepresentativesForClient(ID)).thenReturn(List.of(representative));
        AddressService addressService = Mockito.mock(AddressService.class);
        List<Address> addresses = new ArrayList<>();
        Mockito.when(addressService.getAllAddressForClient(ID)).thenReturn(addresses);
        ContactService contactService = Mockito.mock(ContactService.class);
        Contact contact = new Contact();
        Mockito.when(contactService.getContact(1)).thenReturn(contact);
        ClientTypeService clientTypeService = Mockito.mock(ClientTypeService.class);
        ClientType clientType = new ClientType();
        Mockito.when(clientTypeService.getClientType(7)).thenReturn(clientType);
        BusinessBranchService businessBranchService = Mockito.mock(BusinessBranchService.class);
        BusinessBranch businessBranch = new BusinessBranch();
        Mockito.when(businessBranchService.getBusinessBranch(5)).thenReturn(businessBranch);
        BusinessCategoryService businessCategoryService = Mockito.mock(BusinessCategoryService.class);
        BusinessCategory businessCategory = new BusinessCategory();
        Mockito.when(businessCategoryService.getBusinessCategory(2)).thenReturn(businessCategory);
        TaxInfoService taxInfoService = Mockito.mock(TaxInfoService.class);
        TaxInfo taxInfo = new TaxInfo();
        Mockito.when(taxInfoService.getTaxInfo(2)).thenReturn(taxInfo);
        ClientService clientService = new ClientService(clientRepository, representativeService,
                addressService, contactService, clientTypeService, businessBranchService, businessCategoryService,
                taxInfoService);
        Mockito.when(clientRepository.findAllByClientType(7)).thenReturn(models);

        List<Client> clients = clientService.getAllClientsForClientType(7);

        Mockito.verify(representativeService).getAllRepresentativesForClient(ID);
        Mockito.verify(addressService).getAllAddressForClient(ID);
        Mockito.verify(contactService).getContact(1);
        Mockito.verify(clientTypeService).getClientType(7);
        Mockito.verify(businessBranchService).getBusinessBranch(5);
        Mockito.verify(businessCategoryService).getBusinessCategory(2);
        Mockito.verify(taxInfoService).getTaxInfo(2);
        Mockito.verify(clientRepository).findAllByClientType(7);
        assertEquals(1, clients.size());
    }
}