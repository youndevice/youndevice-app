package yndweb

import com.ynd.core.Authority
import com.ynd.core.Customer
import com.ynd.core.CustomerAuthority

class BootStrap {

    def init = { servletContext ->
        println ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>akura"
        if(!Customer.count()){
            Authority admin = new Authority("ROLE_USER").save(failOnError:true)
            Customer user = new Customer("user@test.com", "user").save(failOnError:true)
            CustomerAuthority.create(user, admin, true)
        }
    }
    def destroy = {
    }
}
