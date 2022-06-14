package com.event.address;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/address/{addressId}")
    public Address getAddress(@PathVariable int addressId) {
        return addressService.getAddress(addressId);
    }

    @GetMapping("/address/client/{clientId}")
    public List<Address> getAllAddressForClient(@PathVariable String clientId) {
        return addressService.getAllAddressForClient(clientId);
    }

    @PostMapping("/address")
    public Address addAddress(@RequestBody Address address) {
        return addressService.addAddress(address);
    }

    @PutMapping("/address/{addressId}")
    public Address updateAddress(@PathVariable int addressId, @RequestBody Address newAddress) {
        return addressService.updateAddress(addressId, newAddress);
    }

    @DeleteMapping("/address/{addressId}")
    public String deleteAddress(@PathVariable int addressId) {
        return addressService.deleteAddress(addressId);
    }


}
