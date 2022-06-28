package com.event.clientType;

import com.event.clientType.dao.ClientTypeModel;
import com.event.clientType.dao.ClientTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientTypeService {

        ClientTypeRepository clientTypeRepository;

    @Autowired
    public ClientTypeService(ClientTypeRepository clientTypeRepository) {
        this.clientTypeRepository = clientTypeRepository;
    }

    public ClientType addClientType(ClientType clientType) {
        ClientTypeModel model = new ClientTypeModel(clientType.getTypeName());
        clientTypeRepository.save(model);
        //opcional
        clientType.setId(model.getId());
        return clientType;
    }

    public ClientType getClientType(Integer clientTypeId) {
        ClientTypeModel model = clientTypeRepository.findById(clientTypeId).get();
        return createClientType(model);
    }

    public String deleteClientType(Integer clientTypeId) {
        clientTypeRepository.deleteById(clientTypeId);
        return "Deleted";
    }

    public ClientType updateClientType(Integer clientTypeId, ClientType newClientType) {
        ClientTypeModel model = clientTypeRepository.findById(clientTypeId).get();
        model.setTypeName(newClientType.getTypeName());
        clientTypeRepository.save(model);
        return newClientType;
    }

    public List<ClientType> getAllClientTypes(){
        List<ClientType> clientTypes = new ArrayList<>();
        Iterable<ClientTypeModel> clientTypeModels = clientTypeRepository.findAll();
        for (ClientTypeModel model: clientTypeModels){
            //save
            clientTypes.add(createClientType(model));
        }
        return clientTypes;
    }

    private ClientType createClientType(ClientTypeModel clientTypeModel){
        return new ClientType(clientTypeModel.getId(), clientTypeModel.getTypeName());
    }
}
