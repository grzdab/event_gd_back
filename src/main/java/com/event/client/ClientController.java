package com.event.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:3000")
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
    public List<MiniClient> getAllClients() {
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



    @PutMapping("/client/{clientId}/business-branch/{businessBranchId}/add")
    public void addBusinessBranchToClient(@PathVariable String clientId, @PathVariable Integer businessBranchId) {
        service.addBusinessBranchToClient(clientId, businessBranchId);
    }

    @PutMapping("/client/{clientId}/business-branch/{businessBranchId}/delete")
    public void deleteBusinessBranchFromClient(@PathVariable String clientId, @PathVariable Integer businessBranchId) {
        service.deleteBusinessBranchFromClient(clientId, businessBranchId);
    }

    @PutMapping("/client/{clientId}/business-category/{businessCategoryId}/add")
    public void addBusinessCategoryToClient(@PathVariable String clientId, @PathVariable Integer businessCategoryId) {
        service.addBusinessCategoryToClient(clientId, businessCategoryId);
    }

    @PutMapping("/client/{clientId}/business-category/{businessCategoryId}/delete")
    public void deleteBusinessCategoryFromClient(@PathVariable String clientId, @PathVariable Integer businessCategoryId) {
        service.deleteBusinessCategoryFromClient(clientId, businessCategoryId);
    }

    @GetMapping("/client/business-branch/{businessBranchId}")
    public List<Client> getAllClientsForBusinessBranch(@PathVariable int businessBranchId) {
        return service.getAllClientsForBusinessBranch(businessBranchId);
    }

    @GetMapping("/client/business-category/{businessCategoryId}")
    public List<Client> getAllClientsForBusinessCategory(@PathVariable int businessCategoryId) {
        return service.getAllClientsForBusinessCategory(businessCategoryId);
    }

    @GetMapping("/client/tax-info/{taxInfoId}")
    public List<Client> getAllClientsForTaxInfo(@PathVariable int taxInfoId) {
        return service.getAllClientsForTaxInfo(taxInfoId);
    }

    @GetMapping("/client/client-type/{clientTypeId}")
    public List<Client> getAllClientsForClientType(@PathVariable int clientTypeId) {
        return service.getAllClientsForClientType(clientTypeId);
    }
}
