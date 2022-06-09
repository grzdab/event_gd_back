package com.event.companyData;

import com.event.address.Address;
import com.event.companyData.dao.CompanyDataModel;
import com.event.companyData.dao.CompanyDataRepository;
import com.event.contact.Contact;
import com.event.legalEntityType.LegalEntityType;
import com.event.taxInfo.TaxInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public record CompanyDataService(CompanyDataRepository companyDataRepository) {

    @Autowired
    public CompanyDataService(CompanyDataRepository companyDataRepository) {
        this.companyDataRepository = companyDataRepository;
    }

    public CompanyData addCompanyData(CompanyData companyData){
        CompanyDataModel model = new CompanyDataModel(companyData.getFullName(), companyData.getShortName(), companyData.getTaxInfo(),
                companyData.getLegalEntityType(), companyData.getInUse(), companyData.getNotes(), companyData.getAddress(), companyData.getContact());
        companyDataRepository.save(model);
        companyData.setId(model.getId());
        return companyData;
    }

    public CompanyData getCompanyData(UUID id) {
        CompanyDataModel model = companyDataRepository.findById(id).get();
        return createCompanyData(model);
    }

    public String deleteCompanyData(UUID companyDataId) {
        companyDataRepository.deleteById(companyDataId);
        return "Deleted";
    }
    public CompanyData updateCompanyData(UUID companyDataId, CompanyData newCompanyData) {
        CompanyDataModel model = companyDataRepository.findById(companyDataId).get();
        model.setFullName(newCompanyData.getFullName());
        model.setShortName(newCompanyData.getShortName());
        model.setTaxInfo(newCompanyData.getTaxInfo());
//        model.setLegalEntityType(newCompanyData.getLegalEntityType());
        model.setInUse(newCompanyData.getInUse());
        model.setNotes(newCompanyData.getNotes());
        model.setAddress(newCompanyData.getAddress());
        model.setContact(newCompanyData.getContact());
        companyDataRepository.save(model);
        return newCompanyData;
    }

    public List<CompanyData> getAllCompanyData(){
        List<CompanyData> companyDataList = new ArrayList<>();
        Iterable<CompanyDataModel> companyDataModels = companyDataRepository.findAll();
        for (CompanyDataModel model: companyDataModels){
            //save
            companyDataList.add(createCompanyData(model));
        }
        return companyDataList;
    }

    private CompanyData createCompanyData(CompanyDataModel companyDataModel){
        TaxInfo taxInfo = new TaxInfo();
        LegalEntityType legalEntityType = new LegalEntityType();
        Address address = new Address();
        Contact contact = new Contact();
        return new CompanyData(companyDataModel.getId(), companyDataModel.getFullName(), companyDataModel.getShortName(),
                taxInfo, legalEntityType, companyDataModel.getInUse(), companyDataModel.getNotes(), address, contact);
    }
}
