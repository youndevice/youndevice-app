package youndevice.admin

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional

@Transactional
class BootStrapService {
    SpringSecurityService springSecurityService

    def createDummyAdminUser() {
        log.info(" ****** Inserting Dummy Admin User ****** ")
        com.ynd.core.AdminUser savedUser = com.ynd.core.AdminUser.findByUsername("admin@test.com")
        if (!savedUser) {
            def adminRole = new com.ynd.core.AdminRole(authority: 'ROLE_ADMIN').save(failOnError: true)
            com.ynd.core.AdminUser adminUser = new com.ynd.core.AdminUser(username: "admin@test.com",
                    password: springSecurityService.encodePassword("admin"),enabled: true)
            adminUser.save(failOnError: true)
            com.ynd.core.AdminUserAdminRole.create(adminUser, adminRole, true)
        }
    }
}
