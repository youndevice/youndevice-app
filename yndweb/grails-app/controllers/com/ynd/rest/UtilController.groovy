package com.ynd.rest

import grails.transaction.Transactional

@Transactional(readOnly = true)
class UtilController {

    def test(){
        log.info("******* Test Called *******")
    }
}
