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
                                <strong>Cloud Gateway Microservice - entry point to Cyber Done microservices.
                                It redirects your calls to one of Cyber Done microservices (Account Microservice,
                                Device Microservice, Message Microservice etc.) To use capabilities of these microservices
                                you should:</strong>
                                <br><strong>1.</strong> Create your account. (Not need to pass JWT)
                                <br><strong>2.</strong> Login to your account and save JWT token (from payload) generated after your login.
                                <br><strong>3.</strong> Transmit JWT with Authorization header.
                                <br><strong><i> Example: Authorization: Bearer <token>
                                <br><strong>4.</strong> Make request to needed service using API documentation.
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
