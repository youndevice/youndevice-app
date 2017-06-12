package youndevice.admin

import grails.transaction.Transactional

@Transactional
class BootStrapService {

    def createDummyAdminUser() {
        log.info(" ****** Inserting Dummy Admin User ****** ")
        AdminUser savedUser = AdminUser.findByUsername("admin@youndevice.com")
        if (!savedUser) {
            def adminRole = new AdminRole(authority: 'ROLE_ADMIN').save(failOnError: true)
            AdminUser adminUser = new AdminUser(username: "admin@youndevice.com",
                    password: "admin")
            adminUser.save(failOnError: true)
            AdminUserAdminRole.create(adminUser, adminRole, true)
        }
    }
}
