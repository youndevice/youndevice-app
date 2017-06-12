package youndevice.admin

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class DeviceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Device.list(params), model:[deviceCount: Device.count()]
    }

    def show(Device device) {
        respond device
    }

    def create() {
        respond new Device(params)
    }

    @Transactional
    def save(Device device) {
        if (device == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (device.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond device.errors, view:'create'
            return
        }

        device.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'device.label', default: 'Device'), device.id])
                redirect device
            }
            '*' { respond device, [status: CREATED] }
        }
    }

    def edit(Device device) {
        respond device
    }

    @Transactional
    def update(Device device) {
        if (device == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (device.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond device.errors, view:'edit'
            return
        }

        device.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'device.label', default: 'Device'), device.id])
                redirect device
            }
            '*'{ respond device, [status: OK] }
        }
    }

    @Transactional
    def delete(Device device) {

        if (device == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        device.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'device.label', default: 'Device'), device.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'device.label', default: 'Device'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
