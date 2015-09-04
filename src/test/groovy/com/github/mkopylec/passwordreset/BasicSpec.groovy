package com.github.mkopylec.passwordreset

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Shared

import javax.ws.rs.client.Client
import javax.ws.rs.client.WebTarget

import static javax.ws.rs.client.ClientBuilder.newClient

@WebIntegrationTest
@ActiveProfiles("test")
@ContextConfiguration(loader = SpringApplicationContextLoader, classes = PasswordResetService)
class BasicSpec {

    @Shared
    private Client client = newClient()
    @Autowired
    private EmbeddedWebApplicationContext context

    protected WebTarget buildWebTarget(String path) {
        return client.target("http://localhost:$context.embeddedServletContainer.port").path(path)
    }
}
