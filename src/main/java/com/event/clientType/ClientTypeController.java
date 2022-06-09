package com.event.clientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientTypeController {
    private final ClientTypeService service;

    @Autowired
    public ClientTypeController(ClientTypeService service) {
        this.service = service;
    }

    @PostMapping("/admin/clientType")
    public ClientType addClientType(@RequestBody ClientType clientType){
        return service.addClientType(clientType);
    }

    @PutMapping("/admin/clientType/{clientTypeId}")
    public ResponseEntity<Object> updateClientType(@PathVariable String clientTypeId, @RequestBody ClientType newClientType) {
        ClientType updateClientType = service.updateClientType(clientTypeId, newClientType);
        if (updateClientType == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updateClientType);
        }
    }

    @GetMapping("/admin/clientType/{clientTypeId}")
    public ClientType getClientType(@PathVariable String clientTypeId){
        return service.getClientType(clientTypeId);
    }

    @GetMapping("/admin/clientType")
    public List<ClientType> getAllClientTypes(){
        return service.getAllClientTypes();
    }

    @DeleteMapping("/admin/clientType/{clientTypeId}")
    public String deleteClientType(@PathVariable String clientTypeId){
        return service.deleteClientType(clientTypeId);
    }

}
