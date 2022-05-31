package com.event.event.client;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/client/{clientId}")
    Client getClient(@PathVariable int clientId) {
        return service.getClient(clientId);
    }

    @GetMapping("/client")
    List<Client> getAllClients() {
        return service.getAllClients();
    }

    @PostMapping("/client")
    Client addClient(@RequestBody Client client) {
        return service.addClient(client);
    }

    @PutMapping("/client/{clientId}")
    Client updateClient(@PathVariable int clientId, @RequestBody Client newClient) {
        return service.updateClient(clientId, newClient);
    }

    @DeleteMapping("/client/{clientId}")
    String deleteClient(@PathVariable int clientId) {
        return service.deleteClient(clientId);
    }
}
