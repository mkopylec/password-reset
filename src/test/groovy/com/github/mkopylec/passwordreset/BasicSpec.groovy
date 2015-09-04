package com.github.mkopylec.passwordreset

import com.github.mkopylec.passwordreset.api.UserEndpoint
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
import static org.glassfish.jersey.client.proxy.WebResourceFactory.newResource

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

    protected UserEndpoint getEndpoint() {
        def target = client.target("http://localhost:$context.embeddedServletContainer.port")
        return newResource(UserEndpoint, target)
    }

    void cleanup() {
        mongoTemplate.getDb().dropDatabase()
    }
}
