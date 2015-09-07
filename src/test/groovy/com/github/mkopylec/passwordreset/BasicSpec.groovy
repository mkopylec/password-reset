package com.github.mkopylec.passwordreset

import com.github.mkopylec.passwordreset.api.dto.UserData
import com.github.mkopylec.passwordreset.utils.MailReader
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared
import spock.lang.Specification

import java.lang.reflect.ParameterizedType

@WebIntegrationTest
@ActiveProfiles("test")
@ContextConfiguration(loader = SpringApplicationContextLoader, classes = PasswordResetApp)
abstract class BasicSpec<E> extends Specification {

    @Shared
    private Class<E> endpointClass
    @Autowired
    private EmbeddedWebApplicationContext context
    @Autowired
    private MongoTemplate mongoTemplate
    @Autowired
    private MailReader mailReader

    void setupSpec() {
        endpointClass = (Class<E>) ((ParameterizedType) this.class.genericSuperclass).actualTypeArguments[0]
    }

    protected E getEndpoint() {
        return new ResteasyClientBuilder()
                .build()
                .target("http://localhost:$context.embeddedServletContainer.port")
                .proxy(endpointClass)
    }

    protected <T> T findInMongoDB(Object id, Class<T> entityClass) {
        return mongoTemplate.findById(id, entityClass)
    }

    protected String getMailContent(UserData userData) {
        return mailReader.getMailContent(userData)
    }

    protected String getMailSubject(UserData userData) {
        return mailReader.getMailSubject(userData)
    }

    void cleanup() {
        mongoTemplate.getDb().dropDatabase()
    }
}
