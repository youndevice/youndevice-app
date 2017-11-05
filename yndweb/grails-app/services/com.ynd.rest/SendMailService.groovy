package com.ynd.rest

import com.ynd.core.dto.EmailDTO
import grails.plugins.mail.MailService
import org.springframework.beans.factory.annotation.Value
import reactor.spring.context.annotation.Consumer
import reactor.spring.context.annotation.Selector

@Consumer
class SendMailService {
    MailService mailService

    @Value('${grails.mail.username:UNKNOWN}')
    String senderEmail

    @Value('${grails.mail.password:UNKNOWN}')
    String senderPassword

    @Selector('sendMailEvent')
    void sendConsumer(EmailDTO emailDTO) {
        if (!(emailDTO && emailDTO.toEmailId)) {
            log.fatal("Failed to send email as the recipient is not valid or is empty")
            return
        }
        log.info "Mail Sending to <${emailDTO.toEmailId}> with Mail Subject: ${emailDTO.subject}"
        if (emailDTO.toEmailId)
            try {
                mailService.sendMail {
                    if (emailDTO.attachment || emailDTO.fileEmailDTOs) {
                        multipart(true)
                    }

                    to emailDTO.toEmailId?.split(",")
                    if (emailDTO.toCCEmailId) {
                        cc emailDTO.toCCEmailId.split(",")
                    }
                    if (emailDTO.toBccEmailId) {
                        bcc emailDTO.toBccEmailId.split(",")
                    }
                    subject emailDTO.subject
                    html emailDTO.body

                    if (emailDTO.attachment) {
                        attach(emailDTO.attachment)
                    }

                    if (emailDTO.fileEmailDTOs) {
                        emailDTO.fileEmailDTOs.each {
                            attachBytes(it.fileName, it.contentType, it.fileBytes)
                        }
                    }
                }
            } catch (Exception exception) {
                log.error("Error occurred while sending mail",exception)
            }
    }

    void sendEmailDTO(EmailDTO emailDTO) {
        notify "sendMailEvent", emailDTO
    }

    void sendEmailDTO(String toEmailId, String content, String subject) {
        sendEmailDTO(new EmailDTO(toEmailId: toEmailId, body: content, subject: subject))
    }
}
