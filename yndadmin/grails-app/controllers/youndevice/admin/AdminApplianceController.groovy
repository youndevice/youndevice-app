package youndevice.admin

import com.ynd.core.Appliance
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class AdminApplianceController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [applianceList : Appliance.list()]
    }

    def show(Appliance appliance) {
        respond appliance
    }

    def create() {
        respond new Appliance(params)
    }

    @Transactional
    def save(Appliance appliance) {
        appliance.save(failOnError:true,flush:true)
        redirect(action: 'index')
    }

    def edit(Long id) {
        [appliance:Appliance.read(id)]
    }

    @Transactional
    def update(Appliance appliance) {
        Appliance existingAppliance = Appliance.get(appliance.id)
        existingAppliance.webStatus = appliance.webStatus
        existingAppliance.actualDeviceStatus = appliance.actualDeviceStatus
        existingAppliance.save(failOnError:true,flush:true)
        redirect(controller:'adminAppliance',action: 'index')
    }

    @Transactional
    def delete(Long id) {
        Appliance appliance = Appliance.get(id)
        appliance.delete(failOnError:true,flush:true)
        redirect(controller:'adminAppliance',action: 'index')
    }

}
