package com.event.address;

import com.event.address.dao.AddressModel;
import com.event.address.dao.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getAddress(int addressId) {
        AddressModel addressModel = addressRepository.findById(addressId).get();
        return createAddress(addressModel);
    }

    public List<Address> getAllAddressForClient(String clientId) {
        List<AddressModel> addressModels = addressRepository.findAllByClientId(clientId);
        List<Address> addresses = new ArrayList<>();
        for (AddressModel addressModel: addressModels){
            addresses.add(createAddress(addressModel));
        }
        return addresses;
    }

    public Address addAddress(Address address) {
        AddressModel addressModel = new AddressModel(address.getId(), address.getStreet(), address.getStreetNumber(),
                address.getPostalCode(), address.getCity(), address.getCountryId(), address.isPrimary(), address.getClientId());
        addressRepository.save(addressModel);
        address.setId(addressModel.getId());
        return address;
    }

    public Address updateAddress(int addressId, Address newAddress) {
        AddressModel addressFromDB = addressRepository.findById(addressId).get();
        addressFromDB.setStreet(newAddress.getStreet());
        addressFromDB.setStreetNumber(newAddress.getStreetNumber());
        addressFromDB.setPostalCode(newAddress.getPostalCode());
        addressFromDB.setCity(newAddress.getCity());
        addressFromDB.setCountryId(newAddress.getCountryId());
        addressFromDB.setPrimaryAddress(newAddress.isPrimary());
        addressFromDB.setClientId(newAddress.getClientId());
        addressRepository.save(addressFromDB);
        return newAddress;
    }

    public String deleteAddress(int addressId) {
        addressRepository.deleteById(addressId);
        return "DELETED";
    }

    private Address createAddress(AddressModel addressModel){
        return new Address(addressModel.getId(), addressModel.getStreet(), addressModel.getStreetNumber(),
                addressModel.getPostalCode(), addressModel.getCity(), addressModel.getCountryId(),
                addressModel.isPrimaryAddress(), addressModel.getClientId());
    }
}
