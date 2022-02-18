package com.bdweb.frumeterapi.controller;

import com.bdweb.frumeterapi.model.Address;
import com.bdweb.frumeterapi.model.User;
import com.bdweb.frumeterapi.model.dto.AddressDTO;
import com.bdweb.frumeterapi.repository.AddressRepository;
import com.bdweb.frumeterapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/adress")
public class AddressController {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressController(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    @GetMapping("/get/{userId}")
    public List<Address> findByUserEmail(@PathVariable("userId") Long userId) {
        return addressRepository.findByUserId(userId);
    }

    @Transactional
    @PostMapping("/save")
    public ResponseEntity saveAddres(@RequestBody AddressDTO addressDTO) {
        Optional<User> user = userRepository.findById(addressDTO.getUserId());

        try {
            if(user.isPresent()){
                Address address = Address.builder()
                        .street(addressDTO.getStreet())
                        .number(addressDTO.getNumber())
                        .neighborhood(addressDTO.getNeighborhood())
                        .city(addressDTO.getCity())
                        .state(addressDTO.getState())
                        .postalCode(addressDTO.getPostalCode())
                        .complement(addressDTO.getComplement())
                        .referencePoint(addressDTO.getReferencePoint())
                        .user(user.get())
                        .build();

                addressRepository.save(address);
                return new ResponseEntity<>("Endereço salvo com sucesso!", HttpStatus.OK);
            }

            return new ResponseEntity<>("Nenhum usuário encontrado com esse id", HttpStatus.NOT_FOUND);
        } catch (Exception exception) {
            log.error("Erro ao salvar endereço", exception);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao cadastrar produto, por favor tente novamente.");
        }
    }
}
