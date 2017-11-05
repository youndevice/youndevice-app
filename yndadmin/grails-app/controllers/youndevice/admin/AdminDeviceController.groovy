package youndevice.admin

import com.ynd.core.Device
import com.ynd.core.enums.DeviceType
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class AdminDeviceController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [deviceList : Device.list()]
    }

    def show(Device device) {
        respond device
    }

    def create() {
        respond new Device(params)
    }

    @Transactional
    def save(Device device) {
        device.save(failOnError:true,flush:true)
        redirect(action: 'index')
    }

    def edit(Long id) {
        [device:Device.read(id)]
    }

    @Transactional
    def update(Device device) {
        Device existingDevice = Device.get(device.id)
        existingDevice.deviceType = device.deviceType
        existingDevice.applianceType = device.applianceType
        existingDevice.save(failOnError:true,flush:true)
        redirect(controller:'adminDevice',action: 'index')
    }

    @Transactional
    def delete(Long id) {
        Device device = Device.get(id)
        device.delete(failOnError:true,flush:true)
        redirect(controller:'adminDevice',action: 'index')
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'adminDevice.label', default: 'Device'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
