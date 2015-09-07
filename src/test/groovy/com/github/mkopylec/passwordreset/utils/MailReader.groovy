package com.github.mkopylec.passwordreset.utils

import com.github.mkopylec.passwordreset.api.dto.UserData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.mail.MailProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

import javax.mail.Folder
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Multipart
import javax.mail.Part
import javax.mail.Session
import javax.mail.Store
import javax.mail.search.SearchTerm
import javax.mail.search.SubjectTerm

import static com.github.mkopylec.passwordreset.utils.MailReader.MailPart.CONTENT
import static com.github.mkopylec.passwordreset.utils.MailReader.MailPart.SUBJECT
import static javax.mail.Folder.READ_WRITE
import static javax.mail.Session.getInstance

@Component
@EnableConfigurationProperties(MailProperties)
class MailReader {

    @Autowired
    private MailProperties mail

    String getMailSubject(UserData userData) throws MessagingException, IOException {
        return getMailPart(userData, SUBJECT)
    }

    String getMailContent(UserData userData) throws MessagingException, IOException {
        return getMailPart(userData, CONTENT)
    }

    private String getMailPart(UserData userData, MailPart mailPart) throws MessagingException, IOException {
        Properties props = new Properties()
        props.setProperty('mail.store.protocol', 'imaps')

        Session session = getInstance(props)
        Store store = null
        Folder inbox = null
        try {
            store = session.getStore()
            store.connect(mail.host, userData.email, mail.password)
            inbox = store.getFolder('INBOX')
            inbox.open(READ_WRITE)

            SearchTerm search = new SubjectTerm("$userData.firstName $userData.lastName")
            Message[] messages = inbox.search(search)
            if (messages.length == 0) {
                return null
            }
            Message lastMessage = messages[messages.length - 1]
            return mailPart == SUBJECT ? lastMessage.subject : getBodyText(lastMessage)
        } finally {
            if (inbox != null) {
                inbox.close(false)
            }
            if (store != null) {
                store.close()
            }
        }
    }

    private static String getBodyText(Part p) throws MessagingException, IOException {
        if (p.isMimeType('text/*')) {
            return (String) p.getContent()
        }

        if (p.isMimeType('multipart/alternative')) {
            Multipart mp = (Multipart) p.getContent()
            String text = null
            for (int i = 0; i < mp.getCount(); i++) {
                Part bp = mp.getBodyPart(i)
                if (bp.isMimeType('text/plain')) {
                    if (text == null)
                        text = getBodyText(bp)
                } else if (bp.isMimeType('text/html')) {
                    String s = getBodyText(bp)
                    if (s != null)
                        return s
                } else {
                    return getBodyText(bp)
                }
            }
            return text
        } else if (p.isMimeType('multipart/*')) {
            Multipart mp = (Multipart) p.getContent()
            for (int i = 0; i < mp.getCount(); i++) {
                String s = getBodyText(mp.getBodyPart(i))
                if (s != null)
                    return s
            }
        }

        return null
    }

    static enum MailPart {

        CONTENT, SUBJECT
    }
}
