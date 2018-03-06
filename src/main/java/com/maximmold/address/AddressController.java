package com.maximmold.address;

import io.github.benas.randombeans.api.EnhancedRandom;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class AddressController {
    private Map<Long, Address> addressHashMap = new ConcurrentHashMap<>();

    @RequestMapping("address")
    public Address address(Long fileNumber){
        Address address = addressHashMap.get(fileNumber);
        if (address != null) {
            address = EnhancedRandom.random(Address.class);
            addressHashMap.putIfAbsent(fileNumber, address);
        }
        return address;
    }
}