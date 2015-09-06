package com.github.mkopylec.passwordreset

import com.github.mkopylec.passwordreset.utils.MailReader
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification

import javax.ws.rs.client.Client

import static javax.ws.rs.client.ClientBuilder.newClient

@WebIntegrationTest
@ActiveProfiles("test")
@ContextConfiguration(loader = SpringApplicationContextLoader, classes = PasswordResetService)
class BasicSpec extends Specification {

    @Shared
    private Client client = newClient()
    @Autowired
    private EmbeddedWebApplicationContext context
    @Autowired
    private MongoTemplate mongoTemplate
    @Autowired
    private MailReader mailReader

    protected <T> T findInMongoDB(Object id, Class<T> entityClass) {
        return mongoTemplate.findById(id, entityClass)
    }

    protected String getMailContent(String email) {
        return mailReader.getMailContent(email)
    }

    protected String getMailSubject(String email) {
        return mailReader.getMailSubject(email)
    }

    void cleanup() {
        mongoTemplate.getDb().dropDatabase()
    }
}
