package com.ynd.rest

import com.ynd.core.Authority
import com.ynd.core.Customer
import com.ynd.core.CustomerAuthority
import com.ynd.core.dto.EmailDTO
import com.ynd.core.utils.CommonUtil
import grails.transaction.Transactional
import grails.web.mapping.LinkGenerator

@Transactional
class UserService {

    def springSecurityService
    def sendMailService
    def grailsLinkGenerator

    def registerUser(def params){
        try{
            Customer user = new Customer(params)
            user.text = params.password
            user.token = CommonUtil.uniqueID
            user.enabled =false
            user.save(failOnError:true)
            Authority role = Authority.findByAuthority('ROLE_USER')
            CustomerAuthority.create(user,role,true)
            String confirmUrl = grailsLinkGenerator.link(controller: "user", action: "confirmUser", absolute: true,params:[authCode:user.token])
            String content ="Hi ${user.username}, <br/> Thanks for signing up on You N Device. Please click on below link to confirm your email.<br/> ${confirmUrl}"
            sendMailService.sendEmailDTO(new EmailDTO(toEmailId: user.username, body: content, subject: "User Registration"))
        }catch (Exception e){
            log.error("Error occurred while saving user",e)
        }
    }

    def getCurrentUser(){
        springSecurityService.currentUser
    }
}
