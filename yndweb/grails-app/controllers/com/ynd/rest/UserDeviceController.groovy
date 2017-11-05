package com.ynd.rest

import com.ynd.core.Customer
import com.ynd.core.Device
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserDeviceController {

    def springSecurityService

    def listDevices(){
        Customer user = springSecurityService.currentUser
        [deviceList:user?.devices]
    }

    def addDevice() {
    }

    def saveDevice() {
        String deviceId = params.deviceId
        String userFriendlyName = params.userFriendlyName
        Customer user = springSecurityService.currentUser
        Boolean count = user.devices.count { it.deviceId == deviceId } as Boolean
        if (!count) {
            Device device = Device.findByDeviceId(deviceId)
            if (device) {
                flash.success = 'device is successfully added to user'
                device.userFriendlyName = userFriendlyName
                user.addToDevices(device)
                user.save(failOnError: true)
            }else{
                flash.error = "Invalid Device Id"
            }
        } else {
            flash.error = "This device is already added"x
        }
        redirect(controller: 'userDevice', action: 'addDevice')
    }

    def showDevice(Long id){
        Device device = Device.read(id)
        [device:device]
    }
}
