package youndevice.admin

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ApplianceController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Appliance.list(params), model:[applianceCount: Appliance.count()]
    }

    def show(Appliance appliance) {
        respond appliance
    }

    def create() {
        respond new Appliance(params)
    }

    @Transactional
    def save(Appliance appliance) {
        if (appliance == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (appliance.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond appliance.errors, view:'create'
            return
        }

        appliance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'appliance.label', default: 'Appliance'), appliance.id])
                redirect appliance
            }
            '*' { respond appliance, [status: CREATED] }
        }
    }

    def edit(Appliance appliance) {
        respond appliance
    }

    @Transactional
    def update(Appliance appliance) {
        if (appliance == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (appliance.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond appliance.errors, view:'edit'
            return
        }

        appliance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'appliance.label', default: 'Appliance'), appliance.id])
                redirect appliance
            }
            '*'{ respond appliance, [status: OK] }
        }
    }

    @Transactional
    def delete(Appliance appliance) {

        if (appliance == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        appliance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'appliance.label', default: 'Appliance'), appliance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'appliance.label', default: 'Appliance'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
