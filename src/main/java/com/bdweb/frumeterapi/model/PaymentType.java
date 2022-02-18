package com.bdweb.frumeterapi.model;

import com.google.common.collect.Maps;

import java.util.Map;


public enum PaymentType {

    BOLETO("Boleto"),
    CARTAO_DE_CREDITO("Cartão de Crédito"),
    PIX("Pix");

    private String nome;

    PaymentType(String nome) {
        this.nome = nome;
    }

    public static Map<PaymentType, String> PaymentTypeByName() {
        Map<PaymentType, String> payments = Maps.newHashMap();

        for (PaymentType paymentType : PaymentType.values()) {
            payments.put(paymentType, paymentType.nome);
        }
        return payments;
    }

}
