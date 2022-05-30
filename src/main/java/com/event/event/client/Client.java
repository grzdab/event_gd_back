package com.event.event.client;

import com.event.event.address.dao.AddressModel;
import com.event.event.businessBranch.dao.BusinessBranchModel;
import com.event.event.businessCategory.dao.BusinessCategoryModel;
import com.event.event.clientType.dao.ClientTypeModel;
import com.event.event.representative.dao.RepresentativeModel;
import com.event.event.taxInfo.dao.TaxInfoModel;
import io.swagger.models.Contact;

import java.util.List;
import java.util.UUID;

public class Client {

    UUID id;
    String fullName;
    String shortName;
    List<AddressModel> addresses;
    Contact contact;
    boolean isActive;
    ClientTypeModel clientType;
    TaxInfoModel taxInfo;
    List<BusinessBranchModel> businessBranches;
    List<BusinessCategoryModel> businessCategories;
    String notes;
    List<RepresentativeModel> representatives;

    public Client(UUID id, String fullName, String shortName, List<AddressModel> addresses, Contact contact, boolean isActive, ClientTypeModel clientType, TaxInfoModel taxInfo, List<BusinessBranchModel> businessBranches, List<BusinessCategoryModel> businessCategories, String notes, List<RepresentativeModel> representatives) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.addresses = addresses;
        this.contact = contact;
        this.isActive = isActive;
        this.clientType = clientType;
        this.taxInfo = taxInfo;
        this.businessBranches = businessBranches;
        this.businessCategories = businessCategories;
        this.notes = notes;
        this.representatives = representatives;
    }

    public Client() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<AddressModel> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressModel> addresses) {
        this.addresses = addresses;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ClientTypeModel getClientType() {
        return clientType;
    }

    public void setClientType(ClientTypeModel clientType) {
        this.clientType = clientType;
    }

    public TaxInfoModel getTaxInfo() {
        return taxInfo;
    }

    public void setTaxInfo(TaxInfoModel taxInfo) {
        this.taxInfo = taxInfo;
    }

    public List<BusinessBranchModel> getBusinessBranches() {
        return businessBranches;
    }

    public void setBusinessBranches(List<BusinessBranchModel> businessBranches) {
        this.businessBranches = businessBranches;
    }

    public List<BusinessCategoryModel> getBusinessCategories() {
        return businessCategories;
    }

    public void setBusinessCategories(List<BusinessCategoryModel> businessCategories) {
        this.businessCategories = businessCategories;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<RepresentativeModel> getRepresentatives() {
        return representatives;
    }

    public void setRepresentatives(List<RepresentativeModel> representatives) {
        this.representatives = representatives;
    }
}
