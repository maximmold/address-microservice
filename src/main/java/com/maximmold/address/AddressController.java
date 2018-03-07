package com.maximmold.address;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class AddressController {
    private Map<Long, Address> addressHashMap = new ConcurrentHashMap<>();

    @RequestMapping("address/{fileNumber}")
    public Address address(@PathVariable Long fileNumber){
        Address address = addressHashMap.get(fileNumber);
        if (address == null) {
            address = EnhancedRandom.random(Address.class);
            address.setFileNumber(fileNumber);
            addressHashMap.putIfAbsent(fileNumber, address);
        }
        return address;
    }
}