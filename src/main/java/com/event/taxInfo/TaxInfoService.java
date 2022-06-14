package com.event.taxInfo;

import com.event.legalEntityType.LegalEntityType;
import com.event.legalEntityType.LegalEntityTypeService;
import com.event.taxInfo.dao.TaxInfoModel;
import com.event.taxInfo.dao.TaxInfoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaxInfoService {

    private final TaxInfoRepository taxInfoRepository;
    private final LegalEntityTypeService legalEntityTypeService;

    public TaxInfoService(TaxInfoRepository taxInfoRepository, LegalEntityTypeService legalEntityTypeService) {
        this.taxInfoRepository = taxInfoRepository;
        this.legalEntityTypeService = legalEntityTypeService;
    }

    public TaxInfo getTaxInfo(int taxInfoId) {
        TaxInfoModel taxInfoModel = taxInfoRepository.findById(taxInfoId).get();
        return createTaxInfo(taxInfoModel);
    }

    public List<TaxInfo> getAllTaxInfo() {
        Iterable<TaxInfoModel> taxInfoModels = taxInfoRepository.findAll();
        List<TaxInfo> taxInfo = new ArrayList<>();
        for (TaxInfoModel taxInfoModel: taxInfoModels){
            taxInfo.add(createTaxInfo(taxInfoModel));
        }
        return taxInfo;
    }

    public TaxInfo addTaxInfo(TaxInfo taxInfo) {
        TaxInfoModel taxInfoModel = new TaxInfoModel(taxInfo.getId(), taxInfo.getRegon(), taxInfo.getPesel(),
                taxInfo.getNip(), taxInfo.getKrs(), taxInfo.getInsurance(), taxInfo.getLegalEntityType().getId());
        taxInfoRepository.save(taxInfoModel);
        taxInfo.setId(taxInfoModel.getId());
        return taxInfo;
    }

    public TaxInfo updateTaxInfo(int taxInfoId, TaxInfo newTaxInfo) {
        TaxInfoModel taxInfoFromDB = taxInfoRepository.findById(taxInfoId).get();
        taxInfoFromDB.setRegon(newTaxInfo.getRegon());
        taxInfoFromDB.setPesel(newTaxInfo.getPesel());
        taxInfoFromDB.setNip(newTaxInfo.getNip());
        taxInfoFromDB.setKrs(newTaxInfo.getKrs());
        taxInfoFromDB.setInsurance(newTaxInfo.getInsurance());
        taxInfoFromDB.setLegalEntityTypeId(newTaxInfo.getLegalEntityType().getId());
        taxInfoRepository.save(taxInfoFromDB);
        return newTaxInfo;
    }

    public String deleteTaxInfo(int taxInfoId) {
        taxInfoRepository.deleteById(taxInfoId);
        return "DELETED";
    }

    private TaxInfo createTaxInfo(TaxInfoModel taxInfoModel){
        LegalEntityType legalEntityType = legalEntityTypeService.getLegalEntityType(taxInfoModel.getLegalEntityTypeId());
        return new TaxInfo(taxInfoModel.getId(), legalEntityType, taxInfoModel.getRegon(), taxInfoModel.getPesel(),
                taxInfoModel.getNip(), taxInfoModel.getKrs(), taxInfoModel.getInsurance());
    }
}
