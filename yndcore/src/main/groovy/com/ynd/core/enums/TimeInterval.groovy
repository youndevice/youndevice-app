package com.ynd.core.enums

enum TimeInterval {
    DAYS("DAYS"),
    HOURS("HOURS"),
    MINUTES("MINUTES")

    final String value

    TimeInterval(String value) {
        this.value = value
    }
}