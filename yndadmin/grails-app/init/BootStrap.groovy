import youndevice.admin.BootStrapService

class BootStrap {

    BootStrapService bootStrapService

    def init = { servletContext ->
        bootStrapService.createDummyAdminUser()
    }
    def destroy = {
    }
}
