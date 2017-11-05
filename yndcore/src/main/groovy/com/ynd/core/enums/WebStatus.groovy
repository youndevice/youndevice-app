package com.ynd.core.enums

enum WebStatus {

    ON("on"),
    OFF("off")

    final String value

    WebStatus(String value) {
        this.value = value
    }

    WebStatus getInverseValue(){
        (this == ON ) ? OFF : ON
    }
}