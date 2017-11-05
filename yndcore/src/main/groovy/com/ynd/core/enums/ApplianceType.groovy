package com.ynd.core.enums

enum ApplianceType {

    TYPE_0("00",0),
    TYPE_1("01",1),
    TYPE_2("02",2),
    TYPE_3("03",3),
    TYPE_4("04",4),
    TYPE_5("05",5),
    TYPE_6("06",6)

    final String value
    final int typeValue

    ApplianceType(String value,int typeValue) {
        this.value = value
        this.typeValue = typeValue
    }
}