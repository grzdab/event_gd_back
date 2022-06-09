package com.event.legalEntityType;

import com.event.legalEntityType.dao.LegalEntityTypeModel;
import com.event.legalEntityType.dao.LegalEntityTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public record LegalEntityTypeService(LegalEntityTypeRepository legalEntityTypeRepository)
{

    @Autowired
    public LegalEntityTypeService(LegalEntityTypeRepository legalEntityTypeRepository) {
        this.legalEntityTypeRepository = legalEntityTypeRepository;
    }

    public LegalEntityType addLegalEntityType(LegalEntityType legalEntityType) {
        LegalEntityTypeModel model = new LegalEntityTypeModel(legalEntityType.getTypeName());
        legalEntityTypeRepository.save(model);
        //opcional
        legalEntityType.setId(model.getId());
        return legalEntityType;
    }

    public LegalEntityType getLegalEntityType(String legalEntityTypeId) {
        LegalEntityTypeModel model = legalEntityTypeRepository.findById(legalEntityTypeId).get();
        return createLegalEntityType(model);
    }

    public String deleteLegalEntityType(String legalEntityTypeId) {
        legalEntityTypeRepository.deleteById(legalEntityTypeId);
        return "Deleted";
    }

    public LegalEntityType updateLegalEntityType(String legalEntityTypeId, LegalEntityType newLegalEntityType) {
        LegalEntityTypeModel model = legalEntityTypeRepository.findById(legalEntityTypeId).get();
        model.setTypeName(newLegalEntityType.getTypeName());
        legalEntityTypeRepository.save(model);
        return newLegalEntityType;
    }

    public List<LegalEntityType> getAllLegalEntityTypes(){
        List<LegalEntityType> legalEntityTypes = new ArrayList<>();
        Iterable<LegalEntityTypeModel> legalEntityTypeModels = legalEntityTypeRepository.findAll();
        for (LegalEntityTypeModel model: legalEntityTypeModels){
            //save
            legalEntityTypes.add(createLegalEntityType(model));
        }
        return legalEntityTypes;
    }

    private LegalEntityType createLegalEntityType(LegalEntityTypeModel legalEntityTypeModel){
        return new LegalEntityType(legalEntityTypeModel.getId(), legalEntityTypeModel.getTypeName());
    }
}

