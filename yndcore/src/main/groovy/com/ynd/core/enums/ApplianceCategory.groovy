package com.ynd.core.enums

enum ApplianceCategory {

    FAN("fan","ceiling_fan_48"),
    BULB("bulb","bulb_48")

    final String value
    final String img48

    ApplianceCategory(String value,String img48) {
        this.value = value
        this.img48 = img48
    }
}