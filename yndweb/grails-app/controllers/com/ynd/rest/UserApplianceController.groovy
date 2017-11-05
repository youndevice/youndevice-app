package com.ynd.rest

import com.ynd.core.Appliance
import com.ynd.core.Customer
import com.ynd.core.Device
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserApplianceController {

    def springSecurityService

    def listAppliances() {
        Customer user = springSecurityService.currentUser
        List deviceIdList = user.devices.collect { it.id }
        List<Appliance> applianceList = []
        if(deviceIdList){
            applianceList = Appliance.createCriteria().list {
                'device' {
                    'in'('id', deviceIdList)
                }
            } as List<Appliance>
        }
        [applianceList: applianceList]
    }

    def addAppliance(int pinNumber, Long deviceId) {
        [appliance: new Appliance(pinNumber: pinNumber), deviceId: deviceId]
    }

    def saveAppliance(Long applianceInstanceId) {
        println("params ${params}")
        Appliance appliance
        flash.success = "Appliance saved successfully."
        try {
            if (applianceInstanceId) {
                appliance = Appliance.get(applianceInstanceId)
                appliance.properties = params
                appliance.save(failOnError: true)
            } else {
                int pinNumber = params.pinNumber
                appliance = new Appliance(params)
                Device device = Device.get(params.deviceId)
                int count = device.appliances.count { it.pinNumber == pinNumber }
                if (!count) {
                    device.addToAppliances(appliance)
                    device.save(failOnError: true)
                }else{
                    flash.error = "There is another appliance already assigned with this pin."
                }
            }
        } catch (Exception e) {
            log.error(e)
            flash.error = "Error occurred while saving appliance."
        }
        redirect(controller: 'userAppliance', action: 'listAppliances')
    }

    def showAppliance(Long id) {
        Appliance appliance = Appliance.read(id)
        [appliance: appliance]
    }

    def editAppliance(Long id) {
        Appliance appliance = Appliance.read(id)
        render view: 'addAppliance.gsp', model: [appliance: appliance, deviceId: appliance.deviceId]
    }

    def deleteAppliance(Long id) {
        Appliance appliance = Appliance.get(id)
        try {
            flash.success = "Appliance deleted successsfully"
            Device device = appliance.device
            device.removeFromAppliances(appliance)
            appliance.delete()
        } catch (Exception e) {
            log.error(e)
            flash.error = "Some error occurred while deleting appliance"
        }
        redirect(controller: 'userAppliance', action: 'listAppliances')
    }

    def toggleAppliance(Long id) {
        Appliance appliance = Appliance.get(id)
        appliance.webStatus = appliance.webStatus.inverseValue
        appliance.save(failOnError: true)
        redirect(controller: 'userAppliance', action: 'listAppliances')
    }
}
