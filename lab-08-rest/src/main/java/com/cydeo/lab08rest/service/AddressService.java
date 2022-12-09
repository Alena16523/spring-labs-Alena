package com.cydeo.lab08rest.service;

import com.cydeo.lab08rest.dto.AddressDTO;
import com.cydeo.lab08rest.dto.CustomerDTO;

import java.util.List;

public interface AddressService {

    List<AddressDTO> getAddressList();
    AddressDTO updateAddress(AddressDTO addressDTO);
    AddressDTO createAddress(AddressDTO addressDTO) throws Exception;
    List<AddressDTO> getAddressListByStartsWithAddress(String startsWith);
    List<AddressDTO> getAddressListByCustomerId(Long id);
    AddressDTO getAddressWithCustomerIdAndName(Long id, String name) throws Exception;

}
