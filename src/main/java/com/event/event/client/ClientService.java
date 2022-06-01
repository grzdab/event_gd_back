package com.event.event.client;

import com.event.event.address.Address;
import com.event.event.businessBranch.BusinessBranch;
import com.event.event.businessCategory.BusinessCategory;
import com.event.event.client.dao.ClientModel;
import com.event.event.client.dao.ClientRepository;
import com.event.event.clientType.ClientType;
import com.event.event.contact.Contact;
import com.event.event.representative.Representative;
import com.event.event.taxInfo.TaxInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(Client client) {
        ClientModel clientModel = new ClientModel(client.getFullName(), client.getShortName(), client.getContact().getId(), client.isActive(), client.getClientType().getId(), client.getNotes(), client.getTaxInfo().getId());
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
        for (ClientModel model: clientModels){
            clients.add(createClient(model));
        }
        return clients;
    }

    private Client createClient(ClientModel clientModel){
        List<Address> addresses = new ArrayList<>(); // załadowanie adresów klienta
        Contact contact = new Contact(); // załadowanie kontaktu klienta
        ClientType clientType = new ClientType(); // załadowanie clientType dla klienta
        TaxInfo taxInfo = new TaxInfo(); // załadowanie TaxInfo dla klienta
        List<BusinessBranch> businessBranches = new ArrayList<>(); // załadowanie businessBranches dla klienta
        List<BusinessCategory> businessCategories = new ArrayList<>(); // załadowanie businessCategories dla klienta
        List<Representative> representatives = new ArrayList<>(); // załadowanie representatives dla klienta
        return new Client(clientModel.getId(), clientModel.getFullName(), clientModel.getShortName(), addresses, contact,
                clientModel.isActive(), clientType, taxInfo, businessBranches, businessCategories,
                clientModel.getNotes(), representatives);
    }
}
