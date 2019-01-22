package com.natay.mareksan.model;

public enum OrderStatus {
    PREPAIRING("Hazirlaniyor"),
    IN_PROGRESS("İslem Goruyor"),
    DONE("Tamamlandi"),
    DELIVERED("Teslim Edildi");

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
