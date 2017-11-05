package youndevice.admin

import grails.transaction.Transactional

@Transactional(readOnly = true)
class AdminController {

    AdminUserService adminUserService

    static defaultAction = "dashboard"

    def dashboard(){
        render (view: '/dashboard',model:[user:adminUserService.loggedInUserDetails])
    }
}
