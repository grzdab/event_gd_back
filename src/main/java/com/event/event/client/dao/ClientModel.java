package com.event.event.client.dao;

import com.event.event.address.dao.AddressModel;
import com.event.event.businessBranch.dao.BusinessBranchModel;
import com.event.event.businessCategory.dao.BusinessCategoryModel;
import com.event.event.clientType.dao.ClientTypeModel;
import com.event.event.representative.dao.RepresentativeModel;
import com.event.event.taxInfo.dao.TaxInfoModel;
import io.swagger.models.Contact;

import java.util.List;
import java.util.UUID;

public class ClientModel {

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

}
