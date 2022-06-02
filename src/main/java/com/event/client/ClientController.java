package com.event.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ClientController {

    private final ClientService service;

    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/client/{clientId}")
    public Client getClient(@PathVariable String clientId) {
        return service.getClient(clientId);
    }

    @GetMapping("/client")
    public List<Client> getAllClients() {
        return service.getAllClients();
    }

    @PostMapping("/client")
    public Client addClient(@RequestBody Client client) {
        return service.addClient(client);
    }

    @PutMapping("/client/{clientId}")
    public Client updateClient(@PathVariable String clientId, @RequestBody Client newClient) {
        return service.updateClient(clientId, newClient);
    }

    @DeleteMapping("/client/{clientId}")
    public String deleteClient(@PathVariable String clientId) {
        return service.deleteClient(clientId);
    }
}
