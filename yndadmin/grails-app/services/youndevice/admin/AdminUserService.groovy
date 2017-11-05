package youndevice.admin

import com.ynd.core.AdminUser
import org.springframework.context.i18n.LocaleContextHolder

class AdminUserService {
    def springSecurityService

    AdminUser getLoggedInUserDetails() {
        springSecurityService.getCurrentUser() as AdminUser
    }

}
