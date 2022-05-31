package com.event.event.client;

import com.event.event.address.Address;
import com.event.event.businessBranch.BusinessBranch;
import com.event.event.businessCategory.BusinessCategory;
import com.event.event.client.dao.ClientDAO;
import com.event.event.client.dao.ClientModel;
import com.event.event.clientType.ClientType;
import com.event.event.contact.Contact;
import com.event.event.representative.Representative;
import com.event.event.taxInfo.TaxInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public Client addClient(Client client) {
        ClientModel clientModel = new ClientModel(client.getFullName(), client.getShortName(), client.getContact().getId(), client.isActive(), client.getClientType().getId(), client.getNotes(), client.getTaxInfo().getId());
        clientDAO.add(clientModel);
        client.setId(clientModel.getId());
        return client;
    }

    public Client updateClient(String clientId, Client newClient) {
        clientDAO.update(clientId, newClient);
        return newClient;
    }

    public Client getClient(String clientId) {
        ClientModel clientModel = clientDAO.find(clientId);
        return createClient(clientModel);
    }

    public String deleteClient(String clientId) {
        clientDAO.remove(clientId);
        return "DELETED";
    }

    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        List<ClientModel> clientModels= clientDAO.getAll();
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
