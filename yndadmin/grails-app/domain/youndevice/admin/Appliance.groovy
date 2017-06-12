package youndevice.admin

import com.youndevice.core.utils.CoreDateTimeUtil

class Appliance {

    String webStatus
    String actualDeviceStatus
    String userFriendlyName
    String applianceId
    Date dateCreated
    Date lastUpdated

    static belongsTo = [device:Device]

    static constraints = {
        webStatus nullable: true
        actualDeviceStatus nullable: true
        userFriendlyName nullable: true
        applianceId nullable: true
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
