package com.cydeo.lab08rest.controller;

import com.cydeo.lab08rest.dto.AddressDTO;
import com.cydeo.lab08rest.model.ResponseWrapper;
import com.cydeo.lab08rest.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    public final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllAddresses(){
        List<AddressDTO> addressDTOList=addressService.getAddressList();
        return ResponseEntity.ok(new ResponseWrapper("Addresses are successfully retrieved", addressDTOList, HttpStatus.OK ));
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper> updateAddress(@RequestBody AddressDTO addressDTO){
        addressService.updateAddress(addressDTO);
        return ResponseEntity.ok(new ResponseWrapper("string", HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper> createAddress(@RequestBody AddressDTO addressDTO) throws Exception {
        addressService.createAddress(addressDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("string", HttpStatus.OK));
    }

    @GetMapping("/startsWith/{address}")
    public ResponseEntity<ResponseWrapper> getAddressListByStartsWithAddress(String addressDTO){
        addressService.getAddressListByStartsWithAddress(addressDTO);
        return ResponseEntity.ok(new ResponseWrapper("Addresses starting with Address are retrieved", HttpStatus.OK));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<ResponseWrapper> getAddressListByCustomerId(@PathVariable("id") Long id){
        List<AddressDTO> addressDTOList=addressService.getAddressListByCustomerId(id);
        return ResponseEntity.ok(new ResponseWrapper("Addresses are retrived", addressDTOList, HttpStatus.OK));
    }

//    @GetMapping("/customer/{customer}/name/{name}")
//    public ResponseEntity<ResponseWrapper> getAddressWithCustomerIdAndName(@PathVariable("customer") Long id, @PathVariable("name") String name){
//        addressService.
//    }



}
