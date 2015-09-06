package com.github.mkopylec.passwordreset.utils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.mail.MailProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component

import javax.mail.Flags
import javax.mail.Folder
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.Multipart
import javax.mail.Part
import javax.mail.Session
import javax.mail.Store

import static com.github.mkopylec.passwordreset.utils.MailReader.MailPart.CONTENT
import static com.github.mkopylec.passwordreset.utils.MailReader.MailPart.SUBJECT
import static javax.mail.Flags.Flag.SEEN
import static javax.mail.Folder.READ_WRITE
import static javax.mail.Session.getInstance

@Component
@EnableConfigurationProperties(MailProperties)
class MailReader {

    @Autowired
    private MailProperties mail

    String getMailSubject(String email) throws MessagingException, IOException {
        return getMailPart(email, SUBJECT)
    }

    String getMailContent(String email) throws MessagingException, IOException {
        return getMailPart(email, CONTENT)
    }

    private String getMailPart(String email, MailPart mailPart) throws MessagingException, IOException {
        Properties props = new Properties()
        props.setProperty('mail.store.protocol', 'imaps')

        Session session = getInstance(props)
        Store store = null
        Folder inbox = null
        try {
            store = session.getStore()
            store.connect(mail.host, email, mail.password)
            inbox = store.getFolder('INBOX')
            inbox.open(READ_WRITE)

            int messageCount = inbox.getMessageCount()
            if (messageCount < 1) {
                return null
            }
            Message lastMessage = inbox.getMessage(messageCount)

            if (lastMessage.getFlags().contains(new Flags(SEEN))) {
                return null
            }
            return mailPart == com.github.mkopylec.passwordreset.utils.MailReader.MailPart.SUBJECT ? lastMessage.subject : getBodyText(lastMessage)
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
