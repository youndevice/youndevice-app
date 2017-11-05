package com.ynd.core

import com.ynd.core.enums.ApplianceCategory
import com.ynd.core.enums.WebStatus
import com.ynd.core.utils.CoreDateTimeUtil

class Appliance {
    WebStatus webStatus = WebStatus.OFF
    String actualDeviceStatus
    String userFriendlyName
    String applianceId
    Date dateCreated
    Date lastUpdated
    int pinNumber = 0
    ApplianceCategory category

    static belongsTo = [device:Device]

    static constraints = {
        webStatus nullable: true
        actualDeviceStatus nullable: true
        userFriendlyName nullable: true
        applianceId nullable: true
        category nullable: true
        device nullable: true
    }

    def beforeInsert() {
        if (!this.applianceId) {
            String applianceId = generateApplianceId(this)
            this.applianceId = applianceId
        }
    }

    String generateApplianceId(Appliance appliance) {
        Integer count = createCriteria().get {
            projections {
                max "id"
            }
        }
        return ("A"+ "${appliance.dateCreated?.format(CoreDateTimeUtil.REFERENCE_CODE_DATE_FORMAT)}" + (count ? 1000 + count : 1000))
    }
}



