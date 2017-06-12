package youndevice.admin

import com.youndevice.core.enums.ApplianceType
import com.youndevice.core.enums.DeviceType
import com.youndevice.core.utils.CoreDateTimeUtil

class Device {

    Boolean enabled
    String userFriendlyName
    String status
    String deviceId
    Date dateCreated
    Date lastUpdated
    DeviceType deviceType
    ApplianceType applianceType = ApplianceType.TYPE_0

//    static belongsTo = [user:User]
    static constraints = {
        enabled nullable: true
        deviceType nullable: true
        userFriendlyName nullable: true
        status nullable: true
        deviceType nullable: true
        applianceType nullable: true
        deviceId nullable: true
    }

    def beforeInsert() {
        if (!this.deviceId) {
            String deviceId = generateDeviceId(this)
            this.deviceId = deviceId
        }
    }

    String generateDeviceId(Device device) {
        Integer count = createCriteria().get {
            projections {
                max "id"
            }
        }
        return ("D${deviceType.value}${applianceType.value}${device.dateCreated?.format(CoreDateTimeUtil.REFERENCE_CODE_DATE_FORMAT)}" + (count ? 1000 + count : 1000))
    }
}

