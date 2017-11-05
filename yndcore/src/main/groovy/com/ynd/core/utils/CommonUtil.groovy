package com.ynd.core.utils

import com.google.gson.Gson
import groovy.json.JsonSlurper
import groovy.util.logging.Log4j

import java.util.regex.Matcher
import java.util.regex.Pattern

@Log4j
class CommonUtil {
    public static final Random random = new Random()

    static String getUniqueID() {
        return UUID.randomUUID().toString().replaceAll("-", "")
    }

    static String generateApiUrl(String appUrl, def dto) {
        appUrl + "?" + dto.properties.
                findAll { !(it.key in ["class", "constraintsMap", "constraints", "errors"]) && it.value != null }
                .collect {
            encodeAsURL(it.key) + "=" + (it.value instanceof Date ? CoreDateTimeUtil.convertDateToString(it.value) : encodeAsURL(it.value))
        }
        .join("&")

    }

    static String generateUrlFromMap(String appUrl, Map map) {
        List parameters = []
        map.each { key, value ->
            if (value != null && value != "") {
                if ((key.toString().toLowerCase()).indexOf("date") > -1) {
                    parameters.add(key + "=" + value)
                } else {
                    parameters.add(encodeAsURL(key) + "=" + encodeAsURL(value))
                }
            }
        }
        return appUrl + parameters.join("&")
    }

    static String encodeAsURL(Object string) {
        URLEncoder.encode(string?.toString(), "UTF-8")
    }

    static Map removeNullPropertiesFromObject(def obj) {
        Map map = obj.properties.minus(obj.properties.findAll {
            it.value == null || it.key == "class"
        })
        map
    }

    static def removeNullFromData(def data) {
        Gson gson = new Gson()
        String parsedString = gson.toJson(data)
        def jsonSlurper = new JsonSlurper()
        jsonSlurper.parseText(parsedString)
    }

    static String decodeASURL(Object string) {
        URLDecoder.decode(string.toString(), "UTF-8")
    }

    static Boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
        Matcher mat = pattern.matcher(email)
        return mat.matches()
    }

    static boolean checkIfFieldsAreNull(def obj, List properties = null) {
        List results
        List excludeProperties = ['class', 'metaClass', 'errors', 'constraintsMap', 'constraints']
        if (properties)
            results = properties.collect { obj.getProperty(it) as boolean }
        else {
            results = obj.properties.findAll { !excludeProperties.contains(it.key) }.collect {
                obj.getProperty(it.key) as boolean
            }
        }
        results.contains(true)
    }
}
