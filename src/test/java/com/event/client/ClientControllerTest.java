package com.event.client;

import com.event.clientType.ClientType;
import com.event.contact.Contact;
import com.event.taxInfo.TaxInfo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClientControllerTest {

    private static final String ID = "3b62696f-096b-4cdf-ab42-80d67eb22fe8";

    @Test
    void getAllClientsTest() {
        //arrange
        List<MiniClient> clients = new ArrayList<>();
        MiniClient miniClient = new MiniClient(UUID.randomUUID(), "name", new ArrayList<>(),
                new Contact(), new ArrayList<>(), "userId");
        clients.add(miniClient);
        ClientService clientService = Mockito.mock(ClientService.class);
        Mockito.when(clientService.getAllClients()).thenReturn(clients);
        ClientController clientController = new ClientController(clientService);

        //act
        List<MiniClient> allClients = clientController.getAllClients();

        //assert
        assertEquals(clients.size(), allClients.size());
        Mockito.verify(clientService).getAllClients();
    }

    @Test
    void getClientByIdTest() {
        //arrange
        Client client = new Client(UUID.fromString(ID), "Tomasz",
                "Tom", new ArrayList<>(), new Contact(), false, new ClientType(), new TaxInfo(),
                new ArrayList<>(), new ArrayList<>(), "notes", new ArrayList<>(), "userId");
        ClientService clientService = Mockito.mock(ClientService.class);
        Mockito.when(clientService.getClient(ID)).thenReturn(client);
        ClientController clientController = new ClientController(clientService);

        //act
        Client newClient = clientController.getClient(ID);

        //assert
        assertEquals(client.getFullName(), newClient.getFullName());
        Mockito.verify(clientService).getClient(ID);
    }

    @Test
    void updateClientByIdTest() {
        //arrange
        Client newClient = new Client(UUID.fromString(ID), "Tomasz Kowalski",
                "Tom", new ArrayList<>(), new Contact(), false, new ClientType(), new TaxInfo(),
                new ArrayList<>(), new ArrayList<>(), "notes", new ArrayList<>(), "userId");
        ClientService clientService = Mockito.mock(ClientService.class);
        Mockito.when(clientService.updateClient(ID, newClient)).thenReturn(newClient);
        ClientController clientController = new ClientController(clientService);

        //act
        Client updateClient = clientController.updateClient(ID, newClient);

        //assert
        assertEquals(newClient.getFullName(), updateClient.getFullName());
        Mockito.verify(clientService).updateClient(ID, newClient);
    }

    @Test
    void addClientTest() {
        //arrange
        Client client = new Client(UUID.fromString(ID), "Tomasz",
                "Tom", new ArrayList<>(), new Contact(), false, new ClientType(), new TaxInfo(),
                new ArrayList<>(), new ArrayList<>(), "notes", new ArrayList<>(), "userId");
        ClientService clientService = Mockito.mock(ClientService.class);
        Mockito.when(clientService.addClient(client)).thenReturn(client);
        ClientController clientController = new ClientController(clientService);

        //act
        Client addClient = clientController.addClient(client);

        //assert
        assertEquals(addClient.getFullName(), client.getFullName());
        Mockito.verify(clientService).addClient(client);
    }

    @Test
    void deleteClientByIdTest() {
        //arrange
        ClientService clientService = Mockito.mock(ClientService.class);
        Mockito.when(clientService.deleteClient(ID)).thenReturn("DELETED");
        ClientController clientController = new ClientController(clientService);

        //act
        String deleteClient = clientController.deleteClient(ID);

        //assert
        assertEquals(deleteClient, "DELETED");
        Mockito.verify(clientService).deleteClient(ID);
    }

    @Test
    void addBusinessBranchToClientTest() {
        //arrange
        ClientService clientService = Mockito.mock(ClientService.class);
        ClientController clientController = new ClientController(clientService);

        //act
        clientController.addBusinessBranchToClient(ID, 1);

        //assert
        Mockito.verify(clientService).addBusinessBranchToClient(ID, 1);
    }

    @Test
    void removeBusinessBranchFromClientTest() {
        //arrange
        ClientService clientService = Mockito.mock(ClientService.class);
        ClientController clientController = new ClientController(clientService);

        //act
        clientController.deleteBusinessBranchFromClient(ID, 1);

        //assert
        Mockito.verify(clientService).deleteBusinessBranchFromClient(ID, 1);
    }

    @Test
    void addBusinessCategoryToClientTest() {
        //arrange
        ClientService clientService = Mockito.mock(ClientService.class);
        ClientController clientController = new ClientController(clientService);

        //act
        clientController.addBusinessCategoryToClient(ID, 1);

        //assert
        Mockito.verify(clientService).addBusinessCategoryToClient(ID, 1);
    }

    @Test
    void removeBusinessCategoryFromClientTest() {
        //arrange
        ClientService clientService = Mockito.mock(ClientService.class);
        ClientController clientController = new ClientController(clientService);

        //act
        clientController.deleteBusinessCategoryFromClient(ID, 1);

        //assert
        Mockito.verify(clientService).deleteBusinessCategoryFromClient(ID, 1);
    }
}