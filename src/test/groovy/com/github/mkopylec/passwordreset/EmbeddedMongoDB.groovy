package com.github.mkopylec.passwordreset

import de.flapdoodle.embed.mongo.MongodExecutable
import de.flapdoodle.embed.mongo.config.IMongodConfig
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.mongo.config.Net
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.mongo.MongoProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import static de.flapdoodle.embed.mongo.MongodStarter.getDefaultInstance
import static de.flapdoodle.embed.mongo.distribution.Version.Main.PRODUCTION
import static de.flapdoodle.embed.process.runtime.Network.localhostIsIPv6

@Configuration
@EnableConfigurationProperties(MongoProperties)
class EmbeddedMongoDB {

    @Autowired
    private MongoProperties mongo

    @Bean(initMethod = "start", destroyMethod = "stop")
    public MongodExecutable mongodExecutable() throws IOException {
        IMongodConfig config = new MongodConfigBuilder()
                .version(PRODUCTION)
                .net(new Net(mongo.host, mongo.port, localhostIsIPv6()))
                .build();
        return getDefaultInstance().prepare(config);
    }
}
