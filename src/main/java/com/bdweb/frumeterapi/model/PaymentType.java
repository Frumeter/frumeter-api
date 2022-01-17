package com.bdweb.frumeterapi.model;

public enum PaymentType {

    BOLETO("Boleto"),
    CARTAO_DE_CREDITO("Cartão de Crédito"),
    PIX("Pix");

    private String type;

    PaymentType(String nome) {
    }
}
