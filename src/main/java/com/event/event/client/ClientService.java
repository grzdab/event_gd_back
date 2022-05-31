package com.event.event.client;

import com.event.event.client.dao.ClientDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public Client addClient(Client client) {

        return null;
    }

    public Client updateClient(String clientId, Client newClient) {
        return null;
    }

    public Client getClient(String clientId) {
        return null;
    }


    public String deleteClient(String clientId) {
        return null;
    }

    public List<Client> getAllClients() {
        return null;
    }
}
