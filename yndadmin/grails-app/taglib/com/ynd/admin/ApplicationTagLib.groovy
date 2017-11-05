package com.ynd.admin

import com.ynd.core.AdminUser
import youndevice.admin.AdminUserService

class ApplicationTagLib {
    AdminUserService adminUserService

    static namespace = "ynd"

    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def loggedInUserFirstName = {
        AdminUser user = adminUserService.getLoggedInUserDetails()
        String userName = user?.username
        out << "${userName}"
    }

}
