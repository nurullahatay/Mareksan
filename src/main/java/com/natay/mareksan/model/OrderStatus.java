package com.natay.mareksan.model;

/**
 * Created by Ramazan on 28.11.2018.
 */
public enum OrderStatus {
    PREPAIRING("Hazırlanıyor"),
    IN_PROGRESS("İşlem Görüyor"),
    DONE("Tamamlandı");

    private String value;

    OrderStatus(String text) {
        this.value = text;
    }

    public String getValue() {
        return this.value;
    }

    public static OrderStatus getValue(String text) {
        for (OrderStatus orderStatus : OrderStatus.values()) {
            if (orderStatus.value.equalsIgnoreCase(text)) {
                return orderStatus;
            }
        }
        return null;
    }
}
