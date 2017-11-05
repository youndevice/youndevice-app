package com.ynd.rest

import com.ynd.core.Customer

class ApplicationTagLib {

    def userService

    static defaultEncodeAs = [taglib:'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    static namespace = 'ynd'

    def loggedInUser = {
        Customer user = userService.getCurrentUser()
        String userName = user?.username
        out << "${userName}"
    }
}
