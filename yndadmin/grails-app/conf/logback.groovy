import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender
import ch.qos.logback.core.FileAppender
import grails.util.BuildSettings
import grails.util.Environment
import org.springframework.boot.ApplicationPid
import org.springframework.boot.logging.logback.ColorConverter
import org.springframework.boot.logging.logback.WhitespaceThrowableProxyConverter

import java.nio.charset.Charset

def dateFormat = timestamp("dd-MMM-yyyy")
def targetDir = BuildSettings.TARGET_DIR
def homeDir = System.getProperty("user.home")

if (!System.getProperty("PID")) {
    System.setProperty("PID", (new ApplicationPid()).toString())
}

conversionRule 'clr', ColorConverter
conversionRule 'wex', WhitespaceThrowableProxyConverter

appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        charset = Charset.forName('UTF-8')

        pattern =
                '%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} ' + // Date
                        '%clr(%5p) ' + // Log level
                        '%clr(%property{PID}){magenta} ' + // PID
                        '%clr(---){faint} %clr([%15.15t]){faint} ' + // Thread
                        '%clr(%-40.40logger{39}){cyan} %clr(:){faint} ' + // Logger
                        '%m%n%wex' // Message
    }
}

appender("FULL_STACKTRACE", FileAppender) {
    file = "${homeDir}/ynd_logs_${dateFormat}.log"
    append = true
    encoder(PatternLayoutEncoder) {
        pattern = "%level:%d{yyyy-MM-dd HH:mm:ss.SSS}: %logger - %msg%n"
    }
}

root(ERROR, ['STDOUT'])

if (Environment.isDevelopmentMode() && homeDir) {
    logger("grails.app", ALL, ['STDOUT'], false)
    logger('youndeice.admin', ALL, ['STDOUT'], false)
} else {
    logger("grails.app", ALL, ['FULL_STACKTRACE'], false)
    logger('youndeice.admin', ALL, ['FULL_STACKTRACE'], false)
}