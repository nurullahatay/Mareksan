package com.natay.mareksan.model;

public enum OrderType {
    ADDITION("Adisyon"),
    BROCHURE("Broşür"),
    BILL("Fatura"),
    BUSINESS_CARD("Kartvizit"),
    STAMP("Kaşe"),
    CALENDAR("Takvim");

    private String value;

    OrderType(String text) {
        this.value = text;
    }

    public String getValue() {
        return this.value;
    }

    public static OrderType getValue(String text) {
        for (OrderType orderType : OrderType.values()) {
            if (orderType.value.equalsIgnoreCase(text)) {
                return orderType;
            }
        }
        return null;
    }
}
