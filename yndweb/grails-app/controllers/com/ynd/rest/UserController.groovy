package com.ynd.rest

import com.ynd.core.Customer
import com.ynd.core.utils.CommonUtil
import grails.transaction.Transactional


@Transactional(readOnly = true)
class UserController {

    def userService
    def springSecurityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def registerUser() {
        String emailId = params.username
        Boolean isEmailValid = CommonUtil.isValidEmail(emailId)
        Boolean count = Customer.countByUsername(emailId) as Boolean
        if (isEmailValid) {
            if (count) {
                flash.error = 'You are already registered with us. kindly login to continue.'
            } else {
                userService.registerUser(params)
                render(view: '/verifyUser')
            }
        } else {
            flash.error = "Invalid Email"
            render(view: '/signup')
        }
    }

    def home() {
        Customer user = springSecurityService.currentUser
        def deviceList = user?.devices
        List applianceList = deviceList.collect {
            it.appliances
        }
        applianceList = applianceList.flatten()
        [deviceList: deviceList, applianceList: applianceList]
    }

    def confirmUser(String authCode) {
        String message = "success"
        Boolean isAuthenticated = false
        if (authCode) {
            Customer user = Customer.findByToken(authCode)
            if (user) {
                user.enabled = true
                user.save(failOnError: true)
                isAuthenticated = true
            } else {
                message = "Invalid Auth Code."
            }
        } else {
            message = "Auth code is required."
        }
        [isAuthenticated: isAuthenticated, message: message]
    }

}
