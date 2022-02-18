package com.bdweb.frumeterapi.controller;

import com.bdweb.frumeterapi.model.*;
import com.bdweb.frumeterapi.model.dto.BuyDTO;
import com.bdweb.frumeterapi.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/v1/buy")
public class BuyController {

    private final UserRepository userRepository;
    private final BuyRepository buyRepository;
    private final ProductRepository productRepository;
    private final AddressRepository addressRepository;
    private final BuyProductQuantityRepository buyProductQuantityRepository;

    public BuyController(UserRepository userRepository, BuyRepository buyRepository,
                         ProductRepository productRepository, AddressRepository addressRepository,
                         BuyProductQuantityRepository buyProductQuantityRepository) {
        this.userRepository = userRepository;
        this.buyRepository = buyRepository;
        this.productRepository = productRepository;
        this.addressRepository = addressRepository;
        this.buyProductQuantityRepository = buyProductQuantityRepository;
    }

    @GetMapping("/payment-methods")
    public Map<PaymentType, String> listPaymentMethods() {
        return PaymentType.PaymentTypeByName();
    }

    @Transactional
    @PostMapping("/save-buy")
    public ResponseEntity saveBuy(@RequestBody BuyDTO buyDTO) {

        User user = userRepository.findById(buyDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar usuário com id: " + buyDTO.getUserId()));

        List<BuyProductQuantity> buyProductQuantities = new ArrayList<>();

        buyDTO.getBuyProductQuantities().forEach(buyProductQuantityDTO -> {
            Product product = productRepository.findById(buyProductQuantityDTO.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar produto com id: " + buyProductQuantityDTO.getProductId()));

            BuyProductQuantity buyProductQuantity = BuyProductQuantity.builder()
                    .quantity(buyProductQuantityDTO.getQuantity())
                    .product(product)
                    .build();

            buyProductQuantities.add(buyProductQuantity);
        });

        Address address = addressRepository.findById(buyDTO.getAddressId())
                .orElseThrow(() -> new EntityNotFoundException("Não foi possivel encontrar endereço com id: " + buyDTO.getAddressId()));

        Buy buy = Buy.builder()
                .date(LocalDate.now())
                .discount(buyDTO.getDiscount())
                .user(user)
                .buyProductQuantities(buyProductQuantities)
                .paymentType(buyDTO.getPaymentType())
                .address(address)
                .build();

        buyRepository.save(buy);
        buyProductQuantities.forEach(buyProductQuantity -> buyProductQuantity.setBuy(buy));
        buyProductQuantityRepository.saveAll(buyProductQuantities);
        return new ResponseEntity<>("Compra salva com sucesso!", HttpStatus.OK);
    }
}
