package kg.neobis.gateway.configuration;

import kg.neobis.gateway.configuration.enums.ServiceRoute;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationConfiguration {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder builder = routeLocatorBuilder.routes();

        ServiceRoute
                .getServiceRoutes()
                .stream()
                .filter(ServiceRoute::getIsActive)
                .forEach(serviceRoute -> builder
                        .route(
                                "%s-route".formatted(serviceRoute.getId()),
                                r -> r
                                        .path("/%s/**".formatted(serviceRoute.getId()))
                                        .filters(f -> this.setRouteFilter(f, serviceRoute.getId()))
                                        .uri(serviceRoute.getUri()))
                        .route(
                                "%s-swagger-route".formatted(serviceRoute.getId()),
                                r -> r
                                        .path("/v3/api-docs/%s".formatted(serviceRoute.getId()))
                                        .and().method(HttpMethod.GET)
                                        .filters(f -> f.rewritePath("/(?<path>.*)/%s".formatted(serviceRoute.getId()), "/${path}"))
                                        .uri(serviceRoute.getUri())));

        return builder.build();
    }

    private GatewayFilterSpec setRouteFilter(GatewayFilterSpec filter, String routeId) {
        return filter
                .rewritePath("/%s/(?<path>.*)".formatted(routeId), "/${path}")
                .circuitBreaker(config -> config
                        .setName("%s-circuitbreaker".formatted(routeId))
                        .setFallbackUri("forward:/error/service-unavailable/%s".formatted(routeId)));
    }
}
