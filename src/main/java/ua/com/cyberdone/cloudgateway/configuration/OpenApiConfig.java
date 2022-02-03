package ua.com.cyberdone.cloudgateway.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Value("${spring.application.version}")
    private String applicationVersion;
    @Value("${server.host}")
    private String serverHost;
    @Value("${server.port}")
    private String serverPort;
    @Value("${server.protocol}")
    private String serverProtocol;

    @Bean
    OpenAPI cyberDoneOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Cyberdone Cloud Gateway Microservice API")
                        .version(applicationVersion)
                        .description("""
                                Cloud Gateway Microservice - entry point to cyberdone microservices.
                                It redirects your calls to one of cyberdone microservices (Account Microservice,
                                Device Microservice, Message Microservice)
                                """))
                .servers(List.of(openApiServer()));
    }

    @Bean
    Server openApiServer() {
        var server = new Server();
        server.setUrl(serverProtocol + "://" + serverHost + ":" + serverPort);
        return server;
    }
}
