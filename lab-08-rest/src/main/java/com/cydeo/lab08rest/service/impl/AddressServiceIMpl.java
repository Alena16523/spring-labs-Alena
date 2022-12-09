package com.cydeo.lab08rest.service.impl;

import com.cydeo.lab08rest.dto.AddressDTO;
import com.cydeo.lab08rest.dto.CustomerDTO;
import com.cydeo.lab08rest.entity.Address;
import com.cydeo.lab08rest.entity.Customer;
import com.cydeo.lab08rest.mapper.MapperUtil;
import com.cydeo.lab08rest.repository.AddressRepository;
import com.cydeo.lab08rest.repository.CustomerRepository;
import com.cydeo.lab08rest.service.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressServiceIMpl implements AddressService {

    public final AddressRepository addressRepository;
    public final MapperUtil mapperUtil;
    public final CustomerRepository customerRepository;

    public AddressServiceIMpl(AddressRepository addressRepository, MapperUtil mapperUtil, CustomerRepository customerRepository) {
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
        this.customerRepository = customerRepository;
    }

    @Override
    public List<AddressDTO> getAddressList() {
        return addressRepository.findAll()
                .stream()
                .map(address -> mapperUtil.convert(address, new AddressDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public AddressDTO updateAddress(AddressDTO addressDTO) {

        addressRepository.findById(addressDTO.getId().toString()); //found address Entity that needs to be updated
        Address addressToSave=mapperUtil.convert(addressDTO, new Address());
        addressRepository.save(addressToSave); //saved updated address

        //getting updated address again from DB to be shown in DTO
        AddressDTO updatedAddress=mapperUtil.convert(addressToSave, new AddressDTO());

        return updatedAddress;
    }

    @Override
    public AddressDTO createAddress(AddressDTO addressDTO) throws Exception {

        Optional<Address> foundAddress = addressRepository.findById(addressDTO.getId().toString());
        if (foundAddress.isPresent()) {
            throw new Exception("Address Already Exists!");
        }

        Address addressToSave = mapperUtil.convert(addressDTO, new Address());
        addressRepository.save(addressToSave);

        return mapperUtil.convert(addressToSave, new AddressDTO());
    }

    @Override
    public List<AddressDTO> getAddressListByStartsWithAddress(String startsWith) {
           List<Address> addressesStartsWith= addressRepository.findAllByStreetStartingWith(startsWith);
        return addressesStartsWith.stream()
                .map(address->mapperUtil.convert(address, new AddressDTO())).collect(Collectors.toList());
    }

    @Override
    public List<AddressDTO> getAddressListByCustomerId(Long id) {
        List<Address> adressesByCustomerId=addressRepository.retrieveByCustomerId(id);
        return adressesByCustomerId.stream()
                .map(address->mapperUtil.convert(address, new AddressDTO())).collect(Collectors.toList());
    }

    @Override
    public AddressDTO getAddressWithCustomerIdAndName(Long id, String name) throws Exception {
        Optional<Customer> customer=customerRepository.findById(id);
        if(!customer.isPresent()){
            throw new Exception("No such customer!");
        }

        return null;
    }
}
