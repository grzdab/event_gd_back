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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final RepresentativeService representativeService;
    private final AddressService addressService;
    private final ContactService contactService;
    private final ClientTypeService clientTypeService;
    private final BusinessBranchService businessBranchService;
    private final BusinessCategoryService businessCategoryService;
    private final TaxInfoService taxInfoService;

    public ClientService(ClientRepository clientRepository, RepresentativeService representativeService, AddressService addressService, ContactService contactService, ClientTypeService clientTypeService, BusinessBranchService businessBranchService, BusinessCategoryService businessCategoryService, TaxInfoService taxInfoService) {
        this.clientRepository = clientRepository;
        this.representativeService = representativeService;
        this.addressService = addressService;
        this.contactService = contactService;
        this.clientTypeService = clientTypeService;
        this.businessBranchService = businessBranchService;
        this.businessCategoryService = businessCategoryService;
        this.taxInfoService = taxInfoService;
    }

    public Client addClient(Client client) {
        ClientModel clientModel = new ClientModel(client.getFullName(), client.getShortName(),
                client.getContact().getId(), client.isActive(), client.getClientType().getId(),
                client.getNotes(), client.getTaxInfo().getId(), client.getAppUserId());
        clientRepository.save(clientModel);
        client.setId(clientModel.getId());
        return client;
    }

    public Client updateClient(String clientId, Client newClient) {
        ClientModel clientFromDB = clientRepository.findById(UUID.fromString(clientId)).get();
        clientFromDB.setFullName(newClient.getFullName());
        clientFromDB.setShortName(newClient.getShortName());
        clientFromDB.setContactId(newClient.getContact().getId());
        clientFromDB.setActive(newClient.isActive());
        clientFromDB.setClientTypeId(newClient.getClientType().getId());
        clientFromDB.setNotes(newClient.getNotes());
        clientFromDB.setTaxInfoId(newClient.getTaxInfo().getId());
        clientFromDB.setAppUserId(newClient.getAppUserId());
        clientRepository.save(clientFromDB);
        return newClient;
    }

    public Client getClient(String clientId) {
        ClientModel clientModel = clientRepository.findById(UUID.fromString(clientId)).get();
        return createClient(clientModel);
    }

    public String deleteClient(String clientId) {
        clientRepository.deleteById(UUID.fromString(clientId));
        return "DELETED";
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        Iterable<ClientModel> clientModels = clientRepository.findAll();
        for (ClientModel model : clientModels) {
            clients.add(createClient(model));
        }
        return clients;
    }


    private Client createClient(ClientModel clientModel) {
        List<Address> addresses = addressService.getAllAddressForClient(clientModel.getId().toString());
        Contact contact = contactService.getContact(clientModel.getContactId());
        ClientType clientType = clientTypeService.getClientType(clientModel.getClientTypeId());
        TaxInfo taxInfo = taxInfoService.getTaxInfo(clientModel.getTaxInfoId());
        List<BusinessBranch> businessBranches = getAllBusinessBranchForClient(clientModel.getBusinessBranchesId());
        List<BusinessCategory> businessCategories = getAllBusinessCategoryForClient(clientModel.getBusinessCategoriesId());
        List<Representative> representatives =
                representativeService.getAllRepresentativesForClient(clientModel.getId().toString());
        return new Client(clientModel.getId(), clientModel.getFullName(), clientModel.getShortName(), addresses, contact,
                clientModel.isActive(), clientType, taxInfo, businessBranches, businessCategories,
                clientModel.getNotes(), representatives, clientModel.getAppUserId());
    }

    private List<BusinessBranch> getAllBusinessBranchForClient(List<Integer> businessBranchesId){
        List<BusinessBranch> businessBranches = new ArrayList<>();
        for (Integer id: businessBranchesId){
            businessBranches.add(businessBranchService.getBusinessBranch(id));
        }
        return businessBranches;
    }

    private List<BusinessCategory> getAllBusinessCategoryForClient(List<Integer> businessCategoriesId){
        List<BusinessCategory> businessCategories = new ArrayList<>();
        for (Integer id: businessCategoriesId){
            businessCategories.add(businessCategoryService.getBusinessCategory(id));
        }
        return businessCategories;
    }

    public void addBusinessCategoryToClient(String clientId, Integer businessCategoryId){
        ClientModel clientFromDB = clientRepository.findById(UUID.fromString(clientId)).get();
        clientFromDB.getBusinessCategoriesId().add(businessCategoryId);
        clientRepository.save(clientFromDB);
    }

    public void deleteBusinessCategoryFromClient(String clientId, Integer businessCategoryId){
        ClientModel clientFromDB = clientRepository.findById(UUID.fromString(clientId)).get();
        clientFromDB.getBusinessCategoriesId().remove(businessCategoryId);
        clientRepository.save(clientFromDB);
    }

    public void addBusinessBranchToClient(String clientId, Integer businessBranchId){
        ClientModel clientFromDB = clientRepository.findById(UUID.fromString(clientId)).get();
        clientFromDB.getBusinessBranchesId().add(businessBranchId);
        clientRepository.save(clientFromDB);

    }

    public void deleteBusinessBranchFromClient(String clientId, Integer businessBranchId){
        ClientModel clientFromDB = clientRepository.findById(UUID.fromString(clientId)).get();
        clientFromDB.getBusinessBranchesId().remove(businessBranchId);
        clientRepository.save(clientFromDB);
    }

}
