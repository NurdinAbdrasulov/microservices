package kg.neobis.gateway.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import kg.neobis.gateway.configuration.enums.ServiceRoute;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI openAPI(
            @Value("${info.application.version}") String applicationVersion,
            @Value("${springdoc.server.url}") String serverUrl
    ) {
        return new OpenAPI()
                .servers(List.of(new Server().url(serverUrl)))
                .info(new Info().title("su").version(applicationVersion));
    }

    @Bean
    public CommandLineRunner openApiGroups(SwaggerUiConfigParameters swaggerUiParameters) {
        return args -> ServiceRoute
                .getServiceRoutes()
                .stream()
                .filter(ServiceRoute::getIsActive)
                .forEach(serviceRoute -> swaggerUiParameters.addGroup(serviceRoute.getId()));
    }
}
