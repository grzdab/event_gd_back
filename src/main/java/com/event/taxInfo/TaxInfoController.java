package com.event.taxInfo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaxInfoController {

    private final TaxInfoService taxInfoService;

    public TaxInfoController(TaxInfoService taxInfoService) {
        this.taxInfoService = taxInfoService;
    }

    @GetMapping("/tax-info/{taxInfoId}")
    public TaxInfo getTaxInfo(@PathVariable int taxInfoId) {
        return taxInfoService.getTaxInfo(taxInfoId);
    }

    @GetMapping("/tax-info")
    public List<TaxInfo> getAllTaxInfo() {
        return taxInfoService.getAllTaxInfo();
    }

    @PostMapping("/tax-info")
    public TaxInfo addTaxInfo(@RequestBody TaxInfo taxInfo) {
        return taxInfoService.addTaxInfo(taxInfo);
    }

    @PutMapping("/tax-info/{taxInfoId}")
    public TaxInfo updateTaxInfo(@PathVariable int taxInfoId, @RequestBody TaxInfo newTaxInfo) {
        return taxInfoService.updateTaxInfo(taxInfoId, newTaxInfo);
    }

    @DeleteMapping("/tax-info/{taxInfoId}")
    public String deleteTaxInfo(@PathVariable int taxInfoId) {
        return taxInfoService.deleteTaxInfo(taxInfoId);
    }
}
